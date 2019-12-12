/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import broker.Broker;
import conexion.BrokerList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.Jugadores;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique
 * Mendoza
 */
public class StringHandler extends BaseHandler {

    public void handle(Broker broker, Object request) {
        if (request.getClass() == String.class) {
            if (((String) request).contains("tamanio")) {
                String size = (String) request;
                Jugadores.getInstance().setMaxSize(Integer.parseInt(size.substring(size.indexOf("|") + 1)));
            } else if (((String) request).contains("listo")) {
                String size = (String) request;
                Jugadores.getInstance().get(Integer.parseInt(size.substring(size.indexOf("|") + 1))).setListo(true);

                for (int i = 0; i < Jugadores.getInstance().size(); i++) {
                    System.out.println(Jugadores.getInstance().get(i).isListo());
                }
                
                super.brokerList = BrokerList.getInstance().getList();
                try {
                    for (Broker brokerItem : brokerList) {
                        brokerItem.forwardResponse(Jugadores.getInstance());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JoinHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if(((String) request).contains("pintar")) {
                super.brokerList = BrokerList.getInstance().getList();
                try {
                    for (Broker brokerItem : brokerList) {
                        brokerItem.forwardResponse(((String) request));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JoinHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        } else if (request.getClass() == int[].class) {
            super.brokerList = BrokerList.getInstance().getList();
                try {
                    for (Broker brokerItem : brokerList) {
                        brokerItem.forwardResponse(request);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JoinHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else {
            super.next.handle(broker, request);
        }
    }
}
