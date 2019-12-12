/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import broker.Broker;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class ServerConnection implements Runnable {

    private int portNumber;
    private ServerSocket serverSocket;
    private BrokerList brokerList;
    
    public ServerConnection(int portNumber) {
        this.portNumber = portNumber;
        this.brokerList = BrokerList.getInstance();
    }

    @Override
    public void run() {
        try {
            Broker broker = new Broker(portNumber);
            serverSocket = broker.startServerSocket();
            while (true) {
                broker.findClient();
                BrokerThread brokerThread = new BrokerThread(broker);
                Thread thread = new Thread(brokerThread);
                thread.start();
                
                this.brokerList.add(broker);
                broker = new Broker(serverSocket);
            }
        } catch (IOException ioe) {
            System.err.println("Excepción I/O al aceptar la conexión con el cliente");
            System.err.println(ioe.getMessage());
            System.err.println(ioe.getCause());
            System.exit(1);
        }
    }
}
