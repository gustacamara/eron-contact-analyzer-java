package Controllers;

import model.Graph;

import java.util.ArrayList;
import java.util.Map;

public class GraphController {
    public void runGraph(Graph g) {

        g.createAdjacency(0, 3);
        g.createAdjacency(0, 3);
        g.createAdjacency(0, 1);
        g.createAdjacency(1, 4);
//        g.createAdjacency(1, 2); // makes 1 get everyone because 2 go to all nodes
        g.createAdjacency(2, 0);
        g.createAdjacency(2, 1);
        g.createAdjacency(2, 3);
        g.createAdjacency(2, 3);
        g.createAdjacency(2, 3);
        g.createAdjacency(2, 4);
        g.createAdjacency(2, 5);
        g.createAdjacency(3, 4);
        g.createAdjacency(4, 5);
        g.createAdjacency(5, 1);
        ArrayList<Integer> listOfAdjacency =  new ArrayList<>();
//        g.printAdjacency();
        System.out.println(g.vertexGrade(2, listOfAdjacency));
        System.out.println(listOfAdjacency);
        System.out.println();

        g.printAdjacency();
//        g.warshall();
//        System.out.println(g.dijkstra(0,5) + ": custo do caminho!");
    }

    public Graph setAdjByHash(Map<String, Map<String, Integer>> map){
        Map<String, Integer> key;
        String[] labels, toList;
        int frequency;
        labels = map.keySet().toArray(new String[0]);
        Graph g = new Graph(labels);
        for(String from: labels) {

            key = map.get(from);
            toList = key.keySet().toArray(new String[0]);

            for (String s : toList) {
                frequency = key.get(s);
                g.createAdjacency(g.getIndex(from), g.getIndex(s), frequency);
            }
        }
        return g;
    }
}