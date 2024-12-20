package org.logistics.view;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.logistics.model.CustomerLocation;
import org.logistics.model.DeliveryHub;
import org.logistics.model.Vehicle;
import org.logistics.model.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Display {
    private org.logistics.model.Graph graphinformation;
    Scanner scanner = new Scanner(System.in);
    Graph graph = new SingleGraph("Tutorial 1");


    public Display(org.logistics.model.Graph graph) {
        this.graphinformation = graph;
    }

    public void createGraph() {
        System.setProperty("org.graphstream.ui", "swing");



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


    }

    public void displayGUI(){
        JFrame frame = new JFrame("GraphStream");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.swing_viewer.SwingViewer");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        viewer.enableAutoLayout(new SpringBox());

        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        frame.add(viewPanel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
//        Viewer viewer = graph.display();
//        viewer.enableAutoLayout(new SpringBox());
    }

    public void add_deliveryHub() {

    }

    public void add_customerLocation() {

    }

    public void remove_deliveryHub() {

    }

    public void remove_customerLocation() {

    }

    public void add_edge() {

    }

    public void modify_edge() {

    }

    public void remove_edge() {

    }

    public void print_adjacencyList() {

    }

    public void dynamic_options() {
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

                    // TODO: An Error has occurred: Cannot invoke "java.util.LinkedList.remove(Object)" because the return value of "java.util.HashMap.get(Object)" is null
                    graphinformation.modify_edge(new Vertex(start_vertex), new Vertex(connecting_vertex), new_distance, new Vertex(new_start_vertex), new Vertex(new_connecting_vertex));

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


    /**
     * Method responsible for highlighting the current vertex which the vehicle is currently on
     * @param vertex (Vertex) - Vertex to highlight
     * @param OnOff (int) - 0 to turn off highlight, 1 to turn on highlight
     */
    public void visualise_vehicle(Vertex vertex, int OnOff) {
        Node current_Node = graph.getNode(vertex.get_node_value());

        if (OnOff == 0) {
            current_Node.setAttribute("ui.style", "fill-color: black;");
        }

        if (OnOff == 1) {
            current_Node.setAttribute("ui.style", "fill-color: red;");

        }




    }

}
