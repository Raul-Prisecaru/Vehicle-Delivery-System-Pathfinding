package org.logistics.model;

import java.util.*;

public class Graph {
    private HashSet<DeliveryHub<String>> deliveryHubList = new HashSet<>();
    private HashSet<CustomerLocation<String>> customerLocationList = new HashSet<>();
    private HashMap<Vertex<String>, LinkedList<Edge>> adjacencyList = new HashMap<>();

    /**
     * Method Responsible for adding deliveryHub to the Hashmap
     * @param start_vertex (DeliveryHub) - DeliveryHub to add
     * @throws Exception
     */
    public void add_deliveryHub(DeliveryHub<String> start_vertex) throws Exception {
        deliveryHubList.add(start_vertex);

        // Adds to the hashmap if start_node does not exist
        if (adjacencyList.get(start_vertex) == null) {
            adjacencyList.put(start_vertex, new LinkedList<>());
        } else {
            throw new Exception("Node: " + start_vertex.getNodeValue() + " Already Exists, Cannot be Added");
        }
    }

    /**
     * Method Responsible for adding CustomerLocation to the Hashmap
     * @param start_vertex (CustomerLocation) - CustomerLocation to add
     * @throws Exception
     */
    public void add_customerLocation(CustomerLocation<String> start_vertex) throws Exception {
        customerLocationList.add(start_vertex);

        // Adds to the hashmap if start_node does not exist
        if (adjacencyList.get(start_vertex) == null) {
            adjacencyList.put(start_vertex, new LinkedList<>());
        } else {
            throw new Exception("Node: " + start_vertex.getNodeValue() + " Already Exists, Cannot be Added");
        }
    }


    /**
     * Method responsible for removing Vertex from the AdjacencyList
     * @param vertex (Vertex) - Vertex to remove
     */
    public void remove_vertex(Vertex<String> vertex) {
        Vertex<String> exist_vertex = this.findVertex(vertex);

        if (exist_vertex == null) {
            System.out.println("Couldn't find vertex to remove");
        }

        adjacencyList.remove(exist_vertex);
    }

    /**
     * Method responsible for creating edges between two vertexes with weights
     * @param start_vertex Starting Vertex
     * @param connecting_node Vertex to be connected to by Starting Vertex
     * @param distance_weight Distance between Start and Connecting Vertexes
     * @return None if successful, Error message otherwise
     */
    public void add_directed_edge(Vertex<String> start_vertex, Vertex<String> connecting_node, int distance_weight) {
        Vertex<String> exist_start = this.findVertex(start_vertex);
        Vertex<String> exist_connecting = this.findVertex(connecting_node);

        if (exist_start == null) {
            System.out.println("Vertex: " + start_vertex.getNodeValue() + " Does not exist");
            return;
        }

        if (exist_connecting == null) {
            System.out.println("Vertex: " + connecting_node.getNodeValue() + " Does not exist");
            return;

        }
        Edge vertex_edge = new Edge(start_vertex, connecting_node, distance_weight);

        adjacencyList.get(exist_start).add(vertex_edge);


    }


    public void modify_edge(Vertex<String> start_vertex, Vertex<String> connecting_vertex, int new_distance, Vertex<String> new_start_vertex, Vertex<String> new_connecting_vertex) throws Exception {
        Vertex<String> exist_start = this.findVertex(start_vertex);
        Vertex<String> exist_connecting = this.findVertex(connecting_vertex);
        Vertex<String> exist_new_start = this.findVertex(new_start_vertex);
        Vertex<String> exist_new_connecting = this.findVertex(new_connecting_vertex);

        if (exist_start == null) {
            throw new Exception("Couldn't find starting vertex");
        }

        if (exist_connecting == null) {
            throw new Exception("Couldn't find connecting vertex");
        }

        for (Edge edge : adjacencyList.get(exist_start)) {
            if (Objects.equals(edge.getConnecting_node().getNodeValue(), exist_connecting.getNodeValue())) {
                edge.setConnecting_vertex(exist_new_connecting);
                edge.setDistance_weight(new_distance);
                edge.setStart_vertex(exist_new_start);
                adjacencyList.get(exist_start).remove(edge);

                adjacencyList.get(exist_new_start).push(edge);
            }

        }
    }


    /**
     * Method responsible for removing the Edge connecting from starting Vertex
     * @param start_vertex (Vertex) - Vertex to remove edge from
     */
    public void remove_directed_edge(Vertex<String> start_vertex, Vertex<String> connecting_vertex) throws Exception {
        // TODO: Fix issue where it would remove every Vertex because of unspecified Connecting vertex

        Vertex<String> exist_start = this.findVertex(start_vertex);
        Vertex<String> exist_connecting = this.findVertex(connecting_vertex);

        if (exist_start == null) {
            throw new Exception("Couldn't find starting vertex to remove edge from");
        }

        if (exist_connecting == null) {
            throw new Exception("Couldn't find connecting vertex");
        }
        Edge exist_edge = this.findEdge(exist_start, connecting_vertex);

        if (exist_edge == null) {
            throw new Exception("Couldn't find edge to remove");
        }

        adjacencyList.get(exist_start).remove(exist_edge);
    }

    /**
     * Method responsible for displaying the current adjacencyList
     * @return Output with current vertex and edges information
     */
    public void print_List() {
        for (Vertex<String> vertex : adjacencyList.keySet()) {
            System.out.println("------------");
            System.out.println("Start Node: " + vertex.getNodeValue());

            for (Edge edge : adjacencyList.get(vertex)) {
                System.out.println("---");
                System.out.println("Edge Distance: " + edge.getDistance_weight());
                System.out.println("Connecting Node:" + edge.getConnecting_node().getNodeValue());
            }
            System.out.println("------------");
        }
    }

    /**
     * Method responsible for return the AdjacencyList
     * @return adjacencyList
     */
    public HashMap<Vertex<String>, LinkedList<Edge>> getAdjacencyList() {
        return adjacencyList;
    }


    /**
     * Method responsible for finding appropriate vertex in the adjacencyList based on Vertex Value
     * @param vertex (Vertex) - Vertex to find in the AdjacencyList
     * @return vertex - Returns Appropriate Vertex. Otherwise null
     */
    public Vertex<String> findVertex(Vertex<String> vertex) {
        for (Vertex<String> v : adjacencyList.keySet()) {
            if (Objects.equals(v.getNodeValue(), vertex.getNodeValue())) {
                return v;
            }
        }
        return null;
    }



    public Edge findEdge(Vertex<String> start_vertex, Vertex<String> connecting_vertex) {
        for (Edge edge : adjacencyList.get(start_vertex)) {
            if (Objects.equals(edge.getConnecting_node().getNodeValue(), connecting_vertex.getNodeValue())) {
                return edge;
            }
        }

        return null;
    }


    public HashSet<DeliveryHub<String>> getDeliveryHubList() {
        return deliveryHubList;
    }

//    public Boolean findVertex(Vertex vertex1, Vertex vertex2) {
//        // TODO: Improve this to search for both so we don't have to reloop each time
//        for (Vertex v : adjacencyList.keySet()) {
//            if (v.get_node_value() == vertex1.get_node_value()) {
//                return true;
//            }
//        }
//        return false;
//    }

}


