/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

/**
 * Clase para el marcador.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Marcador {

    private int pJ1, pJ2, pJ3, pJ4;

    public Marcador(int pJ1, int pJ2, int pJ3, int pJ4) {
        this.pJ1 = pJ1;
        this.pJ2 = pJ2;
        this.pJ3 = pJ3;
        this.pJ4 = pJ4;
    }
    
    public int[] obtenerPuntajes(){
        int[] puntajes = new int[4];
        puntajes[0] = pJ1;
        puntajes[1] = pJ2;
        puntajes[2] = pJ3;
        puntajes[3] = pJ4;
        return puntajes;
    }

    public int getpJ1() {
        return pJ1;
    }

    public void setpJ1(int pJ1) {
        this.pJ1 = pJ1;
    }

    public int getpJ2() {
        return pJ2;
    }

    public void setpJ2(int pJ2) {
        this.pJ2 = pJ2;
    }

    public int getpJ3() {
        return pJ3;
    }

    public void setpJ3(int pJ3) {
        this.pJ3 = pJ3;
    }

    public int getpJ4() {
        return pJ4;
    }

    public void setpJ4(int pJ4) {
        this.pJ4 = pJ4;
    }
}
