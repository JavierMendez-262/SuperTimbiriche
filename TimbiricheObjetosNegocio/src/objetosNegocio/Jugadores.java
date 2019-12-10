/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import objetosNegocio.Jugador;

/**
 *
 * @author javie
 */
public class Jugadores implements Serializable {

    private ArrayList<Jugador> jugadores;
    private static Jugadores instance;
    private int maxSize = 0;

    private Jugadores() {
        jugadores = new ArrayList<>();
    }

    public static Jugadores getInstance() {
        if (instance == null) {
            instance = new Jugadores();
        }
        return instance;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    
    public int maxSize() {
        return maxSize;
    }
    
    public boolean add(Jugador jugador) {
        if (jugadores.size() < maxSize) {
            return jugadores.add(jugador);
        }
        return false;
    }

    public void remove(Jugador jugador) {
        jugadores.remove(jugador);
    }

    public Object[] toArray() {
        return jugadores.toArray();
    }

    public Jugador get(int i) {
        return jugadores.get(i);
    }

    public int size() {
        return jugadores.size();
    }

    public void clear() {
        jugadores.clear();
    }
    
    public ArrayList<Jugador> getJugadores (){
        return jugadores;
    }

    public void update(Jugadores jugadores) {
        this.jugadores = jugadores.getJugadores();
        maxSize = jugadores.maxSize();
    }
}
