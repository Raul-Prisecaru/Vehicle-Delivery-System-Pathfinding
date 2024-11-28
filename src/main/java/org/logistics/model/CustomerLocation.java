package org.logistics.model;

public class CustomerLocation extends Node {
    public CustomerLocation(int value) {
        super(value);
    }

    public int get_node_value() {
        return super.get_node_value();
    }

    public void set_node_value(int new_value) {
        super.set_node_value(new_value);
    }

    @Override
    public String toString() {
        return "CustomerLocation " + get_node_value();
    }
}
