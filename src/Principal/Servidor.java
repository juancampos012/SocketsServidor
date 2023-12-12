/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Espera conexiones de clientes en un puerto específico, y por cada conexión aceptada,
 * crea un nuevo hilo (ManejadorCliente) para manejar la comunicación con ese cliente.
 * Utiliza la clase ServerSocket para escuchar las conexiones entrantes.
 * 
 * @author juancamposbetancourth
 * @version 21132023
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            int puerto = 9876;
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            Hotel hotel = new Hotel();

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                Thread t = new Thread(new ManejadorCliente(clienteSocket, hotel));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
