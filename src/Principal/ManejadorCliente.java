/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Se encarga de la comunicación con un cliente a través de sockets y realiza acciones en función de las opciones
 * enviadas por el cliente, como mostrar disponibilidad, reservar habitación, liberar habitación y cerrar la conexión.
 * 
 * @author juancamposbetancourth
 * @author Sebastian Garcia
 * @version 21112023
 */
public class ManejadorCliente implements Runnable {
    /**
     * El socket que representa la conexión con el cliente.
     */
    private Socket clienteSocket;
    /**
     * El objeto Hotel que contiene la lógica de reservación.
     */
    private Hotel hotel;
    /**
     * El objeto Escritor utilizado para escribir en el archivo de historial.
     */
    private Escritor escritor;

    
    /**
     * Constructor de la clase.
     * 
     * @param clienteSocket El socket que representa la conexión con el cliente.
     * @param hotel El objeto Hotel que contiene la lógica de reservación.
     */
    public ManejadorCliente(Socket clienteSocket, Hotel hotel) {
        this.clienteSocket = clienteSocket;
        this.hotel = hotel;
        this.escritor = new Escritor();
    }

     /**
     * Se ejecutará en un hilo separado al iniciar el manejador del cliente, se puede liberar, reservar o mostrar habitaciones.
     */
    @Override
    public void run() {
        try (
            ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream())
        ) {
            while (true) {
                int opcion = in.readInt();

                if(opcion == 1 || opcion == 2 || opcion == 3 || opcion == 4){
                    if(opcion == 1){
                        LinkedList<String> estados = new LinkedList<>();
                        for(Habitacion actual: hotel.getHabitaciones()){
                            estados.add(actual.toString());
                        }
                        out.writeObject(estados);
                        out.flush();
                    }else if(opcion == 2){
                        String nombreCliente = in.readUTF();
                        int numReserva = in.readInt();
                        Habitacion habitacion = hotel.reservarHabitacion(numReserva);
                        if(habitacion != null){
                            habitacion.setCliente(nombreCliente);
                            out.writeBoolean(true);
                            escritor.escribirHistorial(habitacion.toString());
                        }else{
                            out.writeBoolean(false);
                        }
                        out.flush();
                    }else if(opcion == 4){
                        int numReserva = in.readInt();
                        Habitacion habitacion = hotel.liberarHabitacion(numReserva);
                        if(habitacion != null){
                            out.writeBoolean(true);
                            escritor.escribirHistorial(habitacion.toString());
                        }else{
                            out.writeBoolean(false);
                        }
                        out.flush();
                    }else if(opcion == 3){
                        escritor.cerrarEscritor();
                        clienteSocket.close();
                    }
                }
            }
        } catch (IOException e) {
        }
    }
}
