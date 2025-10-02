package Controllers;

import model.RawDataNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

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
            File csv = new File(FILTERED_DATASET_NAME);
            if (!csv.exists()) {
                infraestructure.DatasetReader mydata = new infraestructure.DatasetReader();
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
}
