package ru.job4j.tree;

import java.util.*;

/**
 * Tree - simple tree
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Root of tree
     */
    private Node<E> root;

    /**
     * Counter of modification
     */
    private int modCount = 0;

    /**
     * Size of tree
     */
    private int size;

    public Tree(E root) {
        this.root = new Node<>(root);
        this.size++;
    }

    /**
     * Method getSize
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentResult = findBy(parent);
        Optional<Node<E>> childResult = findBy(child);
        if (parentResult.isPresent() && !childResult.isPresent()) {
            Node<E> parentNode = parentResult.get();
            Node<E> childNode = new Node<>(child);
            parentNode.add(childNode);
            result = true;
            this.modCount++;
            this.size++;
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> nodes = new LinkedList<>();
        nodes.offer(this.root);
        while (!nodes.isEmpty()) {
            Node<E> node = nodes.poll();
            if (node.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : node.leaves()) {
                nodes.offer(child);
            }
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>();
    }

    /**
     * TreeIterator
     * @param <E>
     */
    final class TreeIterator<E extends Comparable<E>> implements Iterator<E> {

        Queue<Node<E>> data;

        int expectedModCount = modCount;

        @SuppressWarnings("unchecked")
        public TreeIterator() {
            this.data = new LinkedList<>();
            this.data.offer((Node<E>) root);
            Queue<Node<E>> temp = new LinkedList<>();
            temp.offer((Node<E>) root);
            while (!temp.isEmpty()) {
                Node<E> element = temp.poll();
                for (Node<E> child : element.leaves()) {
                    this.data.offer(child);
                    temp.offer(child);
                }
            }
        }

        /**
         * ${@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return !this.data.isEmpty();
        }

        /**
         * ${@inheritDoc}
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return this.data.poll().getValue();
        }
    }
}
