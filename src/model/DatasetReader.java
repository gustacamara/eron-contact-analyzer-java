package model;

import model.RawDataNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DatasetReader {
    public ArrayList<Path> scanDir(String dirPath) throws IOException {
        ArrayList<Path> fileNames = new ArrayList<>();
        Path rootPath = Paths.get(dirPath);
        if (!Files.exists(rootPath)) {
            throw new IOException("O diretório especificado não existe: " + dirPath);
        }

        try (Stream<Path> paths = Files.walk(rootPath)) {
            paths.filter(Files::isDirectory)
                    .filter(path -> path.getFileName().toString().contains("_sent_mail"))
                    .forEach(fileNames::add);
        }
        return fileNames;
    }

    public void getFromAndToFromFolder(Path sentMailDir, ArrayList<RawDataNode> nodes) {
        try (Stream<Path> files = Files.walk(sentMailDir)) {
            files.filter(Files::isRegularFile).forEach(file -> {
                try (BufferedReader reader = Files.newBufferedReader(file)) {
                    StringBuilder builder = new StringBuilder();
                    String filteredLine = filterLine(reader, builder);
                    reduceFile(nodes, filteredLine, builder);
                } catch (IOException e) {
                    System.err.println("Erro ao ler arquivo " + file + ": " + e.getMessage());
                }
            });
        } catch (IOException e) {
            System.err.println("Erro ao listar arquivos do diretório " + sentMailDir + ": " + e.getMessage());
        }
    }

    private static void reduceFile(ArrayList<RawDataNode> nodes, String filteredLine, StringBuilder builder) {
        if (filteredLine != null && !builder.isEmpty()) {
            String toStr = builder.toString()
                    .replace(" ", "");
            String[] toArray = toStr.split(",");
            ArrayList<String> toList = new ArrayList<>();

            for (String email : toArray) {
                String cleanEmail = email.trim();
                if (!cleanEmail.isEmpty()) {
                    toList.add(cleanEmail);
                }
            }
            if (!toList.isEmpty()) {
                for (String toEmail: toList) {
                    nodes.add(new RawDataNode(filteredLine, toEmail));
                }
            }
        }
    }

    private static String filterLine(BufferedReader reader, StringBuilder toBuilder) throws IOException {
        boolean isReadingTo = false;
        String from = null;
        String line;
        while ((line = reader.readLine()) != null) {
            String trimmedLine = line.trim();
            if (trimmedLine.startsWith("From:")) {
                from = trimmedLine.substring(5).trim();
            }
            else if (trimmedLine.startsWith("To:")) {
                toBuilder.append(trimmedLine.substring(3).trim());
                isReadingTo = true;
            }
            else if (trimmedLine.startsWith("Subject:")) {
                break;
            }
            else if (isReadingTo && !trimmedLine.isEmpty()) {
                toBuilder.append(" ").append(trimmedLine);
            }
        }
        return from;
    }


}

