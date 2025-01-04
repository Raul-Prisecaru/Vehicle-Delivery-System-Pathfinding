package org.logistics.model.algorithms;

import org.logistics.model.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class kruskal_undirected {
    private HashMap<Vertex<String>, Vertex<String>> vertexSets = new HashMap<>();
    private HashSet<Edge> weights = new HashSet<>();
    private Graph undirected_graph;

    /**
     * Constructor
     */
    public kruskal_undirected() throws Exception {
        Graph undirected_graph = new Graph();

        undirected_graph.add_customerLocation(new CustomerLocation<>("A"));
        undirected_graph.add_customerLocation(new CustomerLocation<>("B"));
        undirected_graph.add_customerLocation(new CustomerLocation<>("C"));

        undirected_graph.add_undirected_edge(new CustomerLocation<>("A"), new CustomerLocation<>("B"), 1);
        undirected_graph.add_undirected_edge(new CustomerLocation<>("A"), new CustomerLocation<>("C"), 2);
        undirected_graph.add_undirected_edge(new CustomerLocation<>("B"), new CustomerLocation<>("C"), 3);


        this.undirected_graph = undirected_graph;
    }



    /**
     * Method Responsible for finding MST of the undirected Graph
     */
    public void find_MST(Graph undirected_graph) {
        vertexSets.clear();

        // Initial Stuff
        generateStartVertexesForHashMap(undirected_graph);
        getWeightsIncreasingOrder(undirected_graph);

        for (Integer integer : weights.keySet()) {
            for (Edge edge : weights.values()) {

            }

        }

    }

    public Vertex<String> getPointerPair(Vertex<String> vertex) {
        return vertexSets.get(vertex);
    }

    // TODO: Might be best to perhaps apply a sorting algorithm instead?
    public void getWeightsIncreasingOrder(Graph undirected_graph) {
        if (vertexSets.isEmpty()) {
            return;
        }

        PriorityQueue<Edge> orderedWeightsByComparator = new PriorityQueue<>(Comparator.comparingInt(Edge::getDistance_weight));

        for (Vertex<String> vertex : vertexSets.keySet()) {
            for (Edge edge : undirected_graph.getEdges(vertex)) {
                orderedWeightsByComparator.add(edge);
            }
        }

        for (Edge edge : orderedWeightsByComparator) {
            weights.add(edge);
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
