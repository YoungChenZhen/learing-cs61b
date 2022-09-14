package deque;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Comparator;
public class TestMaxArrayDeque {
    @Test
    public void testMaxArrayDeque(){
        Comparator<Dog> ageComparator=Dog.getAgeComparator();
        Comparator<Dog> nameComparator=Dog.getNameComparator();

        MaxArrayDeque<Dog> dl=new MaxArrayDeque<>(ageComparator);
        dl.addLast(new Dog("Pedal",3));
        dl.addLast(new Dog("Maka",2));
        dl.addLast(new Dog("Friday",3));
        dl.addLast(new Dog("Mary",7));
        dl.addLast(new Dog("Kail",2));
        dl.addLast(new Dog("Yeo",1));

        dl.max().bark();
        dl.max(nameComparator).bark();
    }
}
