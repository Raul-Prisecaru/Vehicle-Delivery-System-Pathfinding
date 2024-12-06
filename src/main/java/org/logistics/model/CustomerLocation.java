package org.logistics.model;

public class CustomerLocation extends Vertex {
    

    public CustomerLocation(String value) {
        super(value);
    }

    public String get_node_value() {
        return super.get_node_value();
    }

    public void set_node_value(String new_value) {
        super.set_node_value(new_value);
    }

    public int get_distance() {
        return super.get_distance();
    }

    public void set_distance(int new_distance) {
        super.set_distance(new_distance);
    }

    @Override
    public String toString() {
        return "CustomerLocation " + get_node_value();
    }
}
