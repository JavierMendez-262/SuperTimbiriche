package interfaces;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public interface IConexion {
    void conexion ();
    //Envía mensajes
    public void enviar(String obj);
    //Recibe datos y los procesa
    public void interpretar(Object obj);
    //Si el juego termina
    public void ganar(String maybe);
    //Continue
    public void continuar(String cont);
    //Checa la conexión
    public void conectado();
}
