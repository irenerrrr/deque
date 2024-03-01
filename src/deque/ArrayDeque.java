package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    final int MAGICNUMBER = 16;
    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
    }
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity;

    public ArrayDeque() {

        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        nextFirst = capacity - 1;
        nextLast = 0;

    }
    private void resize(int capacity1) {

        T[] a = (T[]) new Object[capacity1];

        for (int i = 0; i < size; i++) {
            int index = (nextFirst + 1 + i) % capacity;
            a[i] = items[index];
        }

        capacity = capacity1;
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }


    @Override
    //add from the back [- - - 1]
    public void addFirst(T x) {
        if (size == capacity) {
            resize(this.capacity * 2);
        }
        items[nextFirst] = x;
        size++;
        nextFirst--;
        if (nextFirst < 0) {
            nextFirst = capacity - 1;
        }
    }

    //add from the front [1 - - -]
    @Override
    public void addLast(T x) {
        if (size == capacity) {
            resize(this.capacity * 2);
        }
        items[nextLast] = x;
        size++;
        nextLast++;
        if (nextLast == capacity) {
            nextLast = 0;
        }

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = (nextFirst + 1 + i) % capacity;
            returnList.add(items[index]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if ((size <= capacity / 4) && capacity > MAGICNUMBER) {
            resize(this.capacity / 2);
        }
        if (size > 0) {
            size--;
            T x;
            if (nextFirst == capacity - 1) {
                x = items[0];
                items[0] = null;
                nextFirst = 0;
            } else {
                x = items[nextFirst + 1];
                items[nextFirst + 1] = null;
                nextFirst++;
            }
            if (size == 0) {
                nextFirst = capacity - 1;
                nextLast = 0;
            }
            return x;
        }
        if (nextLast == capacity) {
            nextLast = 0;
        }
        return null;
    }


    @Override
    public T removeLast() {
        if ((size <= capacity / 4) && capacity > MAGICNUMBER) {
            resize(this.capacity / 2);
        }
        if (size > 0) {
            size--;
            T x;
            if (nextLast <= 0) {
                x = items[capacity - 1];
                nextLast = capacity - 1;
                items[capacity - 1] = null;
            } else {
                x = items[nextLast - 1];
                items[nextLast - 1] = null;
                nextLast--;
            }
            if (size == 0) {
                nextLast = 0;
                nextFirst = capacity - 1;
            }
            return x;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index1 = (nextFirst + 1 + i) % capacity;
            returnList.add(items[index1]);
        }
        return returnList.get(index);
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = nextFirst + 1;
            if (wizPos == capacity) {
                wizPos = 0;
            }
        }

        public boolean hasNext() {

            return wizPos != nextLast;
        }

        @Override
        public T next() {
            if (wizPos == capacity) {
                wizPos = 0;
            }

            T returnItem = items[wizPos];
            wizPos++;

            if (wizPos == capacity) {
                wizPos = 0;
            }
            return returnItem;
        }
    }


    @Override
    public boolean equals(Object o) {
        //base case
        if (this == o) {
            return true;
        }
        if (o instanceof Deque oas) {
            // check ArrayDeque are of the same size
            if (oas.size() != this.size) {
                return false;
            }
            //check if each element is same
            for (int i = 0; i < size; i++) {
                if (!oas.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        // o is not an ArrayDeque, so return false
        return false;
    }

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        return "[" + String.join(", ", listOfItems) + "]";
    }


}

