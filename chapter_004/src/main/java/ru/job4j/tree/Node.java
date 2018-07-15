package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Node - element of tree
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Node<E extends Comparable<E>> {

    /**
     * List of children
     */
    private final List<Node<E>> children = new ArrayList<>();

    /**
     * Value for root node
     */
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    /**
     * Method add - add child
     * @param child
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Method leaves - return children
     * @return
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Method eqValue - equals values
     * @param that
     * @return
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Method getValue
     * @return
     */
    public E getValue() {
        return this.value;
    }
}
