package org.example.lesson3_4.task1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persons {

    public static void main(String[] args) {
        File file = new File("src/main/java/org/example/lesson3_4/task1/inputFile/persons.xml");
        Persons persons = new Persons();
        persons.parseAndWriteChangedFile(file,"src/main/java/org/example/lesson3_4/task1/outputFile/");
    }

    // A regular expression that matches the name tag.
    private final Pattern namePattern = Pattern.compile("(?<!sur)name\\s?=\\s?\"(?<name>\\S+)\"", Pattern.MULTILINE | Pattern.COMMENTS);

    // A regular expression that matches the surname tag.
    private final Pattern surnamePattern = Pattern.compile("surname\\s?=\\s?\"(?<surname>\\S+)\"", Pattern.MULTILINE | Pattern.COMMENTS);

    /**
     * It takes an input file, parses it, and writes the parsed data to an output file
     *
     * @param inputFile The file to be parsed.
     * @param outputPath The path to the output file.
     */
    public void parseAndWriteChangedFile(File inputFile, String outputPath) {
        try (var fileInputStream = new FileInputStream(inputFile);
             var scanner = new Scanner(fileInputStream, StandardCharsets.UTF_8).useDelimiter("/>");
             var fileWriter = new FileWriter(outputPath + inputFile.getName(), true);
             var bufferedWriter = new BufferedWriter(fileWriter)
        ) {

            // Replacing the name tags to full name.
            while (scanner.hasNext()) {
                // Replacing the name tags to full name.
                String line = replaceNameTagsToFullName(scanner.next());
                bufferedWriter.write(scanner.hasNext() ? line + "/>" : line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * It takes a line of text, finds the name and surname tags, and replaces them with a full name tag
     *
     * @param line The line of text that we're currently working on.
     * @return The newline string is being returned.
     */
    private String replaceNameTagsToFullName(String line) {
        Matcher nameMatcher = namePattern.matcher(line);
        Matcher surnameMatcher = surnamePattern.matcher(line);

        String personName;
        String personSurname = "";
        String newline = line;

        // Replacing the surname tag with an empty string.
        if (surnameMatcher.find()) {
            personSurname = surnameMatcher.group("surname");
            newline = newline.replaceAll(surnamePattern.toString(), "");
        }

        // Replacing the name tag with the full name.
        if (nameMatcher.find()) {
            personName = nameMatcher.group("name");
            newline = newline.replaceAll(namePattern.toString(), String.format("name=\"%s %s\"", personName, personSurname));
        }



        return newline;
    }
}