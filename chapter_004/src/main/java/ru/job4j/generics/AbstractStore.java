package ru.job4j.generics;

/**
 * AbstractStore
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * Массив элементов
     */
    private SimpleArray<T> elements;

    /**
     * Инициализация массива
     * @param size
     */
    protected AbstractStore(int size) {
        this.elements = new SimpleArray<>(size);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void add(T model) {
        this.elements.add(model);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int index = 0; index < this.elements.getPosition(); index++) {
            if (this.elements.get(index).getId().equals(id)
                    && this.elements.get(index).getId().equals(model.getId())) {
                this.elements.set(index, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index < this.elements.getPosition(); index++) {
            if (this.elements.get(index).getId().equals(id)) {
                this.elements.delete(index);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public T findById(String id) {
        T result = null;
        for (int index = 0; index < this.elements.getPosition(); index++) {
            if (this.elements.get(index).getId().equals(id)) {
                result = this.elements.get(index);
                break;
            }
        }
        return result;
    }

    /**
     * Method getElements
     * @return массив элементов
     */
    public SimpleArray<T> getElements() {
        return this.elements;
    }
}
