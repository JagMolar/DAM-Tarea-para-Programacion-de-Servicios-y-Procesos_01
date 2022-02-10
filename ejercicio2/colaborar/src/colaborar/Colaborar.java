/*
 * TAREA PARA PSP01 EJERCICIO 2.
 * SEGUNDA PARTE:
 * Implementa una aplicación llamada 'colaborar', que lance al menos 10 
 * instancias de la aplicación "lenguaje", formando un gran fichero.  
 * Cada instancia generará un número creciente de palabras de 10,20,30...
 * Escribiendo cada conjunto de letras en una línea distinta y con de un número
 * de letras dado por el usuario  en el momento de la ejecución. 
 */
package colaborar;

import java.util.Random;

/**
 *
 * @author juang <juangmuelas@gmail.com>
 * @since 02/11/2020
 */

public class Colaborar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
        * @param args totalInstancia integer que guarda el número a añadir
        */
        int totalInstancias = 0;
        
        
        /**
        * @param args palabras integer que guarda el número de palabras por 
        * instancia. Inicializamos en 10 para seguir el ejercicio propuesto
        */
        int palabras = 10;
        
        /**
        * @param args nuevoProceso variable de tipo Process
        */
        Process nuevoProceso;
        
        //Procedemos a añadir instancias en bloques de 10
        while (totalInstancias<10){
            Random r = new Random();
            //Añadimos una limitación para no extender la prueba
            totalInstancias = r.nextInt(50);
        }
        
        //Comprobamos que solo recibe el total a ejecutar
        if(args.length==1){
            /**
             * seguimos el código del ejemplo múltiples accesos, pero al 
             * tener ya la primera parte hecha, evitamos el paso de asegurar
             * la existencia del fichero y creamos las instancias que accederán
             * al mismo.
             */
            try{
                for (int i = 1; i <= totalInstancias; i++){
                    nuevoProceso = Runtime.getRuntime().exec("java -jar "+
                        "lenguaje.jar " + palabras + " "+ args[0]);
                    //Creamos el nuevo proceso y le indicamos el número de palabras y
                    //el fichero que debe utilizar.
                    System.out.println("Creada la instancia " + i);
                    //Mostramos en consola que hemos creado otro proceso 
                    palabras = palabras + 10; //que aumentarán de 10 en 10             
                }
            }catch (SecurityException ex){
                System.err.println("Ha ocurrido un error de Seguridad."+
                    "No se ha podido crear el proceso por falta de permisos.");
            }catch (Exception ex){
                System.err.println("Ha ocurrido un error, descripción: "+
                    ex.toString());
            }
        }else{
            System.out.println("Indique la ruta del archivo");
        }    
    }//Fin de main    
}//Fin clase Colaborar
