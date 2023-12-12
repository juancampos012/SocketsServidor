/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un hotel contiene hablutaciones y tiene la capacidad de reservar, liberar y mostrar habitaciones.
 *
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 23112023
 */
public class Hotel implements Serializable {
    /**
     * Lista de habitaciones del hotel.
     */
    private List<Habitacion> habitaciones;
    /**
     * Lector de archivo de texto.
     */
    private Lector lector;

    /**
     * Constructor para la clase `Hotel`.
     * Inicializa la lista de habitaciones y crea habitaciones utilizando el método `crearHabitacion`.
     */
    public Hotel() {
        habitaciones = new ArrayList<>();
        this.lector = new Lector();
        crearHabitacion();
    }

    /**
     * Lee valores desde una fuente externa utilizando el objeto `Lector` y crea habitaciones basadas en esos valores.
     */
    public void crearHabitacion() {
        int valor = lector.leerArchivo();
        while (valor != -1) {
            Habitacion actual = new Habitacion(valor);
            habitaciones.add(actual);
            valor = lector.leerArchivo();
        }
    }

    /**
     * Reserva una habitación con el número de habitación especificado, si aún no está reservada.
     *
     * @param numero El número de habitación a reservar.
     * @return La habitación reservada, o null si la habitación no está disponible.
     */
    public synchronized Habitacion reservarHabitacion(int numero) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero && !habitacion.isReservada()) {
                habitacion.reservar();
                return habitacion;
            }
        }
        return null;
    }

    /**
     * Libera una habitación previamente reservada con el número de habitación especificado.
     *
     * @param numero El número de habitación a liberar.
     * @return La habitación liberada, o null si la habitación no estaba reservada.
     */
    public synchronized Habitacion liberarHabitacion(int numero) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero && habitacion.isReservada()) {
                habitacion.liberar();
                return habitacion;
            }
        }
        return null;
    }

    /**
     * Recupera la lista de habitaciones en el hotel.
     *
     * @return La lista de habitaciones.
     */
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
}
