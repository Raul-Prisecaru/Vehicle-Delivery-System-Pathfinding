package org.logistics.view;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.logistics.model.CustomerLocation;
import org.logistics.model.DeliveryHub;
import org.logistics.model.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class directedGraphDisplay {
    private org.logistics.model.Graph graphinformation;
    Scanner scanner = new Scanner(System.in);
    Graph graph = new SingleGraph("Tutorial 1");


    public directedGraphDisplay(org.logistics.model.Graph graph) {
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


        for (Vertex<String> vertex : this.graphinformation.getAllDeliveryHub()) {
            Node node = graph.addNode(vertex.getNodeValue());
            node.setAttribute("ui.label", vertex.getNodeValue());
            node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");
        }

        for (Vertex<String> vertex : this.graphinformation.getAllCustomerLocation()) {
            Node node = graph.addNode(vertex.getNodeValue());
            node.setAttribute("ui.label", vertex.getNodeValue());
            node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/home.png');");
        }

        for (Vertex<String> vertex : this.graphinformation.getAllDeliveryHub()) {
            for (org.logistics.model.Edge currentEdge : this.graphinformation.getEdges(vertex)) {

                if (currentEdge == null) {
                    break;
                }

                String id = currentEdge.getStart_node().getNodeValue() + currentEdge.getConnecting_node().getNodeValue();
                Edge edge = graph.addEdge(id, currentEdge.getStart_node().getNodeValue(), currentEdge.getConnecting_node().getNodeValue(), true);
                edge.setAttribute("ui.label", currentEdge.getTime_weight());

            }

        }


        for (Vertex<String> vertex : this.graphinformation.getAllCustomerLocation()) {
            for (org.logistics.model.Edge currentEdge : this.graphinformation.getEdges(vertex)) {

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

        viewer.enableAutoLayout();

        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);

//        JPanel splitPanel = new JPanel(new GridLayout(1 ,2));

//        splitPanel.add(viewPanel);

        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));

//        splitPanel.add(logPanel);
//
        frame.add(viewPanel);



        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));

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

        JButton switchAlgorithmButton = new JButton("Switch Algorithm");
        switchAlgorithmButton.addActionListener(e -> switchPathfindingButton());

        JButton createVehicleButton = new JButton("Create Vehicle");
        createVehicleButton.addActionListener(e -> addVehicleButton());

        buttonPanel.add(addDeliverHubButton);
        buttonPanel.add(removeDeliverHubButton);
        buttonPanel.add(addCustomerLocationButton);
        buttonPanel.add(removeCustomerLocationButton);
        buttonPanel.add(addEdgeButton);
        buttonPanel.add(modifyEdgeButton);
        buttonPanel.add(removeEdgeButton);
        buttonPanel.add(printAdjacencyListButton);
        buttonPanel.add(switchAlgorithmButton);
        buttonPanel.add(createVehicleButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);


        frame.setSize(1200, 800);
        frame.setVisible(true);
    }

    public void add_deliveryHub() {
        try {
            String vertex_value = JOptionPane.showInputDialog(null,
                    "Enter DeliveryHub Value:",
                    "Add DeliveryHub",
                    JOptionPane.PLAIN_MESSAGE);

            if (vertex_value != null && !vertex_value.trim().isEmpty()) {
                graphinformation.add_deliveryHub(new DeliveryHub<>(vertex_value));

                Node vertex = graph.addNode(vertex_value);
                vertex.setAttribute("ui.label", vertex_value);
                vertex.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");


                JOptionPane.showMessageDialog(null,
                        "DeliveryHub '" + vertex_value + "' added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "DeliveryHub value cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }


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
            String vertex_value = JOptionPane.showInputDialog(null,
                    "Enter CustomerLocation Value:",
                    "Add CustomerLocation",
                    JOptionPane.PLAIN_MESSAGE);

            if (vertex_value != null && !vertex_value.trim().isEmpty()) {

                graphinformation.add_customerLocation(new CustomerLocation<>(vertex_value));
                Node vertex = graph.addNode(vertex_value);
                vertex.setAttribute("ui.label", vertex_value);
                vertex.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/home.png');");


                JOptionPane.showMessageDialog(null,
                        "CustomerLocation '" + vertex_value + "' added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "CustomerLocation value cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }




        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void add_customerLocation(CustomerLocation<String> customerLocation) {
        try {
            String vertex_value = customerLocation.getNodeValue();
            graphinformation.add_customerLocation(new CustomerLocation<>(vertex_value));
            Node vertex = graph.addNode(vertex_value);
            vertex.setAttribute("ui.label", vertex_value);
            vertex.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/home.png');");


        } catch (Exception e) {
            System.out.println("An Error has occurred: " + e.getMessage());
        }
    }


    public void remove_deliveryHub() {
        try {
            String vertex_value = JOptionPane.showInputDialog(null,
                    "Enter DeliveryHub Value:",
                    "Delete DeliveryHub",
                    JOptionPane.PLAIN_MESSAGE);

            if (vertex_value != null && !vertex_value.trim().isEmpty()) {
                graphinformation.remove_vertex(new Vertex<>(vertex_value));
                graph.removeNode(vertex_value);

                JOptionPane.showMessageDialog(null,
                        "DeliveryHub '" + vertex_value + "' deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "DeliveryHub value cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
            String vertex_value = JOptionPane.showInputDialog(null,
                    "Enter CustomerLocation Value:",
                    "Delete CustomerLocation",
                    JOptionPane.PLAIN_MESSAGE);

            if (vertex_value != null && !vertex_value.trim().isEmpty()) {
                graphinformation.remove_vertex(new Vertex<>(vertex_value));
                graph.removeNode(vertex_value);

                JOptionPane.showMessageDialog(null,
                        "CustomerLocation '" + vertex_value + "' deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "CustomerLocation value cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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


            String start_vertex = JOptionPane.showInputDialog(null,
                    "Enter Start Vertex:",
                    "Add Edge",
                    JOptionPane.PLAIN_MESSAGE);

            if (start_vertex == null || start_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Start Vertex cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String connecting_vertex = JOptionPane.showInputDialog(null,
                    "Enter Connecting Vertex:",
                    "Add Edge",
                    JOptionPane.PLAIN_MESSAGE);

            if (connecting_vertex == null || connecting_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Connecting Vertex cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String distance_weight = JOptionPane.showInputDialog(null,
                    String.format("Enter Distance Weight between %s and %s (number):", start_vertex, connecting_vertex),
                    "Add Edge",
                    JOptionPane.PLAIN_MESSAGE);

            if (distance_weight == null || distance_weight.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Distance Weight cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }


            graphinformation.add_directed_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex), Integer.parseInt(distance_weight));

            Edge edge = graph.addEdge(start_vertex + "" + connecting_vertex, start_vertex, connecting_vertex, true);
            edge.setAttribute("ui.label", distance_weight);


            JOptionPane.showMessageDialog(null,
                    "Edge Successfully Added",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
            String start_vertex = JOptionPane.showInputDialog(null,
                    "Enter Existing Start Vertex:",
                    "Continue",
                    JOptionPane.PLAIN_MESSAGE);

            if (start_vertex == null || start_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Start Vertex cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String connecting_vertex = JOptionPane.showInputDialog(null,
                    "Enter Existing Connecting Vertex:",
                    "Continue",
                    JOptionPane.PLAIN_MESSAGE);

            if (connecting_vertex == null || connecting_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Connecting Vertex cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String new_distance = JOptionPane.showInputDialog(null,
                    "Enter New Distance",
                    "Continue",
                    JOptionPane.PLAIN_MESSAGE);

            if (new_distance == null || new_distance.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Distance Weight cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String new_start_vertex = JOptionPane.showInputDialog(null,
                    "Enter New Start Vertex",
                    "Continue",
                    JOptionPane.PLAIN_MESSAGE);

            if (new_start_vertex == null || new_start_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "New Start Vertex cannot be empty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }


            String new_connecting_vertex = JOptionPane.showInputDialog(null,
                    "Enter New Connecting Vertex",
                    "Modify Edge",
                    JOptionPane.PLAIN_MESSAGE);

            if (new_connecting_vertex == null || new_connecting_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "New Connecting Vertex cannot be empty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }


            graphinformation.modify_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex), Integer.parseInt(new_distance), new Vertex<>(new_start_vertex), new Vertex<>(new_connecting_vertex));

            graph.removeEdge(start_vertex + "" + connecting_vertex);
            Edge edge = graph.addEdge(new_start_vertex + "" + new_connecting_vertex, new_start_vertex, new_connecting_vertex, true);
            edge.setAttribute("ui.label", new_distance);


            JOptionPane.showMessageDialog(null,
                    "Edge Successfully Modified",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
            String start_vertex = JOptionPane.showInputDialog(null,
                    "Enter Start Vertex:",
                    "Continue",
                    JOptionPane.PLAIN_MESSAGE);

            if (start_vertex == null || start_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Start Vertex cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String connecting_vertex = JOptionPane.showInputDialog(null,
                    "Enter Connecting Vertex:",
                    "Delete Vertex",
                    JOptionPane.PLAIN_MESSAGE);

            if (connecting_vertex == null || connecting_vertex.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Connecting Vertex cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            graphinformation.remove_directed_edge(new Vertex<>(start_vertex), new Vertex<>(connecting_vertex));
            graph.removeEdge(start_vertex + "" + connecting_vertex);

            JOptionPane.showMessageDialog(null,
                    "Edge Successfully Deleted",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
    public void visualise_vertex(Vertex<String> vertex, int OnOff) {
        Node current_Node = graph.getNode(vertex.getNodeValue());

        if (OnOff == 0) {
            if (vertex instanceof DeliveryHub<String>) {
                current_Node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/building.png');");

            }

            if (vertex instanceof CustomerLocation<String>) {
                current_Node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/home.png');");
            }

        }

        if (OnOff == 1) {
//            current_Node.setAttribute("ui.style", "fill-color: red;");
            current_Node.setAttribute("ui.style", "fill-image: url('src/main/java/org/logistics/view/icons/fast-delivery.png');");


        }

    }

    public void visualise_edge(org.logistics.model.Edge edge, int OnOff) {
        Edge current_edge = graph.getEdge(edge.getStart_node().getNodeValue() + "" + edge.getConnecting_node().getNodeValue());

        if (current_edge != null) {
            if (OnOff == 1) {
                current_edge.setAttribute("ui.style", "fill-color: red;");
            }

            if (OnOff == 0) {
                current_edge.setAttribute("ui.style", "fill-color: black;");

            }
        }
    }

    public void switchPathfindingButton() {
        try {
            if (graphinformation.getPathfindingOption() == 1) {
                System.out.println("Switched to Bellman-ford Algorithm");
                graphinformation.setPathfindingOption(2);
            } else {
                System.out.println("Switched to Dijkstra's Algorithm");
                graphinformation.setPathfindingOption(1);
            }


        } catch (Exception e) {
            System.out.println("There has been an error: " + e.getMessage() );
        }
    }


    public void addVehicleButton() {
        try {
            graphinformation.createVehicle(graphinformation.getAllDeliveryHub().getFirst());
            System.out.println("Vehicle Created");
        } catch (Exception e) {
            System.out.println("There has been an error creating Vehicle:" + e.getMessage());
        }


    }

    public boolean doesNodeExist(String nodeID) {
        return graph.getNode(nodeID) != null;
    }

    public boolean doesEdgeExist(String edgeID) {
        return graph.getEdge(edgeID) != null;
    }

}


