package app;

import model.DatasetReader;
import model.RawDataNode;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        DatasetReader mydata = new DatasetReader();
        ArrayList<Path> sentMailDir = mydata.scanDir("./src/data/maildir");
        ArrayList<RawDataNode> nodes = new ArrayList<>();
        for(Path sentMailPath: sentMailDir) {
            mydata.getFromAndToFromFolder(sentMailPath, nodes);
        }
        writeNodesToCSV(nodes, "./src/data/data.csv");

    }
    public static void writeNodesToCSV(ArrayList<RawDataNode> nodes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (RawDataNode node : nodes) {
                writer.write(node.toString() + "\n");
            }
            System.out.println("Arquivo CSV criado com sucesso: " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo CSV: " + e.getMessage());
        }
    }
}