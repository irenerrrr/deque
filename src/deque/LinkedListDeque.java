package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        private T item;
        private Node next;
        private Node prev;
        public Node(Node p, T x, Node n) {
            prev = p;
            item = x;
            next = n;
        }
    }
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public static void main(String[] args) {

        Deque<Integer> lld = new LinkedListDeque<>();
    }
    @Override
    public void addFirst(T x) {
        Node newNode = new Node(sentinel, x, sentinel.next);
        sentinel.next = newNode;
        sentinel.next.next.prev = newNode;
        size++;
    }

    // sentinel.pre is Tail
    @Override
    public void addLast(T x) {
        Node newNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev = newNode;
        sentinel.prev.prev.next = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node dummy = sentinel;
        while (dummy.next != sentinel) {
            returnList.add(dummy.next.item);
            dummy = dummy.next;
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

        if (size == 0) {
            return null;
        }
        T x = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return x;

    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return x;
    }

    @Override
    public T get(int index) {

        Node dummy = sentinel;

        while (dummy.next != sentinel) {
            dummy = dummy.next;
            if (index == 0) {
                return dummy.item;
            }
            if (index < 0 || index >= size) {
                return null;
            }
            index--;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return helper(index, sentinel.next);
    }

    public T helper(int index, Node newNode) {
        if (index == 0) {
            return newNode.item;
        }
        return helper(index - 1, newNode.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        private Node p;
        public LinkedListIterator() {
            wizPos = 0;
            p = sentinel;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            p = p.next;
            T returnItem = p.item;
            wizPos += 1;
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
