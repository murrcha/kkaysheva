package ru.job4j.nonblockingcache;

/**
 * Base
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Base {

    private int id;
    private int version = 0;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Method getId
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method getVersion
     * @return version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Method getName
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method changeVersion
     */
    public void changeVersion() {
        this.version++;
    }
}
