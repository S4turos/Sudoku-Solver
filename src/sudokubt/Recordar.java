/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokubt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Carlos
 */
public class Recordar {
    
    File f;
    BufferedReader br;
    FileReader fr;
    
    public Recordar(){
        File dir = new File(System.getenv("APPDATA")+"/.sudoku");
        dir.mkdir();
        this.f = new File (System.getenv("APPDATA")+"/.sudoku/recordar");
        try{
            this.f.createNewFile();
        }catch(Exception e){}
        try{
            this.fr = new FileReader(f);
            this.br = new BufferedReader(fr);
        }catch(Exception e){}
    }
    
    public void escribir(String texto){
        try{
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter pr = new PrintWriter(bw);
            pr.write(texto);
            pr.close();
            bw.close();
        }catch (Exception e){}
    }
    
    public String leer() throws Exception{
        String linea="";
        linea = br.readLine();
        return linea;
    }
}