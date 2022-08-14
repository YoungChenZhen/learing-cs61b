package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    @Test
    public void testSquqrePrimes(){
        IntList L1=IntList.of(7,4,6,11,4,76);
        IntList L2=IntList.of(2,4,6,8,10,12,80);
        IntList L3=IntList.of(7,4,6,17,8,34,23);
        boolean changed1=IntListExercises.squarePrimes(L1);
        boolean changed2=IntListExercises.squarePrimes(L2);
        boolean changed3=IntListExercises.squarePrimes(L3);
        assertEquals("49 -> 4 -> 6 -> 121 -> 4 -> 76", L1.toString());
        assertEquals("4 -> 4 -> 6 -> 8 -> 10 -> 12 -> 80", L2.toString());
        assertEquals("49 -> 4 -> 6 -> 289 -> 8 -> 34 -> 529", L3.toString());
        assertTrue(changed1);
        assertTrue(changed2);
        assertTrue(changed3);
    }
    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }
}
