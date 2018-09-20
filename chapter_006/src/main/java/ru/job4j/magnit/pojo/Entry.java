package ru.job4j.magnit.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Entry
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Entry {

    private List<Field> fields;

    public Entry() {
    }

    public Entry(List<Field> fields) {
        this.fields = fields;
    }

    @XmlElement(name = "field")
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
