package Controllers;

import model.Graph;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class GraphController {
    public void runGraph(Graph g) {
        System.out.println("Numero de vertices: " + g.getMax());
        System.out.println("Numero de arestas: " + g.getAdjacencyCount());

        int topSender = g.getIndex("sally.beck@enron.com");
        int topReceaver = g.getIndex("john.lavorato@enron.com");

        System.out.print("Grau de Saida: ");
        ArrayList<Integer> listOfOutputAdjacency = new ArrayList<>();
        System.out.print(g.getLabelById(topSender));
        System.out.println(" : " + g.outputVertexGrade(topSender, listOfOutputAdjacency));
        g.topOutputGrade();

        System.out.print("\nGrau de entrada: ");
        ArrayList<Integer> listOfInputAdjacency = new ArrayList<>();
        System.out.print(g.getLabelById(topReceaver));
        System.out.println(" : " + g.inputVertexGrade(topReceaver, listOfInputAdjacency));
        g.topInputGrade();

        System.out.println("Busca em largura: " + g.breadthFirstSearch(topSender, topReceaver));
        System.out.println("Busca em profundidade: " + g.deepFirsSearch(topSender, topReceaver));
        System.out.println("Lista de alcançabilidade(" + g.getLabelById(topReceaver) + "): " + g.reach(topReceaver, 5));
        
        ArrayList<Integer> path = new ArrayList<>();
        System.out.println("Custo Dijkstra: " + g.dijkstra(topSender, topReceaver, path));
        System.out.println("Caminho Dijkstra: " + path);

        System.out.println("Grafo ciclino: " + g.isCyclical());
        System.out.println("Numero de componentes: " + g.numberOfComponents());
        System.out.println("Grafo conexo: " + g.isConected());
    }

    public Graph setAdjByHash(Map<String, Map<String, Integer>> map){
        Set<String> allEmails = new HashSet<>();
        for(String from: map.keySet()) {
            allEmails.add(from);
            Map<String, Integer> destinations = map.get(from);
            allEmails.addAll(destinations.keySet()); 
        }
        
        String[] labels = allEmails.toArray(new String[0]);
        System.out.println("Criando grafo com " + labels.length + " vértices únicos");
        System.out.println("Processando " + map.keySet().size() + " remetentes ativos");
        
        Graph g = new Graph(labels);
        int totalAdjacenciesAdded = 0;
        
        for(String from: map.keySet()) {
            Map<String, Integer> destinations = map.get(from);
            
            for (Map.Entry<String, Integer> entry : destinations.entrySet()) {
                String to = entry.getKey();
                int frequency = entry.getValue();
                
                int fromIndex = g.getIndex(from);
                int toIndex = g.getIndex(to);
                
                g.createAdjacency(fromIndex, toIndex, frequency);
                totalAdjacenciesAdded++;
            }
        }
        
        System.out.println("Total de " + totalAdjacenciesAdded + " arestas adicionadas com sucesso");
        return g;
    }
}