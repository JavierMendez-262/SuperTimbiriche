/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import broker.Broker;
import java.io.IOException;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class ClientConnection {

    private String hostName;
    private int portNumber;
    private Broker broker;

    public ClientConnection(String hostName, int portNumber) throws IOException {
        this.hostName = hostName;
        this.portNumber = portNumber;
        broker = new Broker(hostName, portNumber);
        broker.findServer();
    }

    public void sendRequest(Object request) throws IOException {
        broker.forwardResponse(request);
    }

    public Object waitResponse() throws IOException, ClassNotFoundException {
        return broker.forwardRequest();

    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPortNumber() {
        return this.portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
}
