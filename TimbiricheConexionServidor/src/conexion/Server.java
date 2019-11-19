package conexion;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Server {
    private ConexionServer cS;
    
    public void conexion (){
        cS = new ConexionServer(this);
        cS.start();
    }
    
    public Object interpretar(Object obj){
        String verificar="";
        if(obj.getClass()==verificar.getClass()){
            String mensaje=obj.toString();
            if(mensaje.equalsIgnoreCase("ping")){
                return "pong";
            } else if(mensaje.equalsIgnoreCase("pong")){
                return "pingpong";
            } else if(mensaje.equalsIgnoreCase("pingpong"))
                return "conectado";
        }
        return null;
    }
}
