package model;
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
        while (current != to && visited.size() < max){
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
        if (visited.size() == max - 1 || current != to) {
            return null;
        }
        return visited;
    }

    public ArrayList<Integer> deepFirsSearch(int from, int to) {
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> stack = new ArrayList<>();
        boolean[] visited = new boolean[max];
        stack.add(from);

        while (!stack.isEmpty()) {
            int current = stack.removeLast();
            if (visited[current]) {
                continue;
            }

            visited[current] = true;
            path.add(current);
            if (current == to) {
                return path;
            }

            Vertex v = matrix[current];
            while (v != null) {
                int neighborId = v.getId();
                if (!visited[neighborId]) {
                    stack.add(neighborId);
                }
                v = v.getNext();
            }
        }

        return null;
    }

    public ArrayList<Integer> reach(int from, int distance) {
        if (distance == 0) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(from);
            return result;
        }
        ArrayList<Integer> currentLevel = new ArrayList<>();
        ArrayList<Integer> nextLevel = new ArrayList<>();
        boolean[] visited = new boolean[max];
        currentLevel.add(from);
        visited[from] = true;
        for (int d = 0; d < distance; d++) {
            nextLevel.clear();
            for (int current : currentLevel) {
                Vertex v = matrix[current];

                while (v != null) {
                    int neighborId = v.getId();
                    if (!visited[neighborId]) {
                        visited[neighborId] = true;
                        nextLevel.add(neighborId);
                    }
                    v = v.getNext();
                }
            }

            currentLevel = new ArrayList<>(nextLevel);

            if (currentLevel.isEmpty()) {
                break;
            }
        }

        return currentLevel;
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