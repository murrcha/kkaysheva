package ru.job4j.magnit.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * EntryList
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@XmlRootElement(name = "entries")
public class EntryList {

    private List<Entry> entries;

    public EntryList() {
    }

    public EntryList(List<Entry> entries) {
        this.entries = entries;
    }

    @XmlElement(name = "entry")
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
