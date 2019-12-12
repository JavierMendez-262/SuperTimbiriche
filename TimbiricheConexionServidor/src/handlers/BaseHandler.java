/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import broker.Broker;
import interfaces.ClientRequestsHandler;
import java.util.ArrayList;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class BaseHandler implements ClientRequestsHandler {

    protected ClientRequestsHandler next;
    protected ArrayList<Broker> brokerList;

    public BaseHandler() {
    }

    @Override
    public void setNext(ClientRequestsHandler next) {
        this.next = next;
    }

    @Override
    public void handle(Broker broker, Object request) {
    }

}
