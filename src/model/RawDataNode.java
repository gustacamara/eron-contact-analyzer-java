package model;
import java.util.ArrayList;

public class RawDataNode {
    private String from;
    private ArrayList<String> to;

    public RawDataNode(String from, ArrayList<String> to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from + " -> " + (to == null || to.isEmpty() ? "[]" : "[" + String.join(",", to) + "]");
    }
}