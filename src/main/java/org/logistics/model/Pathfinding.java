package org.logistics.model;

import java.util.*;

public class Pathfinding {
    private HashMap<Vertex, LinkedList<Edge>> adjacencyList;

    //TODO: Provide better description for the Constructor JavaDoc
    /**
     * Constructor responsible for setting the graph environment
     * @param graph (Graph) | Graph Environment
     */
    public Pathfinding(Graph graph) {
        this.adjacencyList = graph.getAdjacencyList();
    }

    /**
     * Method Responsible for finding the shortest path between current vehicle position and package destination
     * @param vehicle (Vehicle) | used to find shortest path between current vehicle location and packages it contains destinations
     */
    public HashMap<Vertex, Integer> find_shortest_path(Vehicle vehicle) {
        // TODO: Put the Vertex and distance in a hash table
        HashMap <Vertex, Integer> shortestPath = new HashMap<>();
        if (!vehicle.get_deliveryPackages().isEmpty()) {

            Vertex start_vertex = vehicle.getCurrent_vertex();
            start_vertex.set_distance(0);

            HashMap<Vertex, Vertex> predecessor = new HashMap<>();

            PriorityQueue<Vertex> unvisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::get_distance));
            Queue<Vertex> visited = new LinkedList<>();

            unvisited.add(start_vertex);
            predecessor.put(start_vertex, null);

            while (!unvisited.isEmpty()) {
                Vertex current = unvisited.poll();
                if (!visited.contains(current)) {
                    for (Edge edge : adjacencyList.get(current)) {
                        int totalDistance = current.get_distance() + edge.getDistance_weight();

                        if (totalDistance < edge.getConnecting_node().get_distance()) {
                            edge.getConnecting_node().set_distance(totalDistance);
                            unvisited.remove(edge.getConnecting_node());
                            unvisited.add(edge.getConnecting_node());

                            predecessor.remove(edge.getConnecting_node(), current);
                            predecessor.put(edge.getConnecting_node(), current);
                        }


                    }
                    unvisited.remove(current);
                    visited.add(current);

                }
            }
            System.out.println("-- Final Destination --");
            // TODO: Modify this to get output from Hash Table
            for (Vertex vertex : visited) {
                if (vertex == vehicle.get_deliveryPackages().peek().getDestination()) {
                    System.out.println("Start Location: " + vehicle.getCurrent_vertex());
                    System.out.println("Destination: " + vertex);
                    System.out.println("Distance: " + vertex.get_distance());
                    shortestPath.put(vertex, vertex.get_distance());

                }
            }

            System.out.println("------");
            System.out.println("Predecessor");

            Vertex endVertex = vehicle.get_deliveryPackages().peek().getDestination();
            vehicle.addTravelDestination(endVertex);
            for (Vertex vertex : predecessor.keySet()) {
                if (predecessor.get(endVertex) != null) {
                    vehicle.addTravelDestination(predecessor.get(endVertex));
                    endVertex = predecessor.get(endVertex);
                } else {
                    vehicle.addTravelDestination(vehicle.getCurrent_vertex());
                    break;
                }

                System.out.println("Vertex: " + vertex);
                System.out.println("predecessor: " + predecessor.get(vertex));
                System.out.println("-------");
            }

            System.out.println(vehicle.getTravelDestinations());
        } else {
            System.out.println("Vehicle does not contain any package");
        }
        return shortestPath;
    }


    // TODO: Idea: Method to check which vehicle is closest to High priority packages

}
