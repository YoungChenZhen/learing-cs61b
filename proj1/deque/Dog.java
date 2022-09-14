package deque;

import java.util.Comparator;

public class Dog {
    private final String name;
    private final int age;

    public Dog(String n, int a) {
        name = n;
        age = a;
    }

    public static NameComparator getNameComparator() {
        return new NameComparator();
    }

    public static AgeComparator getAgeComparator() {
        return new AgeComparator();
    }

    public void bark() {
        System.out.println(name + " says: Bark!");
    }

    private static class NameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog o1, Dog o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    private static class AgeComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog o1, Dog o2) {
            return o1.age - o2.age;
        }
    }

}
