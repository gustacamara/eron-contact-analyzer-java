package model;
import java.util.ArrayList;

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

    public void createAdjacency(int from, int to) {
        if (matrix[from] == null) {
            matrix[from] = new Vertex(to, 1);
            return;
        }
        Vertex vertex = matrix[from];
        while (vertex.getNext()!= null && vertex.getId() != to ) {
            vertex = vertex.getNext();
        }
        if (vertex.getId() != to) {
            vertex.setNext(new Vertex(to, 1));
        } else {
            vertex.setWeight(vertex.getWeight() + 1);
        }
    }

    public void removeAdjacency(int from, int to) {
        Vertex previous = matrix[from];
        if (previous == null) {
            return;
        }
        if (previous.getId() == to) {
            matrix[from] = previous.getNext();
            return;
        }

        Vertex current = previous.getNext();
        while (current != null) {
            if (current.getId() == to) {
                previous.setNext(current.getNext());
                return;
            }
            previous = current;
            current = current.getNext();
        }
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

    public void setArrowInformation(int index, String name) {
        label[index] = name;
    }

    public int vertexGrade(int id, ArrayList<Integer> adjacency) {
        int grade = 0;
        Vertex vertex = matrix[id];
        while (vertex != null) {
            adjacency.add(vertex.getId());
            grade ++;
            vertex = vertex.getNext();
        }
        return grade;
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


}