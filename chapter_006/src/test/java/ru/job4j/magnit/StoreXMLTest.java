package ru.job4j.magnit;

import org.junit.Test;
import ru.job4j.magnit.pojo.Entry;

import java.io.*;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * StoreXMLTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreXMLTest {

    private final File target = new File("entries.xml");

    /**
     * Test save
     */
    @Test
    public void whenSaveSqlDataToXmlFileThenCreateXmlFile() throws IOException {
        StoreSQL store = new StoreSQL();
        store.generate(1);
        StoreXML storeXML = new StoreXML(target);
        List<Entry> entries = store.getEntries();
        storeXML.save(entries);
        assertThat(target.exists(), is(true));
        BufferedReader reader = new BufferedReader(new FileReader(target));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><entries>    <entry>        <field>1</field>    </entry></entries>";
        assertThat(result.toString(), is(expected));
    }
}