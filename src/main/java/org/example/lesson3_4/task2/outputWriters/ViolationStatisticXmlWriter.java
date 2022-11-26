package org.example.lesson3_4.task2.outputWriters;

import org.example.lesson3_4.task2.pojo.ViolationType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ViolationStatisticXmlWriter {

    private static final DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    private static final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    private static Transformer transformer;
    private static DocumentBuilder documentBuilder;
    private static Document violationStatisticXml;

    /**
     * > It creates an XML file with the given statistics
     *
     * @param statistic a map of violation types and their corresponding violation rates
     * @param outputPath the path to the output file
     */
    public static void createXml(Map<ViolationType, Double> statistic, String outputPath) {
        prepareWriter();
        generateViolationXml(statistic);

        try (var outputStream = new FileOutputStream(outputPath);
             var bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            writeXml(bufferedOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It creates a new document builder and a new transformer
     */
    private static void prepareWriter() {
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the XML to the output stream.
     *
     * @param bufferedOutputStream The output stream to write the XML to.
     */
    private static void writeXml(BufferedOutputStream bufferedOutputStream) {
        DOMSource domSource = new DOMSource(violationStatisticXml);
        StreamResult result = new StreamResult(bufferedOutputStream);
        try {
            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * It takes a map of violation types and their fine amounts, and creates an XML document with a root node called
     * "statistic" and a child node for each violation type
     *
     * @param statistic a map of violation types and their fine amounts
     */
    private static void generateViolationXml(Map<ViolationType, Double> statistic) {
        violationStatisticXml = documentBuilder.newDocument();

        Element root = violationStatisticXml.createElement("statistic");
        violationStatisticXml.appendChild(root);

        statistic.forEach((violationType, fineAmount) -> root.appendChild(createViolationNode(violationType, fineAmount)));
    }

    /**
     * It creates a new XML node with the given violation type and fine amount
     *
     * @param violationType The type of violation that was committed.
     * @param fineAmount the amount of money that the driver has to pay for the violation
     * @return A node that contains the violation type and the fine amount.
     */
    private static Node createViolationNode(ViolationType violationType, Double fineAmount) {
        Element violationBlock = violationStatisticXml.createElement("violation");

        Element violationTypeNode = violationStatisticXml.createElement("violationType");
        violationTypeNode.setTextContent(violationType.toString());

        Element violationFineAmountNode = violationStatisticXml.createElement("fineAmountSum");
        violationFineAmountNode.setTextContent(fineAmount.toString());

        violationBlock.appendChild(violationTypeNode);
        violationBlock.appendChild(violationFineAmountNode);

        return violationBlock;
    }

}
