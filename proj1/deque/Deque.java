package deque;

public interface Deque<Type> {
    public void addFirst(Type item);

    public void addLast(Type item);

    public default boolean isEmpty() {
        return (this.size() == 0);
    }

    public int size();

    public void printDeque();

    public Type removeFirst();

    public Type removeLast();

    public Type get(int index);
}
