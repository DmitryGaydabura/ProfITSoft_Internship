package org.example.lesson3_4.task2.outputWriters;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.example.lesson3_4.task2.pojo.ViolationType;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
public class ViolationStatisticJsonWriter {

    private static final JsonFactory jsonFactory = new JsonFactory();

    /**
     * It creates a JSON file with the given statistics.
     *
     * @param statistic a map of violation types and their corresponding violation rates
     * @param outputPath the path to the output file
     */
    public static void createJson(Map<ViolationType, Double> statistic, String outputPath) {
        try (var outputStream = new FileOutputStream(outputPath);
             var bufferedOutputStream = new BufferedOutputStream(outputStream);
             var jsonGenerator = jsonFactory.createGenerator(bufferedOutputStream, JsonEncoding.UTF8)) {
            jsonGenerator.useDefaultPrettyPrinter();
            generateViolationJson(jsonGenerator, statistic);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * > This function generates a JSON array of objects, each object containing a violation type and the fine amount
     * associated with it
     *
     * @param jsonGenerator This is the object that will be used to write the JSON.
     * @param statistic a map of violation types and their corresponding fine amounts
     */
    private static void generateViolationJson(JsonGenerator jsonGenerator, Map<ViolationType, Double> statistic) throws IOException {
        jsonGenerator.writeStartArray();
        statistic.forEach(((violationType, fineAmount) -> createViolationObject(jsonGenerator, violationType, fineAmount)));
        jsonGenerator.writeEndArray();
    }

    /**
     * It creates a JSON object with two fields: violationType and fineAmountSum
     *
     * @param jsonGenerator This is the object that will be used to write the JSON file.
     * @param violationType The type of violation that was committed.
     * @param fineAmount The amount of the fine.
     */
    private static void createViolationObject(JsonGenerator jsonGenerator, ViolationType violationType, Double fineAmount) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("violationType", violationType.toString());
            jsonGenerator.writeNumberField("fineAmountSum", fineAmount);
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}