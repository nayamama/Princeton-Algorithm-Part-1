import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if(first == null) {
            Node onlynode = new Node();
            onlynode.item = item;
            onlynode.next = null;
            onlynode.prev = null;
            first = onlynode;
            last = onlynode;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            oldfirst.prev = first;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if(last == null) {
            Node onlynode = new Node();
            onlynode.item = item;
            onlynode.next = null;
            onlynode.prev = null;
            first = onlynode;
            last = onlynode;
        }else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            oldlast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) first = last = null;
        else first.prev = null;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) first = last = null;
        else last.next = null;
        return item;
    }

    // return an iterator over items in order from front to end
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    // unit testing (optional)
    public static void main(String[] args) {
        /*
        Deque deque = new Deque();
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("c");
        deque.addFirst("d");
        deque.removeFirst();
        deque.addLast("e");
        deque.addLast("f");
        deque.removeFirst();
        deque.removeLast();
        deque.addLast("g");
        deque.addLast("h");
        deque.removeLast();
        for (Object s : deque) {
            StdOut.println(s);
        }

        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(0);
        StdOut.println(deque.removeFirst());     //==> 0
        StdOut.println(deque.isEmpty());         //==> true
        deque.addLast(3);
        for (Object s : deque) {
            StdOut.println(s);
        }
        StdOut.println(deque.removeFirst());     //==> 0
        */
    }

}
