package Controllers;

import model.Graph;

import java.util.ArrayList;

public class GraphController {
    public void runGraph() {
        String[] labels = {
                "phillip.allen@enron.com",
                "greg.piper@enron.com",
                "david.l.johnson@enron.com",
                "mark.scott@enron.com",
                "buck.buckner@honeywell.com",
                "keith.holst@enron.com"
        };

        Graph g = new Graph(labels);
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
        g.printAdjacency();
        System.out.println(g.vertexGrade(2, listOfAdjacency));
        System.out.println(listOfAdjacency);
        System.out.println();

        g.printAdjacency();
        g.warshall();
        System.out.println(g.dijkstra(0,5) + ": custo do caminho!");
    }
}
