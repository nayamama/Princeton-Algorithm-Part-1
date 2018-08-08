import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int MINIMUM_STORAGE_SIZE = 2;
    private int size;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[MINIMUM_STORAGE_SIZE];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i=0; i<size; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    public void enqueue(Item item) {
        if(item == null) throw new IllegalArgumentException();
        arr[size++] = item;
        if (size == arr.length) {
            resize(2 * arr.length);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
        int idx = StdRandom.uniform(size);
        Item item = arr[idx];
        size--;
        arr[idx] = arr[size];
        arr[size] = null;
        if (size > 0 && size == arr.length / 4){
            resize(arr.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty()) throw new NoSuchElementException();
        int idx = StdRandom.uniform(size);
        return arr[idx];
    }

    // return an independent iterator over items in random order
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = size;
        private int[] order;

        public  RandomizedQueueIterator(){
            order = new int[i];
            for (int k=0; k<i; k++) {
                order[k] = k;
            }
            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            return arr[order[--i]];
        }
    }

    public Iterator<Item> iterator()  {
        return new RandomizedQueueIterator();
    }

}
