package org.logistics.model.algorithms;

import org.logistics.model.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class Kruskal_undirected {
    private HashMap<Vertex<String>, Vertex<String>> vertexSets = new HashMap<>();
    private HashSet<Edge> weightsHashSet = new HashSet<>();
    private Graph undirected_graph;

    /**
     * Constructor
     */
    public Kruskal_undirected(Graph graph) throws Exception {
//        Graph undirected_graph = new Graph();
//
//        CustomerLocation<String> customerLocationA = new CustomerLocation<>("A");
//        CustomerLocation<String> customerLocationB = new CustomerLocation<>("B");
//        CustomerLocation<String> customerLocationC = new CustomerLocation<>("C");
//        CustomerLocation<String> customerLocationD = new CustomerLocation<>("D");
//        CustomerLocation<String> customerLocationE = new CustomerLocation<>("E");
//        CustomerLocation<String> customerLocationF = new CustomerLocation<>("F");
//        CustomerLocation<String> customerLocationG = new CustomerLocation<>("G");
//
//        undirected_graph.add_customerLocation(customerLocationA);
//        undirected_graph.add_customerLocation(customerLocationB);
//        undirected_graph.add_customerLocation(customerLocationC);
//        undirected_graph.add_customerLocation(customerLocationD);
//        undirected_graph.add_customerLocation(customerLocationE);
//        undirected_graph.add_customerLocation(customerLocationF);
//        undirected_graph.add_customerLocation(customerLocationG);
//
//        undirected_graph.add_undirected_edge(customerLocationA, customerLocationB, 2);
//        undirected_graph.add_undirected_edge(customerLocationA, customerLocationC, 3);
//        undirected_graph.add_undirected_edge(customerLocationA, customerLocationD, 3);
//
//        undirected_graph.add_undirected_edge(customerLocationB, customerLocationC, 4);
//        undirected_graph.add_undirected_edge(customerLocationB, customerLocationE, 3);
//
//        undirected_graph.add_undirected_edge(customerLocationC, customerLocationD, 5);
//        undirected_graph.add_undirected_edge(customerLocationC, customerLocationE, 1);
//
//        undirected_graph.add_undirected_edge(customerLocationD, customerLocationF, 7);
//
//        undirected_graph.add_undirected_edge(customerLocationE, customerLocationF, 8);
//        undirected_graph.add_undirected_edge(customerLocationF, customerLocationG, 9);
//



        this.undirected_graph = graph;
    }



    /**
     * Method Responsible for finding MST of the undirected Graph
     */
    public HashMap<Vertex<String>, Vertex<String>> find_MST() {
        vertexSets.clear();
        int cost = 0;

        // Initial Stuff
        generateStartVertexesForHashMap(undirected_graph);
        getWeightsIncreasingOrder(undirected_graph);

        for (Edge edge : weightsHashSet) {
            if (getPointerPair(edge.getStart_node()) != getPointerPair(edge.getConnecting_node())) {
                vertexSets.put(edge.getConnecting_node(), edge.getStart_node());
                cost += edge.getDistance_weight();
            }
        }

        for (Vertex<String> vertex : vertexSets.keySet()) {
            System.out.println(vertex + " -> " + vertexSets.get(vertex));
        }
        System.out.println("Cost: " + cost);
        return vertexSets;

    }

    public Vertex<String> getPointerPair(Vertex<String> vertex) {
        Vertex<String> findCustomer = undirected_graph.findVertexAndReturn(vertex);

        if (findCustomer != null) {
            return vertexSets.get(findCustomer);
        }

        return null;
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

        while (!orderedWeightsByComparator.isEmpty()) {
            Edge edge = orderedWeightsByComparator.poll();
            weightsHashSet.add(edge);
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
