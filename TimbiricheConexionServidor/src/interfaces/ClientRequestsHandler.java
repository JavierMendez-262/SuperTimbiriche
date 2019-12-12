/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import broker.Broker;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public interface ClientRequestsHandler {

	public void setNext(ClientRequestsHandler next);

	public void handle(Broker broker, Object request);

}