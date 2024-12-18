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
            System.out.println("2 - Add CustomerLocation Vertex");
            System.out.println("3 - Add Edge");
            System.out.println("4 - Print AdjacencyList");
            System.out.print(":: ");

            int input = scanner.nextInt();

            if (input == 1) {
                try {
                    System.out.println("Enter DeliveryHub Value");
                    System.out.print(":: ");
                    String vertex_value = scanner.next();
                    graphinformation.add_vertex(new DeliveryHub(vertex_value));

                    graph.addNode(vertex_value);
                    continue;



                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 2) {
                try {
                    System.out.println("Enter CustomerLocation Value");
                    System.out.print(":: ");
                    String vertex_value = scanner.next();
                    graphinformation.add_vertex(new CustomerLocation(vertex_value));
                    graph.addNode(vertex_value);
                    continue;
                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 3) {
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

                    Edge edge = graph.addEdge(start_vertex + "" + connecting_vertex, start_vertex, connecting_vertex);
                    edge.setAttribute("ui.label", distance_weight);
                    continue;


                } catch (Exception e) {
                    System.out.println("An Error has occurred: " + e.getMessage());
                }
            }

            if (input == 4) {
                graphinformation.print_List();
            }
        }

    }

}
