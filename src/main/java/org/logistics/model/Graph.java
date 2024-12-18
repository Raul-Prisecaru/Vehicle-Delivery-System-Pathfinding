package org.logistics.model;

import java.util.*;

public class Graph {

    private HashMap<Vertex, LinkedList<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Method responsible for adding Vertexes to the Hashmap
     * @param start_vertex Vertex to add to the Hashmap
     * @return None if successful, Error message otherwise
     */
    public void add_vertex(Vertex start_vertex) {

        // Adds to the hashmap if start_node does not exist
        if (adjacencyList.get(start_vertex) == null) {
            adjacencyList.put(start_vertex, new LinkedList<>());
        } else {
            // TODO: Change this to Throw instead Later On
            System.out.println("Node: " + start_vertex.get_node_value() + " Already Exists, Cannot be Added");
        }

    }


    /**
     * Method responsible for removing Vertex from the AdjacencyList
     * @param vertex (Vertex) - Vertex to remove
     */
    public  void remove_vertex(Vertex vertex) {
        Vertex exist_vertex = this.findVertex(vertex);

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
    public void add_directed_edge(Vertex start_vertex, Vertex connecting_node, int distance_weight) {
        Vertex exist_start = this.findVertex(start_vertex);
        Vertex exist_connecting = this.findVertex(connecting_node);

        if (exist_start == null) {
            System.out.println("Vertex: " + start_vertex.get_node_value() + " Does not exist");
            return;
        }

        if (exist_connecting == null) {
            System.out.println("Vertex: " + connecting_node.get_node_value() + " Does not exist");
            return;

        }
        Edge vertex_edge = new Edge(start_vertex, connecting_node, distance_weight);

        adjacencyList.get(exist_start).add(vertex_edge);


    }


    public void modify_edge(Vertex start_vertex, Vertex connecting_vertex, int new_distance, Vertex new_start_vertex, Vertex new_connecting_vertex) {
        Vertex exist_start = this.findVertex(start_vertex);
        Vertex exist_connecting = this.findVertex(connecting_vertex);
        Vertex exist_new_start = this.findVertex(new_start_vertex);
        Vertex exist_new_connecting = this.findVertex(new_connecting_vertex);

        if (exist_start == null) {
            System.out.println("Couldn't find starting vertex");
            return;
        }

        if (exist_connecting == null) {
            System.out.println("Couldn't find connecting vertex");
            return;
        }

//        Edge edge = this.findEdge(exist_start, exist_connecting);

        for (Edge edge : adjacencyList.get(exist_start)) {
            if (Objects.equals(edge.getConnecting_node().get_node_value(), exist_connecting.get_node_value())) {
                edge.setConnecting_vertex(exist_new_connecting);
                edge.setDistance_weight(new_distance);

                adjacencyList.get(start_vertex).remove(edge);

                adjacencyList.get(exist_new_start).push(edge);
            }

        }
    }


    /**
     * Method responsible for removing the Edge connecting from starting Vertex
     * @param start_vertex (Vertex) - Vertex to remove edge from
     */
    public void remove_directed_edge(Vertex start_vertex, Vertex connecting_vertex) {
        // TODO: Fix issue where it would remove every Vertex because of unspecified Connecting vertex

        Vertex exist_start = this.findVertex(start_vertex);
        Vertex exist_connecting = this.findVertex(connecting_vertex);

        if (exist_start == null) {
            System.out.println("Couldn't find starting vertex to remove edge from");
        }

        if (exist_connecting == null) {
            System.out.println("Couldn't find connecting vertex");
        }
        Edge exist_edge = this.findEdge(exist_start, connecting_vertex);

        if (exist_edge == null) {
            System.out.println("Couldn't find edge to remove");
        }

        adjacencyList.get(exist_start).remove(exist_edge);
    }

    /**
     * Method responsible for displaying the current adjacencyList
     * @return Output with current vertex and edges information
     */
    public void print_List() {
        for (Vertex vertex : adjacencyList.keySet()) {
            System.out.println("------------");
            System.out.println("Start Node: " + vertex.get_node_value());

            for (Edge edge : adjacencyList.get(vertex)) {
                System.out.println("---");
                System.out.println("Edge Distance: " + edge.getDistance_weight());
                System.out.println("Connecting Node:" + edge.getConnecting_node().get_node_value());
            }
            System.out.println("------------");
        }
    }

    /**
     * Method responsible for return the AdjacencyList
     * @return adjacencyList
     */
    public HashMap<Vertex, LinkedList<Edge>> getAdjacencyList() {
        return adjacencyList;
    }


    /**
     * Method responsible for finding appropriate vertex in the adjacencyList based on Vertex Value
     * @param vertex (Vertex) - Vertex to find in the AdjacencyList
     * @return vertex - Returns Appropriate Vertex. Otherwise null
     */
    public Vertex findVertex(Vertex vertex) {
        // TODO: Improve this to search for both so we don't have to reloop each time
//        System.out.println(vertex.get_node_value());
        for (Vertex v : adjacencyList.keySet()) {
            if (Objects.equals(v.get_node_value(), vertex.get_node_value())) {
                return v;
            }
        }
        return null;
    }


    public Edge findEdge(Vertex start_vertex, Vertex connecting_vertex) {
        for (Edge edge : adjacencyList.get(start_vertex)) {
            if (Objects.equals(edge.getConnecting_node().get_node_value(), connecting_vertex.get_node_value())) {
                return edge;
            }
        }

        return null;
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


