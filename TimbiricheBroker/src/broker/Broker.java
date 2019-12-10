package broker;

import interfaces.IBroker;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Broker implements IBroker{

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private String hostName;
    private int portNumber;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Broker() {
    }

    public Broker(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    public Broker(int portNumber)  {
        this.portNumber = portNumber;
    }

    public Broker(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }
    
    public ServerSocket startServerSocket() throws IOException {
        return this.serverSocket = new ServerSocket(this.portNumber);
    }

    @Override
    public void findClient() throws IOException {
        this.clientSocket = this.serverSocket.accept();

        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void findServer() throws IOException {
        this.clientSocket = new Socket(this.hostName, this.portNumber);

        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }
    
    @Override
    public Object forwardRequest() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    @Override
    public void forwardResponse(Object response) throws IOException {
        out.reset();
        out.writeObject(response);
        out.flush();
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

    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.clientSocket.close();
        if (this.serverSocket != null) {
            this.serverSocket.close();
        }
    }
}