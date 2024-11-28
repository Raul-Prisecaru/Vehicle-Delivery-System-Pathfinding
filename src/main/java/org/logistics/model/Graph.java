package org.logistics.model;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    private HashMap<Node, LinkedList<Node>> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

}
