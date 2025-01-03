package org.logistics.model.algorithms;

import org.logistics.model.CustomerLocation;
import org.logistics.model.DeliveryHub;
import org.logistics.model.Graph;
import org.logistics.model.Vertex;

import java.util.HashMap;
import java.util.HashSet;

public class kruskal_undirected {
    private HashMap<Vertex<String>, Vertex<String>> vertexSets = new HashMap<>();

    /**
     * Constructor
     */
    public kruskal_undirected() {

    }

    /**
     * Method Responsible for finding MST of the undirected Graph
     */
    public void find_MST(Graph undirected_graph) {
        vertexSets.clear();

        for (DeliveryHub<String> deliveryHub : undirected_graph.getAllDeliveryHub()) {
            vertexSets.put(deliveryHub, deliveryHub);
        }

        for (CustomerLocation<String> customerLocation : undirected_graph.getAllCustomerLocation()) {
            vertexSets.put(customerLocation, customerLocation);
        }



    }

    public Vertex<String> getPointerPair() {
        return null;
    }



}
