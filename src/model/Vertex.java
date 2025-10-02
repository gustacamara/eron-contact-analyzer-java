package model;

public class Vertex {
    private int id;
    private Vertex next;
    private int weight;

    public Vertex(int weight) {
        this.weight = weight;
    }

    public Vertex(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public Vertex getAdjVertexById(int id) {
        Vertex node = this;
        while( node.id != id && node.getNext() != null){
            node = node.getNext();
        }
        if (node.id == id){
            return node;
        }
        return new Vertex((int)Double.POSITIVE_INFINITY);
    }

    public Vertex getNext() {
        return next;
    }

    public int getWeight() {
        return weight;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }
}
