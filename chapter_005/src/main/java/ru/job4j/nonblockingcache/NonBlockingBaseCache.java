package ru.job4j.nonblockingcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * NonBlockingBaseCache
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class NonBlockingBaseCache {

    /**
     * For test call OptimisticException
     */
    private volatile int testValue;
    private int modCount = 0;

    private Map<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Method add - add new model in cache
     * @param model - new model
     */
    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    /**
     * Method update - update exist model in cache
     * @param model - exist model
     * @throws OptimisticException
     */
    public void update(Base model) throws OptimisticException {
        cache.computeIfPresent(model.getId(), (key, value) -> {
            if (cache.get(key).getVersion() == model.getVersion()) {
                model.changeVersion();
                return model;
            } else {
                throw new OptimisticException("incorrect version");
            }
        });
    }

    /**
     * Method delete - delete exist model in cache
     * @param model - exist model
     */
    public void delete(Base model) {
        if (cache.containsKey(model.getId())) {
            cache.remove(model.getId());
        }
    }

    /**
     * Method get - get exist model by id
     * @param id - id
     * @return - model or null
     */
    public Base get(int id) {
        return cache.get(id);
    }

    /**
     * Method getTestValue
     * @return
     */
    public int getTestValue() {
        return this.testValue;
    }

    /**
     * Method getModCount
     * @return
     */
    public int getModCount() {
        return this.modCount;
    }

    /**
     * Method setTestValue
     * @param testValue
     * @param modCount
     */
    public void setTestValue(int testValue, int modCount) throws OptimisticException {
        if (this.modCount != modCount) {
            throw new OptimisticException("Incorrect version");
        }
        this.testValue = testValue;
        this.modCount++;
    }
}
