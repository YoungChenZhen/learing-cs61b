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
            this.resize((int)(p.length*1.6));
        }
        p[firstIndex - 1] = x;
        firstIndex--;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == p.length - firstIndex) {
            this.resize((int)(p.length*1.6));
        }
        p[size + firstIndex] = x;
        size++;
    }

    public T getLast() {
        return p[size - 1 + firstIndex];
    }

    @Override
    public T get(int i) {
        return p[i + firstIndex];
    }

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
        if (getUsageRatio() <= 0.25 && size() > 4) {
            resize(p.length/2);
        }
        T temp = this.getFirst();
        p[firstIndex] = null;     //if the stored stuff is big,then we set its reference to null,
        // so we can save memory
        firstIndex++;
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        if (getUsageRatio() <= 0.25 && size() > 4) {
            resize(p.length/2);
        }
        T temp = this.getLast();
        p[firstIndex + size - 1] = null;   //if the stored stuff is big,then we set its reference to null,so we can save memory
        size--;
        return temp;
    }

    private void resize(int Size) {
        T[] a = (T[]) new Object[Size];    //resize by multiplying the number of boxes by 2.
        // which can be quite fast when add millions numbers;
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

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof deque.Deque<?>)||this.size() != ((Deque<T>)o).size()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        Deque<T> other = (Deque<T>) o;
        int i = 0;
        for (; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                break;
            }
        }
        return i == this.size();
    }

    public double getUsageRatio() {
        usage_Ratio=(double) size/p.length;
        return usage_Ratio;
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
