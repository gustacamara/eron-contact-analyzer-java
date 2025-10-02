package app;

import Controllers.DatasetController;
import Controllers.GraphController;
import infraestructure.DatasetReader;
import model.Graph;
import model.RawDataNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Arrays;

public class Main {
    public static final String DATASET = "./src/data/maildir";
    public static final String DATASET_REDUCED = "./src/data/processed/data.csv";

    public static void main(String[] args) throws IOException {
        DatasetController datasetController = new DatasetController(DATASET, DATASET_REDUCED);
        datasetController.runRoutine();
        GraphController g = new GraphController();
        g.runGraph();
    }

}