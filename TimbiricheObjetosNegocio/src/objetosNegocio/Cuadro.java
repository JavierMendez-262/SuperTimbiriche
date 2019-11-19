package objetosNegocio;

/**
 * Clase que crea cuadros.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Cuadro extends Forma{
    
    public Cuadro (){}
    
    public Cuadro(boolean tomado, Jugador owner) {
        super(tomado, owner);
    }   
}