package ru.job4j.magnit;

import org.junit.Test;
import ru.job4j.magnit.pojo.Entry;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertXSQTTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertXSQTTest {

    private final File target = new File("entries.xml");
    private final File result = new File("result.xml");
    private final File scheme = new File("convert.xsl");

    /**
     * Test convert
     */
    @Test
    public void whenConvertXmlFileThenCreateOtherXmlFile() throws IOException {
        StoreSQL store = new StoreSQL();
        store.generate(2);
        StoreXML storeXML = new StoreXML(target);
        List<Entry> entries = store.getEntries();
        storeXML.save(entries);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(target, result, scheme);
        assertThat(result.exists(), is(true));
        BufferedReader reader = new BufferedReader(new FileReader(result));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry field=\"1\"/><entry field=\"2\"/></entries>";
        assertThat(result.toString(), is(expected));
    }

}