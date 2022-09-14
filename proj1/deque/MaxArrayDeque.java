package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty())
            return null;
        int i = 0;
        int maxIndex = 0;
        for (; i < this.size(); i++) {
            int compareNum = c.compare(this.get(i), this.get(maxIndex));
            if (compareNum > 0)
                maxIndex = i;
        }
        return this.get(maxIndex);
    }
}
