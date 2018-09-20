package ru.job4j.magnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import ru.job4j.magnit.pojo.Entry;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SumCalculatorTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SumCalculatorTest {

    private final File target = new File("entries.xml");
    private final File result = new File("result.xml");
    private final File scheme = new File("convert.xsl");
    private final PrintStream stdOut = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOutput() {
        System.setOut(stdOut);
    }

    /**
     * Test calculateSum
     */
    @Test
    public void whenCalculateSumThenSumOutToConsole() {
        try (StoreSQL store = new StoreSQL()) {
            store.generate(1000000);
            StoreXML storeXML = new StoreXML(target);
            List<Entry> entries = store.getEntries();
            storeXML.save(entries);
            ConvertXSQT convertXSQT = new ConvertXSQT();
            convertXSQT.convert(target, result, scheme);
            SumCalculator calculator = new SumCalculator();
            calculator.calculateSum(result);
            String expected = String.format("Sum value = 500000500000%s", System.lineSeparator());
            assertThat(new String(out.toByteArray()), is(expected));
        } catch (SQLException | JAXBException | TransformerException
                | IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}