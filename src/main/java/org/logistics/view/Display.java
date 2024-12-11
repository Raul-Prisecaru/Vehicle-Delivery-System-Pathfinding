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

        for (Vertex vertex : this.graphinformation.getAdjacencyList().keySet()) {
            graph.addNode(vertex.get_node_value());
        }

        for (Vertex vertex : this.graphinformation.getAdjacencyList().keySet()) {
            for (org.logistics.model.Edge currentEdge : this.graphinformation.getAdjacencyList().get(vertex)) {

                if (currentEdge == null) {
                    break;
                }
                String id = currentEdge.getStart_node().get_node_value() + currentEdge.getConnecting_node().get_node_value();
                graph.addEdge(id, currentEdge.getStart_node().get_node_value(), currentEdge.getConnecting_node().get_node_value(), true);
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
