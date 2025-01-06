package org.logistics.model;

import java.util.HashMap;

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
     * @param vertex1 (Vertex) - starting vertex of the edge
     * @param vertex2 (Vertex) - connecting vertex of the edge
     */
    public HashMap<Integer, Integer> calculateCongestion(Vertex<String> vertex1, Vertex<String> vertex2) {
        HashMap<Integer, Integer> congestionLevelPercentage = new HashMap<>();

        int total = 0;
        Edge foundEdge = graph.findEdgeAndReturn(vertex1, vertex2);

        if (foundEdge == null) {
            return null;
        }

        for (int congestionLevels : foundEdge.getCongestionHistory().keySet()) {
            congestionLevelPercentage.put(congestionLevels, 0);
            total += foundEdge.getCongestionHistory().get(congestionLevels);
        }

        for (int congestionLevels : foundEdge.getCongestionHistory().keySet()) {
            int calculation = (foundEdge.getCongestionHistory().get(congestionLevels) / total * 100);
            congestionLevelPercentage.put(congestionLevels, calculation);

        }

        return congestionLevelPercentage;
    }
}
