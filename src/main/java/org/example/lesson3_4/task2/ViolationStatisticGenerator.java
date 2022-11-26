package org.example.lesson3_4.task2;


import org.example.lesson3_4.task2.inputParsers.ViolationJsonParser;
import org.example.lesson3_4.task2.inputParsers.ViolationXmlParser;
import org.example.lesson3_4.task2.outputWriters.ViolationStatisticJsonWriter;
import org.example.lesson3_4.task2.outputWriters.ViolationStatisticXmlWriter;
import org.example.lesson3_4.task2.pojo.ViolationType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViolationStatisticGenerator {

    public static void main(String[] args) {
        ViolationStatisticGenerator generator = new ViolationStatisticGenerator();
        //XML --> Json
        generator.collectAndGenerateStatistic(
                "src/main/java/org/example/lesson3_4/task2/files/inputFile/xml",
               "src/main/java/org/example/lesson3_4/task2/files/outputFile/statistic.json" );

        //Json --> XML
//        generator.collectAndGenerateStatistic(
//                "src/main/java/org/example/lesson3_4/task2/files/inputFile/json",
//                "src/main/java/org/example/lesson3_4/task2/files/outputFile/statistic.xml" );
    }

    private final ViolationXmlParser xmlParser;
    private final ViolationJsonParser jsonParser;
    private Map<ViolationType, Double> statistic;
    private String firstFileType = "";

    public ViolationStatisticGenerator() {
        this.statistic = new HashMap<>();
        this.xmlParser = new ViolationXmlParser(statistic);
        this.jsonParser = new ViolationJsonParser(statistic);
    }

    /**
     * It walks through the folder, filters out the directories, and then parses the files based on their extension
     *
     * @param pathToFolder the path to the folder with the files to be processed
     * @param outputPath the path to the file where the statistics will be written
     */
    public void collectAndGenerateStatistic(String pathToFolder, String outputPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(pathToFolder))) {
            paths
                    .filter(file -> !Files.isDirectory(file))
                    .forEach((file) -> {
                        if (file.toString().endsWith(".xml")) {
                            if (firstFileType.isEmpty()) firstFileType = "xml";
                            xmlParser.parseViolationXML(file);
                        }
                        if (file.toString().endsWith(".json")) {
                            if (firstFileType.isEmpty()) firstFileType = "json";
                            jsonParser.parseViolationJson(file);
                        }
                    });

            sortMapByFineAmount();
            generateStatistic(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If the first file is an XML file, then create a JSON file, otherwise create an XML file.
     *
     * @param outputPath the path to the output file
     */
    private void generateStatistic(String outputPath) {
        switch (firstFileType) {
            case "xml" -> ViolationStatisticJsonWriter.createJson(statistic, outputPath);
            case "json" -> ViolationStatisticXmlWriter.createXml(statistic, outputPath);
        }
    }

    /**
     * "Sort the map by the value of the map, in reverse order, and return a new map with the same keys and values, but in
     * the new order."
     *
     * The first line of the function is the most important. It's a stream of the map's entries, sorted by the value of the
     * map, in reverse order
     */
    private void sortMapByFineAmount() {
        statistic = statistic.entrySet().stream()
                .sorted(Map.Entry.<ViolationType, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (i1, i2) -> i1, LinkedHashMap::new));
    }

}
