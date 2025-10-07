package model;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;

public class Graph {
    public final int POSITIVE_INFINITY = (int) Double.POSITIVE_INFINITY;

    private Vertex[] matrix;
    private String[] label;
    private final int max;

    public Graph( int max ) {
        this.matrix = new Vertex[max];
        this.label = new String[max];
        this.max = max;
    }

    public Graph( int max, String[] labels ) {
        this.matrix = new Vertex[max];
        this.label = labels;
        this.max = max;
    }

    public Graph( String[] labels ) {
        this.matrix = new Vertex[labels.length];
        this.label = labels;
        this.max = labels.length;
    }

    public void createAdjacency(int from, int to, int weight) {
        if (matrix[from] == null) {
            matrix[from] = new Vertex(to, weight);
            return;
        }
        Vertex vertex = matrix[from];
        while (vertex.getNext()!= null && vertex.getId() != to ) {
            vertex = vertex.getNext();
        }
        if (vertex.getId() != to) {
            vertex.setNext(new Vertex(to, weight));
        } else {
            vertex.setWeight(weight);
        }
    }

    public int getIndex(String email) {
        for(int i = 0; i < max; i ++) {
            if (email.equals(label[i])) {
               return i;
            }
        }
        return 0;
    }

    public double dijkstra(int from, int to) {
        boolean[] visited = new boolean[max];
        double[] distance = new double[max];
        int[] previusVertex = new int[max];
        for( int i = 0; i < max; i++) {
            visited[i] = false;
            distance[i] = POSITIVE_INFINITY;
            previusVertex[i] = -1;
        }
        final int INFINITY = POSITIVE_INFINITY;
        visited[from] = true;
        distance[from] = 0;
        int current = from, k = from;
        double shorterWay, newWay, currentDistance;
        while(current != to) {
            shorterWay = INFINITY;
            currentDistance = distance[current];
            for (int i = 0; i < max; i++) {
                if(!visited[i]){
                    newWay = currentDistance + matrix[current].getAdjVertexById(i).getWeight();
                    if (newWay < distance[i]) {
                        distance[i] = newWay;
                        previusVertex[i] = current;
                    }
                    if (distance[i] < shorterWay) {
                        shorterWay = distance[i];
                        k = i;
                    }
                }
            }
            current = k;
            visited[current] = true;
        }
        return distance[k];
    }

    public void printAdjacency() {
        for(int i = 0; i < max; i++) {
            System.out.print(label[i] + " -> " );
            Vertex vertex = matrix[i];
            while(vertex != null) {
                System.out.printf("(%s | %d) ", label[vertex.getId()], vertex.getWeight());
                vertex = vertex.getNext();
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    public int outputVertexGrade(int id, ArrayList<Integer> adjacency) {
        int grade = 0;
        Vertex vertex = matrix[id];
        while (vertex != null) {
            adjacency.add(vertex.getId());
            grade ++;
            vertex = vertex.getNext();
        }
        return grade;
    }

    public void topOutputGrade() {
        ArrayList<FrequencyNode> node = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            node.add(new FrequencyNode( this.getLabelById(i), outputVertexGrade(i, new ArrayList<>())));
        }
        node.sort(Comparator.comparingInt(FrequencyNode::getFrequency).reversed());
        System.out.println("\nTop 20(grau de saida): ");
        for (int i = 0; i < 20; i++) {
            System.out.println(node.get(i).getEmail() + ": " + node.get(i).getFrequency());
        }
    }

    public void topInputGrade() {
        ArrayList<FrequencyNode> node = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            node.add(new FrequencyNode( this.getLabelById(i), inputVertexGrade(i, new ArrayList<>())));
        }
        node.sort(Comparator.comparingInt(FrequencyNode::getFrequency).reversed());
        System.out.println("\nTop 20(grau de entrada): ");
        for (int i = 0; i < 20; i++) {
            System.out.println(node.get(i).getEmail() + ": " + node.get(i).getFrequency());
        }
    }

    public int inputVertexGrade(int id, ArrayList<Integer> adjacency) {
        int grade = 0;
        for (int i = 0; i < max; i++) {
            Vertex vertex = matrix[i];
            while (vertex != null) {
                if(vertex.getId() == id) {
                    adjacency.add(i);
                    grade++;
                }
                vertex = vertex.getNext();
            }
        }

        return grade;
    }

    public ArrayList<Integer> breadthFirstSearch(int from, int to) {
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        visited.add(from);
        Vertex v;
        int current = from;
        while (current != to){
            v = matrix[current];
            while (v != null) {
                if(!ids.contains(v.getId()) && !visited.contains(v.getId())){
                    ids.add(v.getId());
                }
                v = v.getNext();
            }
            visited.add(ids.removeFirst());
            current = visited.getLast();
        }
        return visited;
    }

    public void warshall() {
        boolean[][] warsh = new boolean[max][max];
        Vertex vertex;
        for (int i = 0; i < max; i++) {
            vertex = matrix[i];
            while (vertex != null) {
                warsh[i][vertex.getId()] = true;
                vertex = vertex.getNext();
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        for (int k = 0; k < max; k++) {
            for (int i = 0; i < max; i++) {
                if (warsh[i][k]) {
                    for (int j = 0; j < max; j++) {
                        warsh[i][j] = warsh[i][j] || warsh[k][j];
                    }
                }
            }
        }
        printMatrix(warsh);
    }

    public void printMatrix( boolean[][] matrixB){
        System.out.print(" ");
        for(String i: label){
            System.out.printf("%50s", i);
        }
        System.out.print("\n");
        for(int i = 0; i < max; i++) {
            System.out.print(label[i]);
            for(boolean j: matrixB[i]){
                if (j) {
                    System.out.printf("%5s", 1);
                }else{
                    System.out.printf("%5s", 0);
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public int getMax() {
        return max;
    }

    public int getAdjacencyCount() {
        int adj = 0;

        for (int i = 0; i < max; i++) {
            Vertex v = matrix[i];
            while (v != null) {
                adj++;
                v = v.getNext();
            }
        }
        return adj;
    }

    public String getLabelById(int id) {
        return label[id];
    }
}