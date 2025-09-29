package model;
import java.util.ArrayList;

public class RawDataNode {
    private String from;
    private String to;

    public RawDataNode(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return from + ", " + to;
    }
}