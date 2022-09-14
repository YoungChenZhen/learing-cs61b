package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private int firstIndex;
    private double usage_Ratio;
    private T[] p;

    public ArrayDeque() {
        p = (T[]) new Object[8];
        size = 0;
        firstIndex = 2;
        usage_Ratio = 1;
    }

    @Override
    public void addFirst(T x) {
        if (firstIndex == 0) {
            this.resize();
        }
        p[firstIndex - 1] = x;
        firstIndex--;
        size++;
    }

    @Override
    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if (size == p.length - firstIndex) {
            this.resize();
        }
        p[size + firstIndex] = x;
        size++;
    }

    /**
     * Returns the item from the back of the list.
     */
    public T getLast() {
        return p[size - 1 + firstIndex];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    @Override
    public T get(int i) {
        return p[i + firstIndex];
    }

    /**
     * Returns the number of items in the list.
     */
    @Override
    public int size() {
        return size;
    }

    public T getFirst() {
        if (size() == 0) {
            return null;
        }
        return p[firstIndex];
    }

    @Override
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        usage_Ratio = (double) size / p.length;
        if (usage_Ratio <= 0.25 && size() > 4) {
            resizeToHalf();
        }
        T temp = this.getFirst();
        p[firstIndex] = null;     //if the stored stuff is big,then we set its reference to null,
        // so we can save memory
        firstIndex++;
        size--;
        return temp;
    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    @Override
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        usage_Ratio = (double) size / p.length;
        if (usage_Ratio <= 0.25 && size() > 4) {
            resizeToHalf();
        }
        T temp = this.getLast();
        p[firstIndex + size - 1] = null;   //if the stored stuff is big,then we set its reference to null,so we can save memory
        size--;
        return temp;
    }

    public double getUsageRatio() {
        usage_Ratio = (double) size() / p.length;
        return usage_Ratio;
    }

    private void resize() {
        T[] a = (T[]) new Object[p.length * 2];    //resize by multiplying the number of boxes by 2.
        // which can be quite fast when add millions numbers;
        System.arraycopy(p, firstIndex, a, a.length / 4, size);
        firstIndex = a.length / 4;
        p = a;
    }

    /**
     * In order to save memory,when memory usage ratio is less than 0.25,
     * change array's length to it's half.
     */
    private void resizeToHalf() {
        T[] a = (T[]) new Object[p.length / 2];
        System.arraycopy(p, firstIndex, a, a.length / 4, size);
        firstIndex = a.length / 4;
        p = a;
    }

    @Override
    public void printDeque() {
        for (int i = firstIndex; i < this.size() + firstIndex; i++) {
            System.out.print(this.get(i) + "  ");
        }
    }

    /**
     * Returns an iterator over elements of type {@code Type}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof deque.Deque<?>)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        int i = 0;
        for (; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                break;
            }
        }
        return i == this.size();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int pos;

        public ArrayDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            T temp = get(pos);
            pos++;
            return temp;
        }
    }
}
