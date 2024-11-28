package org.logistics.model;


public class Node {
    private int value;

    public Node(int value){
        this.value = value;
    }

    public int get_node_value() {
        return this.value;
    }

    public void set_node_value(int new_value) {
        this.value = new_value;
    }

    
}
