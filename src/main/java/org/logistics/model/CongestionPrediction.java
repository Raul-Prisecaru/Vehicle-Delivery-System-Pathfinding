package org.logistics.model;

/**
 * Class Responsible for predicting Congestions on Edges
 */
public class CongestionPrediction {
    private Graph graph;

    /**
     * Constructor
     * @param graph
     */
    public CongestionPrediction(Graph graph) {
        this.graph = graph;
    }

    /**
     * Method Responsible for calculating congestion prediction of provided edge
     */
    public int calculateCongestion(Vertex<String> vertex1, Vertex<String> vertex2) {
        int total = 0;
        int percentage = 0;
        Edge foundEdge = graph.findEdgeAndReturn(vertex1, vertex2);

        if (foundEdge == null) {
            return -1;
        }

        for (int congestionLevels : foundEdge.getCongestionHistory().keySet()) {
            total += foundEdge.getCongestionHistory().get(congestionLevels);
        }

        return percentage;
    }
}
