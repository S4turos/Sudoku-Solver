/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokubt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class GUISudoku extends JFrame{
    private static final long serialVersionUID= 1L;
    ArrayList<JTextField> textos = new ArrayList();
    Boolean terminado = false;
    Boolean reiniciar = false;
    Boolean fin = false;
    Boolean generado = false; //Esta variable detecta si se ha generado un sudoku
    Boolean enproceso = false;
    Boolean Comprobar = false;
    Boolean contador = false;
    Boolean pensando = false;
    Boolean autoguardado = false;
    Boolean tabla = false;
    Boolean salir = false;
    Boolean suderror = false; //Esta variable recoge si se ha introducido datos incorrectos en el sudoku
    Integer[] panel0 = {0,1,2,9,10,11,18,19,20};
    Integer[] panel1 = {3,4,5,12,13,14,21,22,23};
    Integer[] panel2 = {6,7,8,15,16,17,24,25,26};
    Integer[] panel3 = {27,28,29,36,37,38,45,46,47};
    Integer[] panel4= {30,31,32,39,40,41,48,49,50};
    Integer[] panel5 = {33,34,35,42,43,44,51,52,53};
    Integer[] panel6 = {54,55,56,63,64,65,72,73,74};
    Integer[] panel7 = {57,58,59,66,67,68,75,76,77};
    Integer[] panel8 = {60,61,62,69,70,71,78,79,80};
    int atras,casillas,cambio,limitecasillas,ejecucion,ejecuciont,segundos,seconds;
    int primeracasilla = -1;
    int soluciones = 0;
    final int nfacil = 35; //Número de casillas en dificultad fácil
    final int nnormal = 30; //Número de casillas en dificultad normal
    final int ndificil = 25; //Número de casillas en dificultad difícil
    final int version = 1; //Versión en entero para detectar nuevas actualizaciones
    String tversion = "1.0"; //Versión de la aplicación en decimal
    Integer[][] input = new Integer[9][9];
    ArrayList<Integer[][]> posibleProblema=new ArrayList();
    ArrayList<Integer> nocultos = new ArrayList();
    ArrayList<Integer> defecto = new ArrayList();
    
    public GUISudoku(){
        super("Sudoku Solver");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float prop = (float) 1366/500;
        float tamf = screenSize.width / prop;
        int tamaño = (int) Math.round(tamf);
        float propx = (float) 1366/450;
        float ejex = screenSize.width / propx;
        int ejexf = (int) ejex;
        float propy = (float) 768/100;
        float ejey = screenSize.height / propy;
        int ejeyf = (int) ejey;
        setBounds(ejexf,ejeyf,tamaño,tamaño);
        
        setIconImage(
            new ImageIcon(getClass().getResource("/icon.png")).getImage()
        );
        
        JPanel panel_central = new JPanel(new GridLayout(3,3,3,3));
        panel_central.setBackground(Color.black);
        add(panel_central,BorderLayout.CENTER);
        ArrayList<JPanel> paneles = new ArrayList();
        
        for(int i = 0;i<9;i++){
            JPanel panel = new JPanel(new GridLayout(3,3));
            panel.setBackground(Color.black);
            paneles.add(panel);
            panel_central.add(panel);
        }
        JButton resolver = new JButton("Resolver");
        JButton reinicio = new JButton("Reiniciar");
        JButton generar = new JButton("Generar sudoku");
        JButton comprobar = new JButton("Comprobar solución");
        JButton tablarecords = new JButton("Tabla de records");
        JButton acerca = new JButton("Acerca de...");
        for(int i = 0; i<81;i++){
            JTextField texto = new JTextField();
            final int id = i;
            texto.addKeyListener(new KeyListener (){

                @Override
                public void keyTyped(KeyEvent e) { Tecla (e,id); }

                @Override
                public void keyPressed(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {}
                
            });
            float proporcion = (float) 500/30;
            float tamañotexto = tamaño/proporcion;
            int tamañofinal = (int) tamañotexto;
            Font font1 = new Font("Arial", Font.PLAIN, tamañofinal);
            texto.setFont(font1);
            texto.setForeground(Color.BLUE);
            texto.setPreferredSize(new Dimension(50,50));
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setBorder(BorderFactory.createLineBorder(Color.gray));
            textos.add(texto);
            if(Arrays.asList(panel0).contains(i)) paneles.get(0).add(texto);
            else if(Arrays.asList(panel1).contains(i)) paneles.get(1).add(texto);
            else if(Arrays.asList(panel2).contains(i)) paneles.get(2).add(texto);
            else if(Arrays.asList(panel3).contains(i)) paneles.get(3).add(texto);
            else if(Arrays.asList(panel4).contains(i)) paneles.get(4).add(texto);
            else if(Arrays.asList(panel5).contains(i)) paneles.get(5).add(texto);
            else if(Arrays.asList(panel6).contains(i)) paneles.get(6).add(texto);
            else if(Arrays.asList(panel7).contains(i)) paneles.get(7).add(texto);
            else if(Arrays.asList(panel8).contains(i)) paneles.get(8).add(texto);
        }
        JPanel panel_botones = new JPanel(new GridLayout(2,3,3,3));
        add(panel_botones,BorderLayout.SOUTH);
        panel_botones.add(generar);
        panel_botones.add(comprobar);
        panel_botones.add(resolver);
        panel_botones.add(reinicio);
        panel_botones.add(tablarecords);
        panel_botones.add(acerca);
        
        Cargar();
        Version();
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if(generado){
                    String temp = "";
                    Save sv = new Save();
                    temp = sv.leer();
                    ArrayList<Integer> vindice = new ArrayList();
                    StringTokenizer st = new StringTokenizer(temp,";");
                    Boolean lectura = true;
                    while(lectura){
                        try{
                            vindice.add(Integer.parseInt(st.nextToken()));
                        }catch (Exception ex){
                            lectura = false;
                        }
                    }
                    temp += "\n"+sv.leer()+"\n";
                    String uindice = "";
                    String unumero = "";
                    for(int x = 0; x<81;x++){
                        if(!vindice.contains(x)&&!(textos.get(x).getText()).equals("")){
                            uindice += String.valueOf(x)+";";
                            unumero += textos.get(x).getText()+";";
                        }
                    }
                    sv.escribir(temp+uindice+"\n"+unumero+"\n"+seconds);
                }
                e.getWindow().dispose();
            }
        });
        
        acerca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = new String[] {"Cancelar", "Página web"};
                int response = JOptionPane.showOptionDialog(null, "Versión: "+tversion+""
                        + "\n\nDesarrollado por Carlos Martínez con la inestimable"
                        + "\nayuda de Daniel Martínez."
                        + "\n\nLicencia: GNU General Public License v2.0"
                        + "\n\nPara más información, visite la página web del proyecto.\n\n", "Acerca de",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                switch(response){
                        case 1:
                            if(Desktop.isDesktopSupported())
                                {
                                    try {
                                        Desktop.getDesktop().browse(new URI("https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/wikis/home"));
                                    } catch (Exception ex) {}
                                }
                            break;
                        default:
                            break;
                    }
                }
        });
        
        tablarecords.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                while(!salir){
                    String[] options = new String[] {"Fácil", "Normal", "Difícil", "Reiniciar"};
                    JLabel etiqueta = new JLabel("Seleccione la dificultad:", JLabel.CENTER);
                    int response = JOptionPane.showOptionDialog(null, etiqueta, "Tabla de records",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                    switch(response){
                        case 0:
                            tabla = true;
                            Tabla(0);
                            break;
                        case 1:
                            tabla = true;
                            Tabla(1);
                            break;
                        case 2:
                            tabla = true;
                            Tabla(2);
                            break;
                        case 3:
                            reRecords();
                            break;
                        default:
                            salir = true;
                            break;
                    }
                }
                salir = false;
            }
        });
        
        
        generar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean cancelado = false;
                String[] options = new String[] {"Fácil", "Normal", "Difícil"};
                JLabel etiqueta = new JLabel("Seleccione la dificultad deseada:", JLabel.CENTER);
                int response = JOptionPane.showOptionDialog(null,etiqueta, "Dificultad",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
                switch(response){
                    case 0:
                        limitecasillas = nfacil;
                        break;
                    case 1:
                        limitecasillas = nnormal;
                        break;
                    case 2:
                        limitecasillas = ndificil;
                        break;
                    default:
                        cancelado = true;
                        break;
                }
                if(!cancelado){
                    autoguardado = false;
                    defecto.clear();
                    enproceso = true;
                    Comprobar = true;
                    Integer[][] problema = new Integer[9][9];
                    for(int i =0;i<81;i++){
                        textos.get(i).setForeground(Color.BLUE);
                        textos.get(i).setBackground(Color.WHITE);
                        textos.get(i).setEnabled(true);
                    }
                    primeracasilla = -1;
                    reiniciar = false;
                    terminado = false;
                    //int z = 0;
                    ejecucion = 0;
                    pensando = true;
                    Tiempo();
                    segundos = 0;
                    while(enproceso){
                        contador = true;
                        atras = 0;
                        //z++;
                        nocultos.clear();
                        posibleProblema.clear();
                        fin = false;
                        soluciones = 0;
                        for(int x = 0;x<9;x++){
                            for(int y = 0;y<9;y++){
                                problema[x][y]=0;
                            }
                        }
                        //System.out.println("Sudoku "+z);
                        generarSudoku(0,problema);
                    }  
                }
            }
        });
        
        comprobar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (reiniciar) JOptionPane.showMessageDialog(null, "Por favor, reinicie el sudoku.",
                        "Error",JOptionPane.INFORMATION_MESSAGE);
                else{
                    primeracasilla = -1;
                    terminado = false;
                    Comprobar = true;
                    soluciones = 0;
                    casillas = 0;
                    if(generado){
                        Integer[][] inputcomp = new Integer[9][9];
                        for (int x = 0; x < 81; x++){
                            if(defecto.contains(x)){
                                inputcomp[x/9][x%9] = Integer.parseInt(textos.get(x).getText());
                                casillas++;
                            }
                            else{
                                inputcomp[x/9][x%9] = 0;
                                if(primeracasilla==-1) primeracasilla = x;
                            }
                        }
                        sbt(0,inputcomp);
                    }else{
                        if(!suderror){
                            for (int x = 0; x < 81; x++){
                                if(input[x/9][x%9] != 0) casillas++;
                                else if(primeracasilla == -1) primeracasilla = x;
                            }
                            sbt(0,input);
                        }
                        else JOptionPane.showMessageDialog(null, "Ha introducido datos incorrectos.",
                                "Error",JOptionPane.INFORMATION_MESSAGE);
                    }
            }
            }
        });
        
        resolver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                terminado = false;
                Comprobar = false;
                soluciones = 0;
                casillas = 0;
                primeracasilla = -1;
                if(generado){
                    String[] options = new String[] {"Cancelar", "Resolver"};
                    JLabel etiqueta = new JLabel("¿Desea resolver el sudoku?", JLabel.CENTER);
                    int response = JOptionPane.showOptionDialog(null, etiqueta, "Resolver",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                    switch(response){
                        case 1:
                            for (int x = 0; x < 81; x++){
                                if(!defecto.contains(x)){
                                    input[x/9][x%9] = 0;
                                    textos.get(x).setEnabled(false);
                                    textos.get(x).setDisabledTextColor(Color.RED);
                                    textos.get(x).setBackground(Color.WHITE);
                                }
                            }
                            sbt(0,input);
                            generado = false;
                            ejecuciont = 0;
                            Save s = new Save();
                            s.escribir("");
                            break;
                        default:
                            break;
                        }
                }
                else if (reiniciar) JOptionPane.showMessageDialog(null, "Por favor, reinicie el sudoku.",
                        "Error",JOptionPane.INFORMATION_MESSAGE);
                else if(!suderror){
                    for(int x = 0; x < 81; x++){
                        if (input[x/9][x%9] == 0){
                            if(primeracasilla == -1) primeracasilla = x;
                            textos.get(x).setEnabled(false);
                            textos.get(x).setForeground(Color.RED);
                            textos.get(x).setDisabledTextColor(Color.RED);
                        }
                    }
                    sbt(0,input);
                }
                else JOptionPane.showMessageDialog(null, "Ha introducido datos incorrectos.",
                        "Error",JOptionPane.INFORMATION_MESSAGE);
                }
        });
        
        reinicio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean reiniciado = false;
                if(generado){
                    String[] options = new String[] {"Cancelar", "Reiniciar"};
                    JLabel etiqueta = new JLabel("¿Desea reiniciar todas las casillas?", JLabel.CENTER);
                    int response = JOptionPane.showOptionDialog(null, etiqueta, "Reiniciar",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                    switch(response){
                        case 1:
                            reiniciado = true;
                            break;
                        default:
                            break;
                        }
                }
                if(reiniciado || !generado){
                    autoguardado = false;
                    Save s = new Save();
                    s.escribir("");
                    ejecuciont = 0;
                    seconds = 0;
                    generado = false;
                    Comprobar = false;
                    casillas = 0;
                    soluciones = 0;
                    primeracasilla = -1;
                    reiniciar = false;
                    terminado = false;
                    suderror = false;
                    defecto.clear();
                    for(int i = 0; i<81 ; i++){
                        input[i/9][i%9] = 0;
                        textos.get(i).setText("");
                        textos.get(i).setForeground(Color.BLUE);
                        textos.get(i).setEnabled(true);
                        textos.get(i).setBackground(Color.WHITE);
                    }
                }
            }
        });
    }
    private void sbt(int puestas, Integer[][] sol){
        if(puestas == 81 && !terminado){
            soluciones++;
            if(soluciones > 1 && enproceso) terminado = true;
            if(soluciones > 100 && !enproceso){
                terminado = true;
                JOptionPane.showMessageDialog(null, 
                        "Número de soluciones: más de 100\nNúmero de casillas: "+casillas,
                        "Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
            if(!Comprobar) setText(sol);
        }
        else if(sol[puestas/9][puestas%9] == 0){
            for(int c = 1; c <= 9 && !terminado; c++){
                   sol[puestas/9][puestas%9] = c;
               if(factible(puestas,sol)) sbt(puestas+1,sol);
            }
            sol[puestas/9][puestas%9] = 0;
            if(primeracasilla == puestas && !terminado){
                if(soluciones == 0) nosol();
                else JOptionPane.showMessageDialog(null, 
                        "Número de soluciones: "+soluciones+"\nNúmero de casillas: "+casillas,
                        "Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            sbt(puestas+1,sol);
            }
    }
    
    private Boolean factible(int puestas, Integer[][] sol){
        int x = puestas/9;
        int y = puestas%9;
        for(int i =0;i<9;i++){
            if(sol[x][y]==sol[x][i]&&y!=i)return false;
            if(sol[x][y]==sol[i][y] && x !=i)return false;
        }
        
        while(x%3!=0)x--;
        while(y%3!=0)y--;
        
        for(int i =x;i<x+3;i++){
            for (int j = y;j<y+3;j++){
                if((i!=puestas/9)&&(j!=puestas%9)){
                    if(sol[puestas/9][puestas%9]==sol[i][j])return false;
                }
            }
        }
        return true;
    }
    
    private void setText(Integer[][]sol){
        Comprobar = false;
        terminado = true;
        reiniciar = true;
        int i = 0;
        for(int x=0;x<9;x++){
            for(int y =0;y<9;y++){
                textos.get(i).setText(String.valueOf(sol[x][y]));
                i++;
            }
        }
    }
    
    private Boolean comprobacion(Integer[][]escritas){
        for(int a = 0;a<81;a++){
            int x = a/9;
            int y = a%9;
            if(escritas[x][y]!=0){
              for(int i =0;i<9;i++){
                if(escritas[x][y]==escritas[x][i]&&y!=i){
                    if(!defecto.contains(a)){
                        textos.get(a).setBackground(Color.LIGHT_GRAY);
                        return false;
                    }
                }
                if(escritas[x][y]==escritas[i][y] && x !=i){
                    if(!defecto.contains(a)){
                        textos.get(a).setBackground(Color.LIGHT_GRAY);
                        return false;
                    }
                }
            }

            while(x%3!=0)x--;
            while(y%3!=0)y--;

                for(int i =x;i<x+3;i++){
                    for (int j = y;j<y+3;j++){
                        if((i!=a/9)&&(j!=a%9)){
                            if(escritas[a/9][a%9]==escritas[i][j]){
                                if(!defecto.contains(a)){
                                    textos.get(a).setBackground(Color.LIGHT_GRAY);
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            textos.get(a).setBackground(Color.WHITE);
        }
        return true;
    }
    
    private void nosol(){
        primeracasilla = -1;
        JOptionPane.showMessageDialog(null, "El sudoku no tiene solución.","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        for(int x = 0;x<81;x++){
            textos.get(x).setForeground(Color.BLUE);
            textos.get(x).setEnabled(true);
        }
    }
    
    private void generarSudoku(int puestas, Integer[][] problema){
        if(puestas == 81){
            fin = true;
            Integer [] ocultos = new Integer[81];
            Contador();
            prepararProblema(problema, ocultos, 80);
        }
        else{
            Random p = new Random();
            int r = p.nextInt(9)+1;
            for (int i = 0;i<9&&!fin;i++){
                if(i+r>9) r = i+r-9;
                else r = i+r;
                problema[puestas/9][puestas%9] = r;
                if(factible(puestas,problema))generarSudoku(puestas+1,problema);
            }
            problema[puestas/9][puestas%9] = 0;
        }
        
        
        
        
    }
    
    private void mostrarProblema(Integer[][] problemaFinal){
        generado = true;
        int i = 0;
        String pordefecto = "";
        String valores = "";
        for(int x=0;x<9;x++){
            for(int y =0;y<9;y++){
                if(problemaFinal[x][y]==0){
                    textos.get(i).setText("");
                    textos.get(i).setForeground(Color.RED);
                    textos.get(i).setDisabledTextColor(Color.RED);
                }else{
                    textos.get(i).setText(String.valueOf(problemaFinal[x][y]));
                    textos.get(i).setEnabled(false);
                    textos.get(i).setDisabledTextColor(Color.BLUE);
                    defecto.add(i);
                    pordefecto += i+";";
                    valores += String.valueOf(problemaFinal[x][y])+";";
                }
                i++;
            }
        }
        Save s = new Save();
        s.escribir(pordefecto+"\n"+valores);
        autoguardado = true;
        seconds = 0;
        Autoguardado();
        Comp(); //Para guardar el input generado
    }
    
    private void prepararProblema(Integer[][]problema, Integer[]ocultos, int restantes){
        for(int x = 0;x<10&&enproceso&&atras<=500&&contador;x++){
            int r;
            Random p = new Random();
            do r = p.nextInt(81);
            while(Arrays.asList(ocultos).contains(r));
            int cambio = problema[r/9][r%9];
            problema[r/9][r%9]=0;
            soluciones = 0;
            terminado = false;
            ocultos[81-restantes]=r;
            sbt(0,problema);
            if(soluciones == 1 &&restantes>limitecasillas)prepararProblema(problema,ocultos,restantes-1);
            else if(soluciones==1&&restantes<=limitecasillas){
                pensando = false;
                //System.out.println("Ha tardado "+segundos+" segundos.");
                enproceso = false;
                mostrarProblema(problema);
            }
            problema[r/9][r%9]=cambio;
            ocultos[81-restantes]=null;
            atras++;
        }
    }
    
    private void Contador(){
        
        Runnable r = new Runnable() {
            
            int hilo;
            
            @Override
            public void run() {
                hilo = ejecucion;
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {}
                if(ejecucion==hilo)contador = false;
            }


        };
        ejecucion++;
        Thread thread = new Thread(r);
        thread.start();
    }
    
    private void Tiempo(){
        
        Runnable r = new Runnable() {
            
            @Override
            public void run() {
                while(pensando){
                    try {
                    Thread.sleep(1000);
                    } catch (Exception ex) {}
                    segundos++;
                }
            }


        };
        Thread thread = new Thread(r);
        thread.start();
    }
    
    private void Autoguardado(){
        
        Runnable r = new Runnable() {
            
            int hilo;
            
            @Override
            public void run() {
                hilo = ejecuciont;
                while(hilo==ejecuciont){
                    for(int z = 0;z<60&&hilo==ejecuciont;z++){
                        try {
                        Thread.sleep(1000);
                        } catch (Exception ex) {}
                        if(hilo==ejecuciont) seconds++;
                    }
                    if(hilo==ejecuciont){
                        String temp = "";
                        Save sv = new Save();
                        temp = sv.leer();
                        ArrayList<Integer> vindice = new ArrayList();
                        StringTokenizer st = new StringTokenizer(temp,";");
                        Boolean lectura = true;
                        while(lectura){
                            try{
                                vindice.add(Integer.parseInt(st.nextToken()));
                            }catch (Exception e){
                                lectura = false;
                            }
                        }
                        temp += "\n"+sv.leer()+"\n";
                        String uindice = "";
                        String unumero = "";
                        for(int x = 0; x<81;x++){
                            if(!vindice.contains(x)&&!(textos.get(x).getText()).equals("")){
                                uindice += String.valueOf(x)+";";
                                unumero += textos.get(x).getText()+";";
                            }
                        }
                        sv.escribir(temp+uindice+"\n"+unumero+"\n"+seconds);
                    }
                }
            }


        };
        ejecuciont++;
        Thread thread = new Thread(r);
        thread.start();
    }
    
    private void Cargar(){
        Save save = new Save();
        Save comprobador = new Save();
        String firstline = "";
        Boolean lectura = true;
        firstline = comprobador.leer();
        if(firstline!=null){
            if(!firstline.equals("")){
                //Hay un sudoku guardado
                generado = true;
                autoguardado = true;
                Autoguardado();
                String indice = save.leer();
                String numeros = save.leer();
                String userindex = save.leer();
                String usernumber = save.leer();
                try{
                    seconds = Integer.parseInt(save.leer());
                }catch (Exception e){}
                ArrayList<Integer> vindice = new ArrayList();
                ArrayList<String> vnumeros = new ArrayList();
                StringTokenizer st = new StringTokenizer(indice,";");
                StringTokenizer st2 = new StringTokenizer(numeros,";");
                while(lectura){
                    try{
                        vindice.add(Integer.parseInt(st.nextToken()));
                        vnumeros.add(st2.nextToken());
                    }catch (Exception e){
                        lectura = false;
                    }
                }
                lectura = true;
                for(int x = 0; x<vindice.size();x++){
                    textos.get(vindice.get(x)).setText(vnumeros.get(x));
                    textos.get(vindice.get(x)).setEnabled(false);
                    textos.get(vindice.get(x)).setDisabledTextColor(Color.BLUE);
                    defecto.add(vindice.get(x));
                }
                for(int x = 0;x<81;x++){
                    if(!vindice.contains(x)){
                        textos.get(x).setForeground(Color.RED);
                        textos.get(x).setDisabledTextColor(Color.RED);
                    }
                }
                try{
                    StringTokenizer st3 = new StringTokenizer(userindex,";");
                    StringTokenizer st4 = new StringTokenizer(usernumber,";");
                    ArrayList<Integer> uindice = new ArrayList();
                    ArrayList<String> unumeros = new ArrayList();
                    while(lectura){
                    try{
                        uindice.add(Integer.parseInt(st3.nextToken()));
                        unumeros.add(st4.nextToken());
                    }catch (Exception e){
                        lectura = false;
                    }
                    for(int x = 0; x<uindice.size();x++){
                    textos.get(uindice.get(x)).setText(unumeros.get(x));
                    }
                }
                }catch(Exception e){}
            }
        }
        Comp();
    }
    
    private void Tabla(int dificultad){
        Record r = new Record();
        String rbruto = "";
        String tdificultad= "";
        String[] temp = new String [2];
        ArrayList<Integer> records = new ArrayList();
        switch(dificultad){
            case 0:
                rbruto = r.leer();
                temp[0] = r.leer();
                temp[1] = r.leer();
                tdificultad = "Fácil";
                break;
            case 1:
                temp[0] = r.leer();
                rbruto = r.leer();
                temp[1] = r.leer();
                tdificultad = "Normal";
                break;
            case 2:
                temp[0] = r.leer();
                temp[1] = r.leer();
                rbruto = r.leer();
                tdificultad = "Difícil";
                break;
        }
        StringTokenizer st = new StringTokenizer(rbruto,";");
        try{
            while(true) records.add(Integer.parseInt(st.nextToken()));
        }catch(Exception ex){}
        Boolean maximo = false;
        Boolean batido = false;
        if(records.size()<10 && !tabla){
            rbruto += seconds+";";
            records.add(seconds);
            maximo = true;
            batido = true;
        }
        Integer[] rpulido = new Integer[10];
        for(int z = 0; z<10;z++){
            if(z<records.size())rpulido[z] = records.get(z);
            else rpulido[z] = 0;
        }
        String[] trecord = new String[10];
        Arrays.sort(rpulido);
        if (records.size() == 10 && rpulido[9] > seconds && !maximo && !tabla){
            rbruto = rbruto.replaceFirst(String.valueOf(rpulido[9]),String.valueOf(seconds));
            rpulido[9] = seconds;
            Arrays.sort(rpulido);
            batido = true;
        }
        if (rpulido[9] != 0){
            while(rpulido[0] == 0){
                for (int y = 0; y<9; y++){
                    int cambio;
                    if(rpulido[y+1] != 0){
                        cambio = rpulido[y+1];
                        rpulido[y] = cambio;
                        rpulido[y+1] = 0;
                    }
                }
            }
        }
        String [] name = new String [10];
        int nuevo = -1;
        for (int z = 0; z < 10;z++){
            if(rpulido[z] == 0){
                trecord[z] = "";
                name[z] = "Vacío";
            }
            else{
                name[z] = System.getenv("USERNAME");
                if(rpulido[z] == seconds) nuevo = z;
                if(rpulido[z]%60<10){
                    trecord[z] = String.valueOf(rpulido[z]/60)+":0"+String.valueOf(rpulido[z]%60);
                }
                else trecord[z] = String.valueOf(rpulido[z]/60)+":"+String.valueOf(rpulido[z]%60);
            }
        }
        if (nuevo != -1 && batido){
            String modificacion = name[nuevo];
            name[nuevo] = "<font color='red'>" + modificacion + "</font>";
            modificacion = trecord[nuevo];
            trecord[nuevo] = "<font color='red'>" + modificacion + "</font>";
        }
        JLabel etiqueta = new JLabel("<html>1º&nbsp;&nbsp;&nbsp;"+name[0]+"&nbsp;&nbsp;&nbsp;"+trecord[0]+"<br/>"
                    + "2º&nbsp;&nbsp;&nbsp;"+name[1]+"&nbsp;&nbsp;&nbsp;"+trecord[1]+"<br/>"
                    + "3º&nbsp;&nbsp;&nbsp;"+name[2]+"&nbsp;&nbsp;&nbsp;"+trecord[2]+"<br/>"
                    + "4º&nbsp;&nbsp;&nbsp;"+name[3]+"&nbsp;&nbsp;&nbsp;"+trecord[3]+"<br/>"
                    + "5º&nbsp;&nbsp;&nbsp;"+name[4]+"&nbsp;&nbsp;&nbsp;"+trecord[4]+"<br/>"
                    + "6º&nbsp;&nbsp;&nbsp;"+name[5]+"&nbsp;&nbsp;&nbsp;"+trecord[5]+"<br/>"
                    + "7º&nbsp;&nbsp;&nbsp;"+name[6]+"&nbsp;&nbsp;&nbsp;"+trecord[6]+"<br/>"
                    + "8º&nbsp;&nbsp;&nbsp;"+name[7]+"&nbsp;&nbsp;&nbsp;"+trecord[7]+"<br/>"
                    + "9º&nbsp;&nbsp;&nbsp;"+name[8]+"&nbsp;&nbsp;&nbsp;"+trecord[8]+"<br/>"
                    + "10º&nbsp;&nbsp;&nbsp;"+name[9]+"&nbsp;&nbsp;&nbsp;"+trecord[9]+"</html>", JLabel.CENTER);
        if(!tabla){
            JOptionPane.showMessageDialog(null,etiqueta,tdificultad,JOptionPane.PLAIN_MESSAGE);
        }else{
            String[] options = new String[] {"Atrás", "Aceptar"};
            int response = JOptionPane.showOptionDialog(null,etiqueta,tdificultad,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            switch(response){
                case 0:
                    salir = false;
                    break;
                case 1:
                    salir = true;
                    break;
                default:
                    salir = true;
                    break;
            }
        }
        if(!tabla){
            switch(dificultad){
                case 0:
                    r.escribir(rbruto+"\n"+temp[0]+"\n"+temp[1]+"\n");
                    break;
                case 1:
                    r.escribir(temp[0]+"\n"+rbruto+"\n"+temp[1]+"\n");
                    break;
                case 2:
                    r.escribir(temp[0]+"\n"+temp[1]+"\n"+rbruto+"\n");
                    break;
            }
            seconds = 0;
        }
        tabla = false;
    }
    
    private void Tecla(KeyEvent e, int id) {
        
        char letra = e.getKeyChar();
        int number = Character.getNumericValue(letra);
        
        if(number > 0 && number < 10){
            if(textos.get(id).getText().length() == 1) e.consume(); //Para no escribir números con más de una cifra
            else Comp(); //Se ha introducido el valor correcto
        }
        else if (letra != KeyEvent.VK_BACK_SPACE) e.consume(); //No se ha introducido el valor correcto
        else Comp(); //Se ha pulsado tecla retroceso
    }
    
    private void Comp(){ //Esta función permite comprobar cada vez que se le llama si se ha introducido un dato incorrecto
        
        Runnable r = new Runnable() {
            
            Boolean correcto = true;
            
            @Override
            public void run() {
                try {
                    Thread.sleep(10); //Este sleep sirve para manejar un extraño error de concurrencia
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUISudoku.class.getName()).log(Level.SEVERE, null, ex);
                }
                suderror = false;
                for(int x = 0; x < 81; x++){
                    if(textos.get(x).getText().equals("")){
                        input[x/9][x%9] = 0;
                        correcto = false;
                    }
                    else input[x/9][x%9]=Integer.parseInt(textos.get(x).getText());
                }
                for(int a = 0;a<81;a++){
                    Boolean error = false;
                    int x = a/9;
                    int y = a%9;
                    if(input[x][y]!=0){
                      for(int i =0;i<9;i++){
                        if(input[x][y] == input[x][i] && y!=i){
                            if(!defecto.contains(a)){
                                textos.get(a).setBackground(Color.LIGHT_GRAY);
                                correcto = false;
                                error = true;
                                suderror = true;
                            }
                        }
                        if(input[x][y] == input[i][y] && x !=i){
                            if(!defecto.contains(a)){
                                textos.get(a).setBackground(Color.LIGHT_GRAY);
                                correcto = false;
                                error = true;
                                suderror = true;
                            }
                        }
                    }

                    while(x%3!=0)x--;
                    while(y%3!=0)y--;

                        for(int i =x;i<x+3;i++){
                            for (int j = y;j<y+3;j++){
                                if((i!=a/9)&&(j!=a%9)){
                                    if(input[a/9][a%9] == input[i][j]){
                                        if(!defecto.contains(a)){
                                            textos.get(a).setBackground(Color.LIGHT_GRAY);
                                            correcto = false;
                                            error = true;
                                            suderror = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(!error) textos.get(a).setBackground(Color.WHITE);
                }
                if(correcto){
                    //Sudoku resuelto
                    generado = false;
                    int min = seconds/60;
                    int sec = seconds%60;
                    ejecuciont = 0;
                    for (int x = 0; x < 81; x++) input[x/9][x%9] = 0;
                    JOptionPane.showMessageDialog(null, 
                            "¡Enhorabuena! Ha resuelto el sudoku"
                                    + "\nen "+min+" minutos y "+sec+" segundos.",
                            "Resuelto",JOptionPane.INFORMATION_MESSAGE);
                    Save s = new Save();
                    s.escribir("");
                    reiniciar = true;
                    for(int z = 0;z<81;z++)textos.get(z).setEnabled(false);
                    switch(defecto.size()){
                        case nfacil:
                            Tabla(0);
                            break;
                        case nnormal:
                            Tabla(1);
                            break;
                        case ndificil:
                            Tabla(2);
                            break;
                    }
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }
    
    private void Version(){ //Esta función detecta la última versión disponible
        
        Runnable r = new Runnable() {
                
            @Override
            public void run() {
                    try {
                        URL url = new URL("http://lotrox.ujacraft.es/SaturosCloud/datos/version");
                        BufferedReader lector = new BufferedReader(new InputStreamReader(url.openStream()));
                        int versionurl = Integer.parseInt(lector.readLine());
                        if(version != versionurl){
                            Recordar rec = new Recordar();
                            long system = System.currentTimeMillis()/1000;
                            long lectura;
                            try {
                                lectura = Long.parseLong(rec.leer());
                            } catch (Exception ex) {
                                lectura = system;
                            }
                            if(system >= lectura){
                                String[] options = new String[] {"Recordar más tarde", "Descargar"};
                                JLabel etiqueta = new JLabel("<html>Ésta es una versión antigua de Sudoku Solver."
                                        + "<br/>¿Desea descargar la nueva actualización?</html>", JLabel.CENTER);
                                int response = JOptionPane.showOptionDialog(null, etiqueta, "Nueva versión",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null, options, options[0]);
                                switch(response){
                                    case 1:
                                        if(Desktop.isDesktopSupported())
                                        {
                                          Desktop.getDesktop().browse(new URI("http://lotrox.ujacraft.es/SaturosCloud/datos/sudokuViews/check.html"));
                                        }
                                        Recordar r1 = new Recordar();
                                        long systemseconds1 = System.currentTimeMillis()/1000;
                                        long recordatorio1 = systemseconds1 + 86400;
                                        r1.escribir(String.valueOf(recordatorio1));
                                        break;
                                    default:
                                        Recordar r = new Recordar();
                                        long systemseconds = System.currentTimeMillis()/1000;
                                        long recordatorio = systemseconds + 86400;
                                        r.escribir(String.valueOf(recordatorio));
                                        break;
                                }
                            }
                        }
                    } catch (Exception e) {}
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }
    
    private void reRecords(){
        String[] options = new String[] {"Cancelar", "Reiniciar"};
        JLabel etiqueta = new JLabel("¿Desea reiniciar los records?", JLabel.CENTER);
        int response = JOptionPane.showOptionDialog(null, etiqueta, "Reiniciar records",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
        switch(response){
            case 1:
                Record r = new Record();
                r.escribir("\n\n\n");
                JOptionPane.showMessageDialog(null,"Los records se han reiniciado.","Reiniciado",JOptionPane.INFORMATION_MESSAGE);
                salir = true;
                break;
            default:
                salir = true;
                break;
            }
    }
}