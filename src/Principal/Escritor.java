/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Permite la escritura de líneas en un archivo específico y proporciona un método
 * para cerrar el escritor del archivo cuando ya no es necesario.
 * 
 * @author juancamposbetancourth
 * @version 23112023
 */
public class Escritor {
    /**
     * Objeto BufferedWriter utilizado para escribir en el archivo.
     */
    private BufferedWriter escritor;

    /**
     * Constructor de la clase `Escritor`.
     * 
     * Intenta abrir un archivo de historial para escritura.
     * Si el archivo no existe, se creará; si existe, se abrirá en modo de anexar.
     */
    public Escritor() {
        try {
            escritor = new BufferedWriter(new FileWriter("/Users/juancamposbetancourth/NetBeansProjects/ProyectoHotel/src/Archivos/Historial.txt", true));
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado.");
        }
    }

    /**
     * Escribe una línea en el archivo de historial.
     * 
     * @param line La línea que se va a escribir en el archivo.
     */
    public void escribirHistorial(String line) {
        try {
            escritor.write(line);
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("Error de escritura.");
        }
    }

    /**
     * Cierra el escritor del archivo.
     * 
     * Este método debe llamarse cuando ya no se necesite escribir en el archivo.
     */
    public void cerrarEscritor() {
        try {
            if (escritor != null) {
                escritor.close();
            }
        } catch (IOException e) {
            // Puedes imprimir un mensaje aquí o considerar lanzar la excepción
            System.err.println("Error al cerrar el escritor del archivo.");
        }
    }
}
