/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Proporciona métodos para leer líneas del archivo, convertirlas a enteros y cerrar el lector del archivo.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 23112023
 */
public class Lector {
    /**
     * Ruta del archivo desde el cual se leerán los valores.
     */
    private String nombreArchivo;
    /**
     * Objeto BufferedReader utilizado para leer el archivo.
     */
    private BufferedReader lector;

    /**
     * Constructor de la clase `Lector`.
     * 
     * Inicializa la ruta del archivo y abre el lector del archivo para lectura.
     */
    public Lector() {
        this.nombreArchivo = "/Users/juancamposbetancourth/NetBeansProjects/ProyectoHotel/src/Archivos/Habitaciones.txt";
        try {
            lector = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + nombreArchivo);
        }
    }

    /**
     * Lee una línea del archivo y la convierte a un entero.
     * 
     * @return El entero leído desde el archivo, o -1 si no hay más líneas para leer.
     */
    public int leerArchivo() {
        try {
            String linea = lector.readLine();
            if (linea != null) {
                return Integer.parseInt(linea);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en el archivo - " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error de lectura en el archivo - " + nombreArchivo);
        }
        return -1;
    }

    /**
     * Cierra el lector del archivo.
     * 
     * Este método debe llamarse cuando ya no se necesite leer del archivo.
     */
    public void cerrarLector() {
        try {
            if (lector != null) {
                lector.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el lector del archivo - " + nombreArchivo);
        }
    }
}
