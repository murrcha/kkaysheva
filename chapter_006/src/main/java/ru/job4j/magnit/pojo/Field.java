package ru.job4j.magnit.pojo;

import javax.xml.bind.annotation.XmlValue;

/**
 * Field
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Field {

    private int field;

    public Field() {
    }

    public Field(int field) {
        this.field = field;
    }

    @XmlValue
    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
