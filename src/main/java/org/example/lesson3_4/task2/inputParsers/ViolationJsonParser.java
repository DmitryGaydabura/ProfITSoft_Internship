package org.example.lesson3_4.task2.inputParsers;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.example.lesson3_4.task2.pojo.Violation;
import org.example.lesson3_4.task2.pojo.ViolationType;

public class ViolationJsonParser {

    private final JsonFactory jsonFactory;
    private final Violation violation;
    private final Map<ViolationType, Double> statistic;

    public ViolationJsonParser(Map<ViolationType, Double> statistic) {
        this.jsonFactory = new JsonFactory();
        this.violation = new Violation();
        this.statistic = statistic;
    }

    /**
     * "Try to create a parser from the file at the given path, and if that succeeds, parse the file."
     *
     * The try-with-resources statement is a try block that declares one or more resources. A resource is an object that
     * must be closed after the program is finished with it. The try-with-resources statement ensures that each resource is
     * closed at the end of the statement. Any object that implements java.lang.AutoCloseable, which includes all objects
     * which implement java.io.Closeable, can be used as a resource
     *
     * @param path The path to the JSON file.
     */
    public void parseViolationJson(Path path) {
        try (var jsonParser = jsonFactory.createParser(path.toFile())) {
            parse(jsonParser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It parses the JSON file and creates a map of violation types and their total fine amounts
     *
     * @param parser the JsonParser object that will be used to parse the JSON file.
     */
    private void parse(JsonParser parser) throws IOException {
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            parser.nextToken();
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getCurrentName();
                switch (fieldName) {
                    case "type" -> {
                        parser.nextToken();
                        violation.setType(ViolationType.valueOf(parser.getText()));
                    }
                    case "fine_amount" -> {
                        parser.nextToken();
                        violation.setFineAmount(Double.parseDouble(parser.getText()));
                    }
                }
            }
            statistic.computeIfPresent(violation.getType(), (key, value) -> value += violation.getFineAmount());
            statistic.putIfAbsent(violation.getType(), violation.getFineAmount());
        }
    }
}
