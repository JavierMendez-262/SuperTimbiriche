/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import conexion.ClientConnection;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.Jugador;
import objetosNegocio.Jugadores;
import presentacion.Sala;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique
 * Mendoza
 */
public class ClientProxy implements Runnable {

    private ClientConnection clientConnection;
    private String hostName;
    private int portNumber;
    private Thread thread;
    private Sala sala;

    public ClientProxy(String hostName, int portNumber, Sala sala) throws IOException {
        this.hostName = hostName;
        this.portNumber = portNumber;
        clientConnection = new ClientConnection(hostName, portNumber);
        this.sala = sala;
        this.esperarNotificacion();
    }

    public void establecerTamanio(int tamanio) throws IOException {
        clientConnection.sendRequest("tamanio|" + tamanio);
    }

    public void unirSala(Jugador jugador) throws IOException {
        clientConnection.sendRequest(jugador);
    }

    public void listo(int noJugador) throws IOException {
        clientConnection.sendRequest("listo|" + noJugador);
    }
    
    public void pintarLinea (int noJugador, String x, String y) throws IOException{
        clientConnection.sendRequest("pintar," + noJugador + "," + x + "," + y);
    }
    
    public void establecerTurnos (int[] turnos) throws IOException{
        clientConnection.sendRequest(turnos);
    }

    public void esperarNotificacion() {
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Object inputObject;
        while (true) {
            try {
                if ((inputObject = clientConnection.waitResponse()) != null) {
                    if (inputObject.getClass() == Jugadores.class) {
                        Jugadores jugadores = Jugadores.getInstance();
                        jugadores.update((Jugadores) inputObject);
                        sala.entradaJugador();
                    }else if(inputObject.getClass() == int[].class) {
                        sala.setTurnos((int[])inputObject);
                    } else if (inputObject.getClass() == String.class) {
                        sala.movimiento((String) inputObject);
                    } else {
                        System.err.println("Input Not Recognized");
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
