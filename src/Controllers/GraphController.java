package Controllers;

import model.Graph;

import java.util.ArrayList;
import java.util.Map;

public class GraphController {
    public void runGraph(Graph g) {
        System.out.println("Lista de adjacencias: \n");
        g.printAdjacency();

        System.out.print("Grau de Saida: ");
        ArrayList<Integer> listOfOutputAdjacency =  new ArrayList<>();
        System.out.print(g.getLabelById(87));
        System.out.println(" : " + g.outputVertexGrade(87, listOfOutputAdjacency));
        g.topOutputGrade();

        System.out.print("\nGrau de entrada: ");
        ArrayList<Integer> listOfInputAdjacency =  new ArrayList<>();
        System.out.print(g.getLabelById(90));
        System.out.println(" : " + g.inputVertexGrade(90, listOfInputAdjacency));
        g.topInputGrade();

        int fromId = 88; // 88, 10
        int toId = 10; // 104, 50
        System.out.println("\nNumero de vertices: " + g.getMax());
        System.out.println("Numero de arestas: " + g.getAdjacencyCount());
        System.out.println("Busca em largura: " + g.breadthFirstSearch(fromId, toId));
        System.out.println("Busca em profundidade: " + g.deepFirsSearch(fromId, toId));
        System.out.println("Lista de alcançabilidade("+  g.getLabelById(fromId)+"): " + g.reach(fromId,3));
        ArrayList<Integer> path = new ArrayList<>();
        System.out.println("Custo Dijkstra: " + g.dijkstra(fromId, toId, path));
        System.out.println("Caminho Dijkstra: " + path);

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