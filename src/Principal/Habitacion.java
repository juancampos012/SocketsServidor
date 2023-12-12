/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.Serializable;

/**
 * Incluye información sobre el número de habitación, su estado de reserva,
 * y el nombre del huésped si la habitación está reservada.
 * 
 * @author juancamposbetancourth
 * @version 21112023
 */
public class Habitacion implements Serializable {
    /**
     * Número de la habitación.
     */
    private int numero;
    /**
     * Indica si la habitación está reservada (true) o disponible (false).
     */
    private boolean reservada; 
    /**
     * Nombre del huésped que ha reservado la habitación.
     */
    private String nombreHuesped;

    /**
     * Constructor de la clase Habitacion.
     * 
     * @param numero El número de la habitación.
     */
    public Habitacion(int numero) {
        this.numero = numero;
        this.reservada = false;
    }

    /**
     * Obtiene el número de la habitación.
     * 
     * @return El número de la habitación.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Verifica si la habitación está reservada.
     * 
     * @return true si la habitación está reservada, false si está disponible.
     */
    public boolean isReservada() {
        return reservada;
    }

    /**
     * Reserva la habitación, estableciendo el estado como reservada.
     */
    public void reservar() {
        reservada = true;
    }
    
    /**
     * Libera la habitación, estableciendo el estado como disponible.
     */
    public void liberar() {
        reservada = false;
    }
    
    /**
     * Representación en cadena de la habitación.
     * 
     * @return Una cadena que representa la habitación, incluyendo su número y estado.
     */
    @Override
    public String toString() {
        if(reservada){
            return "Habitacion #" + numero + " - Reservada a nombre de: " + nombreHuesped;
        } else {
            return "Habitacion #" + numero + " - Disponible";
        }  
    }

    /**
     * Obtiene el nombre del huésped que ha reservado la habitación.
     * 
     * @return El nombre del huésped.
     */
    public String getCliente() {
        return nombreHuesped;
    }

    /**
     * Establece el nombre del huésped que ha reservado la habitación.
     * 
     * @param cliente El nombre del huésped a establecer.
     */
    public void setCliente(String cliente) {
        this.nombreHuesped = cliente;
    }
}
