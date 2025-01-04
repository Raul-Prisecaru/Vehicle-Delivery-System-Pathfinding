package org.logistics.controller;

import org.logistics.model.Package;
import org.logistics.model.*;
import org.logistics.model.algorithms.Dijkstra_customerLocation;
import org.logistics.model.algorithms.Dijkstra_deliveryHub;
import org.logistics.view.directedGraphDisplay;
import org.logistics.view.undirectedGraphDisplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class undirectedGraphSimulation {

    public static void main(String[] args) throws Exception {

        // Initializing Graph
        Graph graph = new Graph();

        // Initializing GUI
        undirectedGraphDisplay undirectedGraphDisplay = new undirectedGraphDisplay(graph);

        // Initializing DeliveryHub Vertexes
        DeliveryHub<String> deliveryHubA = new DeliveryHub<String>("A");
        DeliveryHub<String> deliveryHubB = new DeliveryHub<String>("B");

        // Initializing CustomerLocation Vertexes
        CustomerLocation<String> customerLocationC = new CustomerLocation<String>("C");
        CustomerLocation<String> customerLocationD = new CustomerLocation<String>("D");
        CustomerLocation<String> customerLocationE = new CustomerLocation<String>("E");
        CustomerLocation<String> customerLocationF = new CustomerLocation<String>("F");
        CustomerLocation<String> customerLocationG = new CustomerLocation<String>("G");

        graph.add_deliveryHub(deliveryHubA);
        graph.add_deliveryHub(deliveryHubB);

        // Adding CustomerLocation to the Graph
        graph.add_customerLocation(customerLocationC);
        graph.add_customerLocation(customerLocationD);
        graph.add_customerLocation(customerLocationE);
        graph.add_customerLocation(customerLocationF);
        graph.add_customerLocation(customerLocationG);

        // Adding Weighted Edges connections to the Graph
        graph.add_directed_edge(deliveryHubA, deliveryHubB, 4);
        graph.add_directed_edge(deliveryHubA, customerLocationC, 2);

        graph.add_directed_edge(deliveryHubB, customerLocationC, 1);
        graph.add_directed_edge(deliveryHubB, customerLocationD, 2);
        graph.add_directed_edge(deliveryHubB, customerLocationE, 3);

        graph.add_directed_edge(customerLocationC, customerLocationE, 1);
        graph.add_directed_edge(customerLocationC, customerLocationG, 6);
        graph.add_directed_edge(customerLocationD, customerLocationF, 2);

        graph.add_directed_edge(customerLocationE, customerLocationD, 5);
        graph.add_directed_edge(customerLocationE, customerLocationF, 3);
        graph.add_directed_edge(customerLocationF, customerLocationG, 2);

        graph.add_directed_edge(customerLocationG, deliveryHubA, 4);

        // Creating GUI
        undirectedGraphDisplay.createGraph();
        undirectedGraphDisplay.displayGUI();

        while (true) {


        }


    }

   }