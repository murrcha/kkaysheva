package ru.job4j.statistics;

/**
 * Info
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Info {

    private int addedCount;
    private int changedCount;
    private int deletedCount;

    public Info(int addedCount, int changedCount, int deletedCount) {
        this.addedCount = addedCount;
        this.changedCount = changedCount;
        this.deletedCount = deletedCount;
    }

    /**
     * Method getAddedCount
     * @return
     */
    public int getAddedCount() {
        return addedCount;
    }

    /**
     * Method getChangedCount
     * @return
     */
    public int getChangedCount() {
        return changedCount;
    }

    /**
     * Method getDeletedCount
     * @return
     */
    public int getDeletedCount() {
        return deletedCount;
    }
}
