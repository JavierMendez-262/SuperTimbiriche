package interfaces;

import objetosNegocio.Marcador;

/**
 * Interface para el marcador.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public interface IMarcador {
    //Recibe la puntuación y la modifica en el tablero
    public void puntajes();
    public void setMarcador(Marcador marcador);
}
