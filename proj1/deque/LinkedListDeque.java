package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private final node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T i) {
        node first = new node(sentinel, i, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(T i) {
        node last = new node(sentinel.prev, i, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.size() == 0)
            return null;
        node first = sentinel.next;
        first.prev = null;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        first.next = null;
        this.size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (this.size() == 0)
            return null;
        node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last.prev = null;
        last.next = null;
        this.size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index >= this.size() || index < 0)
            System.out.println("error");
        int mid = (1 + this.size()) / 2;
        node p = sentinel.next;
        if (index <= mid)
            for (int i = 0; index != i; i++, p = p.next) ;
        if (index > mid) {
            p = sentinel.prev;
            for (int i = this.size() - 1; index != i; i--, p = p.prev) ;
        }
        return p.item;
    }

    private T getFirst() {
        return sentinel.next.item;
    }

    private T getLast() {
        return sentinel.prev.item;
    }

    @Override
    public void printDeque() {
        node p = sentinel.next;
        for (; p != sentinel; p = p.next)
            System.out.print(p.item + " ");
        System.out.println();
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, 0, this.sentinel.next);
    }

    private T getRecursiveHelper(int index, int current, node p) {
        if (current == index)
            return p.item;
        return getRecursiveHelper(index, current + 1, p.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof deque.Deque<?>))
            return false;
        if (this == o)
            return true;
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size())
            return false;
        int i = 0;
        for (; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i)))
                break;
        }
        return i == this.size();
    }

    private class node {
        public node prev;
        public T item;
        public node next;

        public node(node p, T i, node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int pos;

        public LinkedListDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T temp = get(pos);
            pos++;
            return temp;
        }
    }
}

