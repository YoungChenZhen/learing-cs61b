package deque;

import java.util.Comparator;

public class MaxArrayDeque<Type> extends ArrayDeque<Type> {
    private final Comparator<Type> comparator;

    public MaxArrayDeque(Comparator<Type> c) {
        super();
        comparator = c;
    }

    public Type max() {
        return max(comparator);
    }

    public Type max(Comparator<Type> c) {
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
