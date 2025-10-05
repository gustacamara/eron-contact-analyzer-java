package app;

import Controllers.DatasetController;
import Controllers.GraphController;
import model.Graph;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static final String DATASET = "./src/data/maildir";
    public static final String DATASET_REDUCED = "./src/data/processed/data.csv";

    public static void main(String[] args) throws IOException {
        DatasetController datasetController = new DatasetController(DATASET, DATASET_REDUCED);
        datasetController.runRoutine();
        Map<String, Map<String, Integer>> adj = datasetController.getAdjacencyRelationShipp();
        GraphController gc = new GraphController();
         Graph g = gc.setAdjByHash(adj);

        gc.runGraph(g);
    }


}