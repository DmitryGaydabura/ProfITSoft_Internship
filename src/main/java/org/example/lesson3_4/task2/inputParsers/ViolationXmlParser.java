package org.example.lesson3_4.task2.inputParsers;
import org.example.lesson3_4.task2.pojo.Violation;
import org.example.lesson3_4.task2.pojo.ViolationType;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
public class ViolationXmlParser extends DefaultHandler {

    private final SAXParserFactory parserFactory;
    private String tagValue = null;

    private final Violation violation;
    private final Map<ViolationType, Double> statistic;

    public ViolationXmlParser(Map<ViolationType, Double> statistic) {
        this.parserFactory = SAXParserFactory.newInstance();
        this.violation = new Violation();
        this.statistic = statistic;
    }

    /**
     * "Create a new SAXParser, and parse the file at the given path using this class as the handler."
     *
     * The first thing we do is create a new SAXParser. This is done by calling the newSAXParser() method on the
     * parserFactory object. This method returns a new SAXParser object, which we store in the parser variable
     *
     * @param path The path to the XML file to parse.
     */
    public void parseViolationXML(Path path) {
        try {
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(path.toFile(), this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If the tag name is "type", then set the violation type to the value of the tagValue variable
     *
     * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is
     * not being performed.
     * @param localName The local name (without prefix), or the empty string if Namespace processing is not being
     * performed.
     * @param qName The qualified name (with prefix), or the empty string if qualified names are not available.
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "type" -> violation.setType(ViolationType.valueOf(tagValue));
            case "fine_amount" -> violation.setFineAmount(Double.parseDouble(tagValue));
            case "violation" -> {
                statistic.computeIfPresent(violation.getType(), (key, value) -> value += violation.getFineAmount());
                statistic.putIfAbsent(violation.getType(), violation.getFineAmount());
            }
        }
    }

    /**
     * The characters() method is called whenever the parser encounters a character string inside a tag
     *
     * @param ch The characters from the XML document.
     * @param start The start position in the array.
     * @param length The length of the character array.
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        tagValue = String.copyValueOf(ch, start, length).trim();
    }
}
