/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import handlers.BaseHandler;
import broker.Broker;
import handlers.*;
import java.io.EOFException;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class BrokerThread implements Runnable {

    private Broker broker;
    private boolean stop = false;
    private BrokerList brokerList;

    public BrokerThread(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void run() {
        BaseHandler h1 = new JoinHandler();
        BaseHandler h2 = new StringHandler();
        h1.setNext(h2);
        try {
            Object inputObject, outputObject;
            while (!this.stop) {
                if ((inputObject = this.broker.forwardRequest()) != null) {
//                    ResponsabilityChain vv Here vv
                    h1.handle(broker, inputObject);
                }
            }
        } catch (EOFException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        } catch (Exception e) {
            System.err.println("ThreadError");
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        } finally {
        }
    }

    public void stop() {
        this.stop = true;
    }
}
