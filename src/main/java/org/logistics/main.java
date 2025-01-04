package org.logistics;

import org.logistics.controller.directedGraphSimulation;
import org.logistics.controller.undirectedGraphSimulation;

import java.util.Scanner;

public class main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- Currently there are two simulation available --");
        System.out.println("- Simulation 1: Directed, Dijkstra Algorithm, Bellman-ford");
        System.out.println("- Simulation 2: Undirected, Kruskal Algorithm");
        System.out.print("Which Simulation would you like to run: ");

        int simulation_option = scanner.nextInt();

        switch (simulation_option) {
            case 1:
                org.logistics.controller.directedGraphSimulation.runSimulation();
                break;

            case 2:
                org.logistics.controller.undirectedGraphSimulation.runSimulation();
        }
    }
}
