package conexion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class ConexionCliente {
    Socket timbSocket = null;
    private ObjectOutputStream flujoSalida;
    String ip;
    private Object salida;
    
    public ConexionCliente (String ip){
        this.ip = ip;
    }

    public void conexion (){
        try {
            timbSocket = new Socket(ip, 777);
            flujoSalida = new ObjectOutputStream(timbSocket.getOutputStream());
            flujoSalida.writeObject(salida);
            timbSocket.close();            
        } catch (IOException ex) {
            System.err.println("No se pudo alcanzar la conexión con el Servidor.");
            System.exit(1);
        }
    }
}
