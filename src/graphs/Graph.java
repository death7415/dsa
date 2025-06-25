package graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<String, ArrayList<String>> adjList = null;
    public Graph(){
        adjList = new HashMap<>();
    }

    public void addVertex(String vertex){
        adjList.computeIfAbsent(vertex, k -> new ArrayList<>());
    }

    public void printGraph(){
        System.out.println(adjList);
    }

    public boolean addEdge(String node1, String node2){
        if (adjList.get(node1) != null && adjList.get(node2) != null){
            adjList.get(node1).add(node2);
            adjList.get(node2).add(node1);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String node1, String node2){
        if (adjList.get(node1) != null && adjList.get(node2) != null){
            adjList.get(node1).remove(node2);
            adjList.get(node2).remove(node1);
            return true;
        }
        return false;
    }

    public boolean removeVertex(String node){
        if (adjList.get(node) != null){
            adjList.get(node).forEach(connectedNode -> {
                adjList.get(connectedNode).remove(node);
            });
            adjList.remove(node);
            return true;
        }
        return false;
    }
}
