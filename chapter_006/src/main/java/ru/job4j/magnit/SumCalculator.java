package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * SumCalculator
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SumCalculator {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(SumCalculator.class.getName());

    /**
     * Method calculateSum - parse xml file and calculate sum all values attribute field
     * @param file xml file
     */
    public void calculateSum(File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            FieldHandler handler = new FieldHandler();
            saxParser.parse(file, handler);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * FieldHandler parser xml
     */
    private class FieldHandler extends DefaultHandler {

        private static final String FIELD = "field";
        private static final String ENTRY = "entry";
        private long sum = 0;

        /**
         * ${@inheritDoc}
         */
        @Override
        public void endDocument() {
            System.out.println(String.format("Sum value = %s", sum));
        }

        /**
         * ${@inheritDoc}
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equalsIgnoreCase(ENTRY)) {
                sum += Integer.parseInt(attributes.getValue(FIELD));
            }
        }
    }
}
