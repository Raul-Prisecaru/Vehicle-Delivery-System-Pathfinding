package org.logistics.model.algorithms;

import org.logistics.model.*;

import java.util.HashMap;
import java.util.PriorityQueue;

public class kruskal_undirected {
    private HashMap<Vertex<String>, Vertex<String>> vertexSets = new HashMap<>();
    private HashMap<Integer, Edge> weights = new HashMap<>();

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

        // Initial Stuff
        generateStartVertexesForHashMap(undirected_graph);

    }

    public Vertex<String> getPointerPair(Vertex<String> vertex) {
        return vertexSets.get(vertex);
    }

    public void getWeightsIncreasingOrder(Graph undirected_graph) {
        if (vertexSets.isEmpty()) {
            return;
        }

        PriorityQueue<Edge> orderedWeightsByComparator = new PriorityQueue();

        for (Vertex<String> vertex : vertexSets.keySet()) {
            for (Edge edge : undirected_graph.getEdges(vertex)) {
                orderedWeightsByComparator.add(edge);
            }
        }
    }

    public void generateStartVertexesForHashMap(Graph undirected_graph) {
        for (DeliveryHub<String> deliveryHub : undirected_graph.getAllDeliveryHub()) {
            vertexSets.put(deliveryHub, deliveryHub);
        }

        for (CustomerLocation<String> customerLocation : undirected_graph.getAllCustomerLocation()) {
            vertexSets.put(customerLocation, customerLocation);
        }
    }



}
