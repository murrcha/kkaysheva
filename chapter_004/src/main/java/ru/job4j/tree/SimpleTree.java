package ru.job4j.tree;

import java.util.Optional;

/**
 * SimpleTree
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Method add - add child for parent if parent present
     * @param parent
     * @param child
     * @return
     */
    boolean add(E parent, E child);

    /**
     * Method findBy - find node by value
     * @param value
     * @return
     */
    Optional<Node<E>> findBy(E value);
}
