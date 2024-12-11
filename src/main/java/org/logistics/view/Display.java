package org.logistics.view;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.logistics.model.Vertex;

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

//        graph.addNode("A");
//        graph.addNode("B");
//        graph.addNode("C");
//        graph.addEdge("AB", "A", "B");
//        graph.addEdge("BC", "B", "C");
//        graph.addEdge("CA", "C", "A");


        graph.display();
    }

}
