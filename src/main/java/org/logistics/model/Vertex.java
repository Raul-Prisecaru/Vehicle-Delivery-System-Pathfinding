package org.logistics.model;


public class Vertex {
    private int value;

    public Vertex(int value){
        this.value = value;
    }

    public int get_node_value() {
        return this.value;
    }

    public void set_node_value(int new_value) {
        this.value = new_value;
    }

    
}
