package objetosNegocio;

import java.io.Serializable;

/**
 * Clase para crear líneas.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Linea extends Forma implements Serializable{
    
    public Linea(boolean tomado, Jugador owner){
        super(tomado, owner);
    }   
    
}
