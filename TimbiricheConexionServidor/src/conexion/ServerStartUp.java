/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique
 * Mendoza  
 */
public class ServerStartUp {

    public static final int portNumber = 777;

    public static void main(String[] args) {
        ServerConnection serverSocket = new ServerConnection(portNumber);
        Thread thread = new Thread(serverSocket);
        thread.start();
    }
}
