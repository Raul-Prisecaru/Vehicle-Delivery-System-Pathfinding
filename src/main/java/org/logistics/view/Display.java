package org.logistics.view;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.logistics.model.Vertex;

import java.util.LinkedList;

public class Display {
    private org.logistics.model.Graph graphinformation;

    public Display(org.logistics.model.Graph graph) {
        this.graphinformation = graph;
    }
    public void displayGraph() {
        System.setProperty("org.graphstream.ui", "swing");


        Graph graph = new SingleGraph("Tutorial 1");
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



//        graph.addNode("A");
//        graph.addNode("B");
//        graph.addNode("C");
//        graph.addEdge("AB", "A", "B");
//        graph.addEdge("BC", "B", "C");
//        graph.addEdge("CA", "C", "A");


        graph.display();
    }

}
