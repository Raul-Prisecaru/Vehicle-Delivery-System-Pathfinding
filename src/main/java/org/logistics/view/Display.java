package org.logistics.view;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.logistics.model.CustomerLocation;
import org.logistics.model.DeliveryHub;
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
                        "   fill-mode: image-scaled;" +
                        "   size: 30px, 30px;" +
                        "}" +
                        "edge {" +
                        "   text-alignment: under;" +
                        "   text-size: 14px;" +
                        "   text-color: black;" +
                        "}");


        for (Vertex<String> vertex : this.graphinformation.getAdjacencyList().keySet()) {
            Node node = graph.addNode(vertex.getNodeValue());
            node.setAttribute("ui.label", vertex.getNodeValue());
            node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");
        }

        // TODO: Make this more efficient by turing nested for loop into a singular loop
        for (Vertex<String> vertex : this.graphinformation.getAdjacencyList().keySet()) {
            for (org.logistics.model.Edge currentEdge : this.graphinformation.getAdjacencyList().get(vertex)) {

                if (currentEdge == null) {
                    break;
                }

                String id = currentEdge.getStart_node().getNodeValue() + currentEdge.getConnecting_node().getNodeValue();
                Edge edge = graph.addEdge(id, currentEdge.getStart_node().getNodeValue(), currentEdge.getConnecting_node().getNodeValue(), true);
                edge.setAttribute("ui.label", currentEdge.getTime_weight());

            }

        }


    }

    public void updateEdge(org.logistics.model.Edge edge_edge) {
        graph.removeEdge(edge_edge.getStart_node().getNodeValue() + "" + edge_edge.getConnecting_node().getNodeValue());
        Edge edge = graph.addEdge(edge_edge.getStart_node().getNodeValue() + "" + edge_edge.getConnecting_node().getNodeValue(), edge_edge.getStart_node().getNodeValue(), edge_edge.getConnecting_node().getNodeValue(), true);
        edge.setAttribute("ui.label", edge_edge.getTime_weight());
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

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10)); // Grid layout for buttons

        JButton addDeliverHubButton = new JButton("Add DeliverHub Vertex");
        addDeliverHubButton.addActionListener(e -> add_deliveryHub());

        JButton removeDeliverHubButton = new JButton("Remove DeliverHub Vertex");
        removeDeliverHubButton.addActionListener(e -> remove_deliveryHub());

        JButton addCustomerLocationButton = new JButton("Add CustomerLocation Vertex");
        addCustomerLocationButton.addActionListener(e -> add_customerLocation());

        JButton removeCustomerLocationButton = new JButton("Remove CustomerLocation Vertex");
        removeCustomerLocationButton.addActionListener(e -> remove_customerLocation());

        JButton addEdgeButton = new JButton("Add Edge");
        addEdgeButton.addActionListener(e -> add_edge());

        JButton modifyEdgeButton = new JButton("Change Edge");
        modifyEdgeButton.addActionListener(e -> modify_edge());

        JButton removeEdgeButton = new JButton("Remove Edge");
        removeEdgeButton.addActionListener(e -> remove_edge());

        JButton printAdjacencyListButton = new JButton("Print AdjacencyList");
        printAdjacencyListButton.addActionListener(e -> print_adjacencyList());

        buttonPanel.add(addDeliverHubButton);
        buttonPanel.add(removeDeliverHubButton);
        buttonPanel.add(addCustomerLocationButton);
        buttonPanel.add(removeCustomerLocationButton);
        buttonPanel.add(addEdgeButton);
        buttonPanel.add(modifyEdgeButton);
        buttonPanel.add(removeEdgeButton);
        buttonPanel.add(printAdjacencyListButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);


        frame.setSize(1200, 800);
        frame.setVisible(true);
//        Viewer viewer = graph.display();
//        viewer.enableAutoLayout(new SpringBox());
    }

    public void add_deliveryHub() {
        try {
            System.out.println("Enter DeliveryHub Value");
            System.out.print(":: ");
            String vertex_value = scanner.next();
            graphinformation.add_deliveryHub(new DeliveryHub<>(vertex_value));

            Node vertex = graph.addNode(vertex_value);
            vertex.setAttribute("ui.label", vertex_value);
            vertex.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }

    public void add_deliveryHub(DeliveryHub<String> deliveryHub) {
        try {
            String vertex_value = deliveryHub.getNodeValue();
            graphinformation.add_deliveryHub(new DeliveryHub<>(vertex_value));

            Node vertex = graph.addNode(vertex_value);
            vertex.setAttribute("ui.label", vertex_value);
            vertex.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }



    public void add_customerLocation() {
        try {
            System.out.println("Enter CustomerLocation Value");
            System.out.print(":: ");
            String vertex_value = scanner.next();
            graphinformation.add_customerLocation(new CustomerLocation<>(vertex_value));
            Node vertex = graph.addNode(vertex_value);
            vertex.setAttribute("ui.label", vertex_value);
            vertex.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");



        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }

    public void add_customerLocation(CustomerLocation<String> customerLocation) {
        try {
            String vertex_value = customerLocation.getNodeValue();
            graphinformation.add_customerLocation(new CustomerLocation<>(vertex_value));
            Node vertex = graph.addNode(vertex_value);
            vertex.setAttribute("ui.label", vertex_value);
            vertex.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }


    public void remove_deliveryHub() {
        try {
            // code to remove DeliveryHub
            System.out.println("Enter DeliveryHub Value");
            System.out.print(":: ");
            String vertex_value = scanner.next();

            graphinformation.remove_vertex(new Vertex<>(vertex_value));
            graph.removeNode(vertex_value);
        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }

    public void remove_deliveryHub(DeliveryHub<String> deliveryHub) {
        try {
            // code to remove DeliveryHub
            String vertex_value = deliveryHub.getNodeValue();

            graphinformation.remove_vertex(new Vertex<>(vertex_value));
            graph.removeNode(vertex_value);
        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }



    public void remove_customerLocation() {
        try {
            // code to remove CustomerLocation
            System.out.println("Enter CustomerLocation Value");
            System.out.print(":: ");
            String vertex_value = scanner.next();

            graphinformation.remove_vertex(new Vertex<>(vertex_value));
            graph.removeNode(vertex_value);
        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }

    public void remove_customerLocation(CustomerLocation<String> customerLocation) {
        try {
            // code to remove CustomerLocation
            String vertex_value = customerLocation.getNodeValue();

            graphinformation.remove_vertex(new Vertex<>(vertex_value));
            graph.removeNode(vertex_value);
        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }



    public void add_edge() {
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

            graphinformation.add_directed_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex), distance_weight);

            Edge edge = graph.addEdge(start_vertex + "" + connecting_vertex, start_vertex, connecting_vertex, true);
            edge.setAttribute("ui.label", distance_weight);


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }

    public void add_edge(org.logistics.model.Edge edge_edge) {
        try {
            String start_vertex = edge_edge.getStart_node().getNodeValue();

            String connecting_vertex = edge_edge.getConnecting_node().getNodeValue();

            int distance_weight = edge_edge.getDistance_weight();

            graphinformation.add_directed_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex), distance_weight);

            Edge edge = graph.addEdge(start_vertex + "" + connecting_vertex, start_vertex, connecting_vertex, true);
            edge.setAttribute("ui.label", distance_weight);


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }



    public void modify_edge() {
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
            graphinformation.modify_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex), new_distance, new Vertex<>(new_start_vertex), new Vertex<>(new_connecting_vertex));

            graph.removeEdge(start_vertex + "" + connecting_vertex);
            Edge edge = graph.addEdge(new_start_vertex + "" + new_connecting_vertex, new_start_vertex, new_connecting_vertex, true);
            edge.setAttribute("ui.label", new_distance);


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }


    public void modify_edge(org.logistics.model.Edge edge_edge, Vertex<String> newStart, Vertex<String> newConnecting, int newDistance) {
        try {
            // code to modify Edge
            String start_vertex = edge_edge.getStart_node().getNodeValue();

            String connecting_vertex = edge_edge.getConnecting_node().getNodeValue();

            int new_distance = edge_edge.getDistance_weight();

            String new_start_vertex = newStart.getNodeValue();

            String new_connecting_vertex = newConnecting.getNodeValue();

            // TODO: An Error has occurred: Cannot invoke "java.util.LinkedList.remove(Object)" because the return value of "java.util.HashMap.get(Object)" is null
            graphinformation.modify_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex), newDistance, new Vertex<>(new_start_vertex), new Vertex<>(new_connecting_vertex));

            graph.removeEdge(start_vertex + "" + connecting_vertex);
            Edge edge = graph.addEdge(new_start_vertex + "" + new_connecting_vertex, new_start_vertex, new_connecting_vertex, true);
            edge.setAttribute("ui.label", new_distance);


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }



    public void remove_edge() {
        try {
            // code to remove Edge
            System.out.println("Enter Start Vertex");
            System.out.print(":: ");
            String start_vertex = scanner.next();

            System.out.println("Enter connecting Vertex");
            System.out.print(":: ");
            String connecting_vertex = scanner.next();

            graphinformation.remove_directed_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex));
            graph.removeEdge(start_vertex + "" + connecting_vertex);
        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }

    public void remove_edge(org.logistics.model.Edge edge_edge) {
        try {
            // code to remove Edge
            String start_vertex = edge_edge.getStart_node().getNodeValue();

            String connecting_vertex = edge_edge.getConnecting_node().getNodeValue();

            graphinformation.remove_directed_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex));
            graph.removeEdge(start_vertex + "" + connecting_vertex);
        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }

    public void print_adjacencyList() {
        graphinformation.print_List();
    }


    /**
     * Method responsible for highlighting the current vertex which the vehicle is currently on
     * @param vertex (Vertex) - Vertex to highlight
     * @param OnOff (int) - 0 to turn off highlight, 1 to turn on highlight
     */
    public void visualise_vehicle(Vertex<String> vertex, int OnOff) {
        Node current_Node = graph.getNode(vertex.getNodeValue());

        if (OnOff == 0) {
            current_Node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");

        }

        if (OnOff == 1) {
//            current_Node.setAttribute("ui.style", "fill-color: red;");
            current_Node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/fast-delivery.png');");


        }

    }

    public boolean doesNodeExist(String nodeID) {
        return graph.getNode(nodeID) != null;
    }

    public boolean doesEdgeExist(String edgeID) {
        return graph.getEdge(edgeID) != null;
    }


}


