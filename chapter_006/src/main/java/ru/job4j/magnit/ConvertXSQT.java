package ru.job4j.magnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * ConvertXSQT
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertXSQT {

    /**
     * Method convert - transform xml file to other xml file by xslt scheme
     * @param source xml file
     * @param destination xml file
     * @param scheme scheme xls file
     * @throws TransformerException exception
     */
    public void convert(File source, File destination, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(scheme)
        );
        transformer.transform(
                new StreamSource(source), new StreamResult(destination)
        );
    }
}
