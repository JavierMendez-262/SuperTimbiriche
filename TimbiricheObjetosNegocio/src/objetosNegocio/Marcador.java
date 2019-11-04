package objetosNegocio;

/**
 * Clase para el marcador.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Marcador {

    private int puntajes[];

    public Marcador(int [] puntajes) {
        this.puntajes = puntajes;
    }

    public int[] getPuntajes() {
        return puntajes;
    }

    public void setPuntajes(int[] puntajes) {
        this.puntajes = puntajes;
    }

}
