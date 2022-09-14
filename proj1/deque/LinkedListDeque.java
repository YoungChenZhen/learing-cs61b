package deque;

import java.lang.Iterable;
import java.util.Iterator;

public class LinkedListDeque<Type> implements Iterable<Type>, Deque<Type> {

    private final node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(Type i) {
        node first = new node(sentinel, i, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(Type i) {
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
    public Type removeFirst() {
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
    public Type removeLast() {
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
    public Type get(int index) {
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

    public Type getFirst() {
        return sentinel.next.item;
    }

    public Type getLast() {
        return sentinel.prev.item;
    }

    @Override
    public void printDeque() {
        node p = sentinel.next;
        for (; p != sentinel; p = p.next)
            System.out.print(p.item + " ");
        System.out.println();
    }

    public Type getRecursive(int index) {
        return getRecursiveHelper(index, 0, this.sentinel.next);
    }

    private Type getRecursiveHelper(int index, int current, node p) {
        if (current == index)
            return p.item;
        return getRecursiveHelper(index, current + 1, p.next);
    }

    @Override
    public Iterator<Type> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque<?>))
            return false;
        if (this == o)
            return true;
        LinkedListDeque<Type> other = (LinkedListDeque<Type>) o;
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
        public Type item;
        public node next;

        public node(node p, Type i, node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private class LinkedListDequeIterator implements Iterator<Type> {

        private int pos;

        public LinkedListDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public Type next() {
            Type temp = get(pos);
            pos++;
            return temp;
        }
    }
}

