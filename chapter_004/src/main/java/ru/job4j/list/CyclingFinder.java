package ru.job4j.list;

/**
 * Node
 * @param <T>
 */
class Node<T> {
    T value;
    Node<T> next;
    public Node(T value) {
        this.value = value;
    }
}

/**
 * CyclingFinder
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class CyclingFinder {

    /**
     * Method hasCycle - проверяет список на наличие замыканий
     * @param first
     * @return
     */
    public boolean hasCycle(Node first) {
        boolean result = false;
        Node slow = first;
        Node fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}
