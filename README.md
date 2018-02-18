**<div align="center">Sudoku Solver</div>**
-------------

<br/>

<p align="center"><img src="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/uploads/871afb28b48ec967f711c121f5487d6c/sudokuportada.jpg"></p>

<br/>
<h4><div align="center">
<a href="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/raw/master/Sudoku%20Solver.jar"><b>Descargar Sudoku Solver v1.0</b></a></div></h4>

<br/>

**¿Qué es Sudoku Solver?**

Juegue al sudoku tantas veces como quiera con Sudoku Solver, un programa que utiliza la interfaz gráfica de Java para generar de manera aleatoria sudokus. Estos se dividen en tres dificultades dependiendo del número de casillas creadas por defecto: fácil (35 casillas), normal (30 casillas) y difícil (25 casillas). Cabe destacar que el tamaño de la ventana se ajusta a la resolución del escritorio.

Para mejorar la experiencia de juego, se ha incluido una ayuda visual que rellena la casilla de color gris al escribir un número incorrecto, como cuando existe una coincidencia en la fila, columna o caja correspondiente. Además, incorpora un sistema de guardado para continuar el sudoku en otro momento, así como un servicio de notificación de actualizaciones que informa al usuario de la publicación de nuevas versiones ofreciendo la posibilidad de descarga directa.

<br/>

<p align="center"><img src="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/uploads/999aa831792c08107ea4a3d352a3f638/coincidencia_caja.gif"></p>
<p align="center">Coincidencia en caja</p>

<br/>

Una vez rellenadas todas las casillas del sudoku, aparecerá un mensaje de enhorabuena junto con el tiempo que se ha invertido en resolverlo. Sin embargo, si existe al menos una colisión con algún valor introducido, el sistema no dará por resuelto el sudoku hasta que se revisen las colisiones que aparecen sombreadas de color gris.

Por otro lado, se puede introducir un sudoku para que Sudoku Solver lo resuelva de manera instantánea gracias a un algoritmo programado por <a href="https://twitter.com/Dani2Martinez" target="_blank">Daniel Martínez</a> mediante la técnica "Backtracking"; asimismo, el botón "Comprobar solución" permite consultar el número de soluciones del sudoku y las casillas del mismo.

Sudoku Solver ha sido probado y funciona correctamente en los siguientes sistemas operativos:
- Windows.
- OS X.
- Ubuntu.

<br/>

**Opciones de Sudoku Solver**

<br/>

<p align="center"><img src="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/uploads/2b6598aabbc374ad5a43c9d032e836e6/menusudoku.jpg"></p>

<br/>

- Generar sudoku: abre un cuadro de diálogo para seleccionar la dificultad del sudoku con las opciones "Fácil" (35 casillas), "Normal" (30 casillas) y "Difícil" (25 casillas). Una vez creado el sudoku, los números azules indican los generados por el programa y los números rojos los colocados por el usuario.

<br/>

<p align="center"><img src="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/uploads/d544ea82737c14aea246eee511a0359d/sudokugen.gif"></p>

<br/>

- Comprobar solución: comprueba el número de soluciones y el número de casillas del sudoku en pantalla.

<br/>

<p align="center"><img src="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/uploads/d3904fcc2f859b3dff03a6a3ec980d03/comprobar.jpg"></p>

<br/>

- Resolver: devuelve la primera solución encontrada. Si no se ha generado un sudoku previamente, el color de los números es inverso; es decir, en color azul se representan los números introducidos por el usuario y en color rojo los calculados por el programa.

<br/>

<p align="center"><img src="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/uploads/5d83abc48fdbfe5ea13ac92c126db5b1/sudokures.gif"></p>


<br/>

- Reiniciar: reinicia todas las casillas del sudoku.
- Tabla de records: permite consultar los records del usuario en cada una de las dificultades pudiendo guardar hasta un máximo de 10 records por dificultad. Se pueden borrar los records guardados pulsando el botón "Reiniciar" del menú emergente. En la siguiente imagen se muestra como ejemplo los records conseguidos en la dificultad fácil:

<br/>

<p align="center"><img src="https://git.ujacraft.es/h02marmc/interfaz_sudokuBT/uploads/d45264883a667245d464d33f7fecd8c6/records2.jpg"></p>

<br/>

- Acerca de...: abre una ventana con información relativa al programa, como la versión actual y un enlace para visitar la página web del proyecto.


<br/>


**Requisitos**


- Java 7 o superior.