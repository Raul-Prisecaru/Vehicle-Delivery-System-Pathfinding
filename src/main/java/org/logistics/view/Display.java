package org.logistics.view;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.view.Viewer;
import org.logistics.model.CustomerLocation;
import org.logistics.model.DeliveryHub;
import org.logistics.model.Vertex;

import java.util.Scanner;

public class Display {
    private org.logistics.model.Graph graphinformation;

    public Display(org.logistics.model.Graph graph) {
        this.graphinformation = graph;
    }
    public void displayGraph() {
        Scanner scanner = new Scanner(System.in);
        System.setProperty("org.graphstream.ui", "swing");


        Graph graph = new SingleGraph("Tutorial 1");

        // TODO: Make it fancy, Customise Nodes and Edges
        graph.setAttribute("ui.stylesheet",
                "node {" +
                        "   text-alignment: under;" +
                        "   text-size: 14px;" +
                        "   text-color: black;" +
                        "}" +
                        "edge {" +
                        "   text-alignment: under;" +
                        "   text-size: 14px;" +
                        "   text-color: black;" +
                        "}");


        for (Vertex vertex : this.graphinformation.getAdjacencyList().keySet()) {
            Node node = graph.addNode(vertex.get_node_value());
            node.setAttribute("ui.label", vertex.get_node_value());
        }

        // TODO: Make this more efficient by turing nested for loop into a singular loop
        for (Vertex vertex : this.graphinformation.getAdjacencyList().keySet()) {
            for (org.logistics.model.Edge currentEdge : this.graphinformation.getAdjacencyList().get(vertex)) {

                if (currentEdge == null) {
                    break;
                }
                String id = currentEdge.getStart_node().get_node_value() + currentEdge.getConnecting_node().get_node_value();
                Edge edge = graph.addEdge(id, currentEdge.getStart_node().get_node_value(), currentEdge.getConnecting_node().get_node_value(), true);
                edge.setAttribute("ui.label", currentEdge.getDistance_weight());
            }

        }

        Viewer viewer = graph.display();
        viewer.enableAutoLayout(new SpringBox());


        // TODO: Somehow add a Unit Test for this
        while (true) {
            System.out.println("1 - Add DeliverHub Vertex");
            System.out.println("2 - Remove DeliverHub Vertex");
            System.out.println("3 - Add CustomerLocation Vertex");
            System.out.println("4 - Remove CustomerLocation Vertex");
            System.out.println("5 - Add Edge");
            System.out.println("6 - Change Edge");
            System.out.println("7 - Remove Edge");
            System.out.println("8 - Print AdjacencyList");
            System.out.print(":: ");

            int input = scanner.nextInt();

            // TODO: Bundle DeliveryHub and CustomerLocation into Add/Delete Vertex option
            if (input == 1) {
                try {
                    System.out.println("Enter DeliveryHub Value");
                    System.out.print(":: ");
                    String vertex_value = scanner.next();
                    graphinformation.add_vertex(new DeliveryHub(vertex_value));

                    Node vertex = graph.addNode(vertex_value);
                    vertex.setAttribute("ui.label", vertex_value);


                    continue;



                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 2) {
                try {
                    // code to remove DeliveryHub
                    System.out.println("Enter DeliveryHub Value");
                    System.out.print(":: ");
                    String vertex_value = scanner.next();

                    graphinformation.remove_vertex(new Vertex(vertex_value));
                    graph.removeNode(vertex_value);
                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 3) {
                try {
                    System.out.println("Enter CustomerLocation Value");
                    System.out.print(":: ");
                    String vertex_value = scanner.next();
                    graphinformation.add_vertex(new CustomerLocation(vertex_value));
                    Node vertex = graph.addNode(vertex_value);
                    vertex.setAttribute("ui.label", vertex_value);

                    continue;
                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 4) {
                try {
                    // code to remove CustomerLocation
                    System.out.println("Enter CustomerLocation Value");
                    System.out.print(":: ");
                    String vertex_value = scanner.next();

                    graphinformation.remove_vertex(new Vertex(vertex_value));
                    graph.removeNode(vertex_value);
                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 5) {
                try {
                    System.out.println("Enter Start Vertex");
                    System.out.print(":: ");
                    String start_vertex = scanner.next();

                    System.out.println("Enter connecting Vertex");
                    System.out.print(":: ");
                    String connecting_vertex = scanner.next();

                    System.out.println("Enter distance weight");
                    System.out.print(":: ");
                    int distance_weight = scanner.nextInt();

                    graphinformation.add_directed_edge(new Vertex(start_vertex), new Vertex(connecting_vertex), distance_weight);

                    Edge edge = graph.addEdge(start_vertex + "" + connecting_vertex, start_vertex, connecting_vertex, true);
                    edge.setAttribute("ui.label", distance_weight);
                    continue;


                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 6) {
                try {
                    // code to modify Edge
                    System.out.println("Enter Existing Start Vertex");
                    System.out.print(":: ");
                    String start_vertex = scanner.next();

                    System.out.println("Enter Existing connecting Vertex");
                    System.out.print(":: ");
                    String connecting_vertex = scanner.next();

                    System.out.println("Enter New Distance weight");
                    System.out.print(":: ");
                    int new_distance = scanner.nextInt();

                    System.out.println("Enter new Start Vertex");
                    System.out.print(":: ");
                    String new_start_vertex = scanner.next();

                    System.out.println("Enter new Connecting Vertex");
                    System.out.print(":: ");
                    String new_connecting_vertex = scanner.next();

//                    graphinformation.modify_edge(new Vertex(start_vertex), new Vertex(connecting_vertex), new_distance, new Vertex(new_start_vertex), new Vertex(new_connecting_vertex));

                    graph.removeEdge(start_vertex + "" + connecting_vertex);
                    Edge edge = graph.addEdge(new_start_vertex + "" + new_connecting_vertex, new_start_vertex, new_connecting_vertex, true);
                    edge.setAttribute("ui.label", new_distance);


                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 7) {
                try {
                    // code to remove Edge
                    System.out.println("Enter Start Vertex");
                    System.out.print(":: ");
                    String start_vertex = scanner.next();

                    System.out.println("Enter connecting Vertex");
                    System.out.print(":: ");
                    String connecting_vertex = scanner.next();

                    graphinformation.remove_directed_edge(new Vertex(start_vertex), new Vertex(connecting_vertex));
                    graph.removeEdge(start_vertex + "" + connecting_vertex);
                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 8) {
                graphinformation.print_List();
            }
        }

    }

}
