/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import broker.Broker;
import java.util.ArrayList;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class BrokerList {

    private ArrayList<Broker> brokerList;
    private static BrokerList instance;

    private BrokerList() {
        this.brokerList = new ArrayList();
    }

    public static BrokerList getInstance() {
        if (instance == null) {
            instance = new BrokerList();
        }

        return instance;
    }

    public void add(Broker broker) {
        this.brokerList.add(broker);
    }

    public boolean remove(Broker broker) {
        return this.brokerList.remove(broker);
    }

    public ArrayList getAllBut(Broker broker) {
        ArrayList<Broker> auxBrokerList = new ArrayList();
        for (Broker brokerItem : brokerList) {
            if (!(brokerItem.equals(broker))) {
                auxBrokerList.add(brokerItem);
            }
        }
        return auxBrokerList;
    }

    public void clear() {
        this.brokerList.clear();
    }

    public boolean isEmpty() {
        return this.brokerList.isEmpty();
    }

    public ArrayList<Broker> getList() {
        return this.brokerList;
    }

    public void setList(ArrayList<Broker> BrokerList) {
        this.brokerList = BrokerList;
    }
}
