/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import broker.Broker;
import conexion.BrokerList;
import objetosNegocio.Jugador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.Jugadores;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class JoinHandler extends BaseHandler {
    
    private Jugadores jugadores;

    public JoinHandler() {
        jugadores = Jugadores.getInstance();
    }

    public void handle(Broker broker, Object request) {
        if (request.getClass() == Jugador.class) {
            Jugador jugador = (Jugador) request;
            jugadores.add(jugador);
            
            int[] turnos = null;
            if (jugadores.size() == jugadores.maxSize()) {
                turnos  = sortearTurnos(jugadores.maxSize());
            }
            
            super.brokerList = BrokerList.getInstance().getList();
            
            try {
                for (Broker brokerItem : brokerList) {
                    brokerItem.forwardResponse(jugadores);
                    if (turnos != null) {
                        brokerItem.forwardResponse(turnos);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(JoinHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            super.next.handle(broker, request);
        }
    }
    
    public int[] sortearTurnos(int tamanio){
        int[] turnos;
        List<Integer> num = new ArrayList<>(tamanio);
        for (int i = 0; i < tamanio; i++) {
            num.add(i);
        }
        Random random = new Random();
        turnos = new int[tamanio];

        int i = 0;
        while (num.size() >= 1) {
            int randomIndex = random.nextInt(num.size());
            turnos[i] = num.get(randomIndex);
            num.remove(randomIndex);
            i++;
        }
        return turnos;
    }

}
