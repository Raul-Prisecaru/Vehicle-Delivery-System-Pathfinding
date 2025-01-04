package org.logistics.model.algorithms;

import org.logistics.model.*;

import java.util.*;


public class Kruskal_undirected {
    private HashMap<Vertex<String>, Vertex<String>> vertexSets = new HashMap<>();
    private PriorityQueue<Edge> weightsHashSet = new PriorityQueue<>(Comparator.comparingInt(Edge::getDistance_weight));
    private Graph undirected_graph;

    /**
     * Constructor
     */
    public Kruskal_undirected(Graph graph) throws Exception {
        this.undirected_graph = graph;
    }

    /**
     * Method Responsible for finding the parent of the provided Child Vertex
     * @param childVertex (vertex) - Child Vertex to find Parent Vertex
     * @return vertex - Parent Vertex of Child Vertex
     */
    public Vertex<String> find(Vertex<String> childVertex) throws Exception {
        Vertex<String> foundVertex = undirected_graph.findVertexAndReturn(childVertex);

        if (foundVertex != null) {
            childVertex = foundVertex;
        } else {
            throw new Exception(childVertex.getNodeValue() + " was not Found in the graph");
        }

        if (!vertexSets.get(childVertex).equals(childVertex)) {
            return find(vertexSets.get(childVertex));
        }
        return vertexSets.get(childVertex);
    }

    /** Method Responsible for connecting vertexes in the same group
     * @param childVertex (Vertex) - Child Vertex to connect with Parent Vertex
     * @param parentVertex (Vertex) - Parent Vertex to connect with Child Vertex
     */
    public void union(Vertex<String> childVertex, Vertex<String> parentVertex) {
        vertexSets.put(childVertex, parentVertex);
    }

    /**
     * Method Responsible for finding MST of the undirected Graph
     */
    public HashMap<Vertex<String>, Vertex<String>> find_MST() throws Exception {
        vertexSets.clear();
        int cost = 0;

        // Initial Stuff
        generateStartVertexesForHashMap();
        getWeightsIncreasingOrder();

        for (Edge edge : weightsHashSet) {
            Vertex<String> childVertex = find(edge.getStart_node());
            Vertex<String> parentVertex = find(edge.getConnecting_node());

            if (!childVertex.equals(parentVertex)) {
                union(edge.getStart_node(), edge.getConnecting_node());
                cost += edge.getDistance_weight();
                System.out.println(edge.getStart_node() + " -> " + edge.getConnecting_node());
            }
        }

        System.out.println("Cost: " + cost);
        return vertexSets;
    }

    /**
     * Method Responsible for taking the edges from graph and sorting them in ascending order.
     */
    // TODO: Might be best to perhaps apply a sorting algorithm instead?
    public void getWeightsIncreasingOrder() {
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

    /**
     * Method Responsible for taking vertexes from the graph
     * and adding to the Parent HashMap while vertexes are connected to itself
     */
    public void generateStartVertexesForHashMap() {
    for (DeliveryHub<String> deliveryHub : undirected_graph.getAllDeliveryHub()) {
        vertexSets.put(deliveryHub, deliveryHub);
    }

    for (CustomerLocation<String> customerLocation : undirected_graph.getAllCustomerLocation()) {
        vertexSets.put(customerLocation, customerLocation);
    }
    }





}
