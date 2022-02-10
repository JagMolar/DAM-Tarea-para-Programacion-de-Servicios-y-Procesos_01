/*
 * TAREA PARA PSP01 EJERCICIO 1.
 * SEGUNDA PARTE:
 * Implementa una aplicación llamada 'aleatorios', que genere al menos 40 números 
 * aleatorios (entre 0 y 100), y que los escriba en su salida estándar. 
 */
package aleatorios;

import java.util.Random;



/**
 *
 * @author juang <juangmuelas@gmail.com>
 * @since 28/10/2020
 */
public class Aleatorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
         * Necesitamos facilitarle limites. 
         * @param limite cantidad de valores a retornar
         */
        int limite = 0;
        
        //Usamos Random para reoger enteros al azar.
        Random a = new Random();
        
        //Aseguramos que no tenga menos de 40 valores
        while (limite<40){
            
            /**
            * El ejercicio no da un límite superior, pero le ponemos uno para no 
            * alargar mucho la ejecución
            * @param limite cantidad de valores a retornar
            */
            limite = a.nextInt(100);
        }        
        // guardamos el dato
        int[] aleatorios = new int[limite];
        
        //Con el límite impuesto, procedemos a recoger los valores.
        for (int i=0; i<aleatorios.length; i++){
            //Volvemos a aprevechar Random para los valores.
            Random numeros = new Random();
            /**
            * El ejercicio pide un valor máximo
            * @param valores , valor limite de 100
            */
            int valores = numeros.nextInt(100);
            aleatorios[i] = valores;
        }       
        
        for (int v=0; v<aleatorios.length; v++){
            //Recorremos y para mostrarlo mejor, añadimos un espacio entre los valores.
            System.out.print(aleatorios[v] + " ");          
        }
        
        //Añado un salto de línea para mejorar un poco la visibilidad.
        System.out.println("\n");
    } // Fin main
} // Fin clase Aleatorios
    

