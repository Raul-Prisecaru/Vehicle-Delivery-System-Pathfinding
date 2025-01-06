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
            return null; // Return null if no edge is found
        }


        for (int count : foundEdge.getCongestionHistory().values()) {
            total += count;
        }


        for (int congestionLevel : foundEdge.getCongestionHistory().keySet()) {
            int count = foundEdge.getCongestionHistory().get(congestionLevel);
            int percentage = (int) (((double) count / total) * 100);
            congestionLevelPercentage.put(congestionLevel, percentage);
        }

        return congestionLevelPercentage;
    }

}
