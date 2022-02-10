/*
 * TAREA PARA PSP01 EJERCICIO 2.
 * PRIMERA PARTE:
 * Implementa una aplicación llamada 'lenguaje', que escriba en un fichero 
 * indicado por el usuario conjuntos de letras generadas de forma aleatoria 
 * (sin sentido real).
 * Escribiendo cada conjunto de letras en una línea distinta y con de un número
 * de letras dado por el usuario  en el momento de la ejecución. 
 */
package lenguaje;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Random;

/**
 *
 * @author juang <juangmuelas@gmail.com>
 * @since 02/11/2020
 */
public class Lenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Siguiendo el consejo del tutor, se reaprovecha parte de los ejemplos 
         * de accesos sincro, usando objeto RandomAccessFile
         */
        
        
        /**
        * @param args numPalabras argumento a pasar para crear archivo
        * @param args nombreFichero argumento a pasar para crear archivo
        */
        
        int numPalabras=0;
        String nombreFichero="";
        
        /**
        * @param args fichero variable para la creación del objeto File
        */
        
        File fichero = null;
        
        /**
        * @param args raf variable dea acceso para el objeto RandomAcessFile
        */
        
        RandomAccessFile raf = null;
        
        /**
        * @param args bloqueo variable para el control de acceso
        */
        FileLock bloqueo = null;
        
        /**
         * El ejercicio pide que la aplicacion solicite número de palabras y 
         * fichero. Siguiendo el ejemplo dado, damos al condicional la necesidad
         * de pasar los dos argumentos y dentro, evitamos consultar el SO (fuera a
         * priori de las indicaciones del ejercicio propuesto.
         */

        if (args.length==2){
            
            try{
                numPalabras = Integer.parseInt(args[0]);
                nombreFichero = args[1];
                
                // Creamos un objeto File para acceder al fichero
                //Antes por ordenar el código declaramos la variable al inicio
                fichero = new File(nombreFichero);
                
                /**
                 * Tras evitar las consultas del SO, la siguiente parte nos lleva 
                 * a acceder al fichero, pero antes se debe verificar si este existe
                 * o debe crearse.
                 */
                if (!fichero.exists()){
                //Si no existe el fichero
                    try {
                        fichero.createNewFile(); //Lo creamos             
                    }catch(Exception e){
                        System.err.println("ERROR EN LA CREACIÓN DEL FICHERO");
                        System.exit(1); //Si hay error, finalizamos
                    }
                }
                try{
                    raf = new RandomAccessFile(fichero,"rw"); //Abrimos el fichero 
                    //*************
                    //Sección crítica
                    bloqueo = raf.getChannel().lock();
                    //bloqueamos el canal de acceso al fichero
                    for (int a=0; a<numPalabras; a++){
                        Random r = new Random();
                        //Añadimos por control una limitación de letras por palabra
                        int nLetras= r.nextInt(20)+1;
//                        int cant= (int) Math.round(Math.random() * 50 + 1);
                        //conseguimos el número aleatorio de letras para cada palabra
                        //he puesto 51 de máximo para que no fueran demasiado extensas
                        for (int i=0; i<nLetras; i++){
                            //nos colocamos al final del fichero para añadir valores
                            raf.seek(raf.length()); 
                            String[] abc = {
                                "A","B","C","D","E","F","G","H","I","J","K",
                                "L","M","N","Ñ","O","P","Q","R","S","T","U",
                                "V","W","X","Y","Z"};
                            int numLetra = r.nextInt(26);
//                            int numLetra = (int) Math.round(Math.random() * 26 );
                            raf.writeBytes(abc[numLetra]);                          
                            //escribimos la letra conseguida en el fichero
                        }
                    raf.seek(raf.length());                    
                    raf.writeChar('\n');
                    //para dividir las palabras, he considerado ponernos al final 
                    //de fichero cuando terminase de generar la palabra y escribir
                    //un salto de línea
                    }
                    System.out.println("Proceso terminado");
                    bloqueo.release(); //liberamos el bloqueo del fichero
                    bloqueo = null;
                    //Fin sección crítica
                    //*************
                }catch(IOException eio){
                    System.err.println("Erroral acceder al fichero");
                    System.err.println(eio.toString());
                }
            }catch(NumberFormatException ex){//Errores de formato
        	System.out.println("Debe indicar el número de palabras y nombre de fichero");
                System.err.println(ex.toString());
            }
            // CERRAR FICHERO.
            try{ 
                if (null != raf) raf.close();
                if (null != bloqueo) bloqueo.release();
            } catch (Exception e2) {
                System.err.println("Error al cerrar el fichero");
                System.err.println(e2.toString());
                System.exit(1); //Si hay error, finalizamos
            }
        }else{
            System.out.println("Debe indicar el número de palabras y nombre de fichero");
        }        

    } //fin clase Main   
}//Fin clase lenguaje
