package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tree Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    /**
     * Test findBy
     */
    @Test
    public void when6ElementFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    /**
     * Test findBy
     */
    @Test
    public void when6ElementFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewChildForExistParentThenReturnTrue() {
        Tree<Integer> tree = new Tree<>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 2), is(false));
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewChildForNotExistParentThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(2);
        assertThat(tree.add(1, 2), is(false));
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewDuplicateChildThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(2);
        assertThat(tree.add(2, 2), is(false));
    }

    /**
     * Test iterator
     */
    @Test
    public void whenGetIteratorThenReturnIterator() {
        Tree<Integer> tree = new Tree<>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(1, 4), is(true));
        assertThat(tree.add(2, 5), is(true));
        assertThat(tree.add(2, 6), is(true));
        assertThat(tree.getSize(), is(6));
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test iterator
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenCallNextAfterAddNewElementThenReturnException() {
        Tree<Integer> tree = new Tree<>(1);
        Iterator<Integer> it = tree.iterator();
        assertThat(tree.add(1, 2), is(true));
        it.next();
    }

    /**
     * Test iterator
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCallNextInEmptyTreeThenReturnException() {
        Tree<Integer> tree = new Tree<>(1);
        Iterator<Integer> it = tree.iterator();
        it.next();
        it.next();
    }

    /**
     * Test isBinary
     */
    @Test
    public void whenTreeIsBinaryThenReturnTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        assertThat(tree.isBinary(), is(true));
    }

    /**
     * Test isBinary
     */
    @Test
    public void whenTreeIsNotBinaryThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        assertThat(tree.isBinary(), is(false));
    }
}