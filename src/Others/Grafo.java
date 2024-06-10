/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Others;

import java.util.*;

class Grafo {
    private Map<String, List<String>> adjList;

    public Grafo() {
        adjList = new HashMap<>();
    }

    public void addNode(String node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String source, String destination) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.get(source).add(destination);
    }

    public Map<String, List<String>> getAdjList() {
        return adjList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String node : adjList.keySet()) {
            sb.append(node).append(" -> ").append(adjList.get(node)).append("\n");
        }
        return sb.toString();
    }
}
