package Controllers;

import model.RawDataNode;
import model.DatasetReader;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatasetController {
    private String DATASET_DIR;
    private String FILTERED_DATASET_NAME;
    private ArrayList<Path> sentMailDir;
    private ArrayList<RawDataNode> nodes;

    public DatasetController(String DATASET_DIR, String FILTERED_DATASET_DIR) {
        this.DATASET_DIR = DATASET_DIR;
        this.FILTERED_DATASET_NAME = FILTERED_DATASET_DIR;
        this.nodes = new ArrayList<>();
    }

    public void runRoutine() throws IOException {
        try{
            if (!hasDataProcessed()) {
                DatasetReader mydata = new DatasetReader();
                ArrayList<Path> sentMailDir = mydata.scanDir(DATASET_DIR);
                ArrayList<RawDataNode> nodes = new ArrayList<>();
                for(Path sentMailPath: sentMailDir) {
                    mydata.getFromAndToFromFolder(sentMailPath, nodes);
                }
                writeCSV(nodes, String.valueOf(FILTERED_DATASET_NAME));
                System.out.println("Diretorio criado");
            }
            System.out.println("Já existe");
        } catch (IOException e){
            System.out.println(e);
        }

    }

    public void writeCSV(ArrayList<RawDataNode> nodes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (RawDataNode node : nodes) {
                writer.write(node.toString() + "\n");
            }
            System.out.println("Arquivo CSV criado com sucesso: " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo CSV: " + e.getMessage());
        }
    }

    public Map<String, Map<String, Integer>> getAdjacencyRelationShipp() throws IOException {
        Map<String, Map<String, Integer>> adj = new HashMap<>();
        if(hasDataProcessed()) {
            String[] fields;
            String line, k, v;
            try (BufferedReader reader = new BufferedReader(new FileReader(FILTERED_DATASET_NAME))) {
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    fields = line.split(", ");
                    k = fields[0].trim();
                    v = fields[1].trim();
                    incrementHash(adj, k, v);
                }
            }
        }
        return  adj;
    }

    public void incrementHash( Map<String, Map<String, Integer>> map, String from, String to) {
        map.putIfAbsent(from, new HashMap<>());
        map.get(from).merge(to, 1, Integer::sum);
    }

    public boolean hasDataProcessed() {
        return new File(FILTERED_DATASET_NAME).exists();
    }
}
