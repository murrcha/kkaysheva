package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.magnit.pojo.Entry;
import ru.job4j.magnit.pojo.EntryList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * StoreXML
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreXML {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(StoreXML.class.getName());

    /**
     * target file for save data from sqlite database
     */
    private final File target;

    /**
     * init file
     * @param target file
     */
    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Method save - save data from database table entry to xml file
     * @param entries list
     */
    public void save(List<Entry> entries) {
        try {
            EntryList entryList = new EntryList(entries);
            JAXBContext jaxbContext = JAXBContext.newInstance(EntryList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entryList, target);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
