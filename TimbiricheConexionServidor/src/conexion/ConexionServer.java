package conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class ConexionServer extends Thread{
    private Socket socketCliente;
    private ServerSocket socketServer;
    private ObjectInputStream flujoEntrada;
    Server s;

    public ConexionServer (Server s){
        this.s = s;
        try {
            socketServer = new ServerSocket(777);
            System.out.println("Servidor listo. En espera de jugadores.");
        } catch(IOException ex ){
            System.err.println("No hay conexión en el puerto: 777.");
            System.exit(1);
        }
        s.interpretar("ping");
    }
        
    @Override
    public void run(){
        while(true){
            Object input=null;
            try{
                socketCliente = socketServer.accept();
                System.out.println("Un jugador ha ingresado.");
                flujoEntrada = new ObjectInputStream(socketCliente.getInputStream());
                input = flujoEntrada.readObject();
                socketCliente.close();
            }catch(IOException | ClassNotFoundException ex){
                System.err.println("No hay conexión en el puerto: 777.");
                System.exit(1);
            }
        }
    }
}
