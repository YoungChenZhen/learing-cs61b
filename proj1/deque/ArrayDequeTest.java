package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testEmptySize() {
        ArrayDeque<String> L = new ArrayDeque<>();
        assertTrue("A newly initialized LLDeque should be empty", L.isEmpty());
        L.addLast("LBJ");

        assertEquals(1, L.size());
        assertFalse("L should now contain 1 item", L.isEmpty());

        L.addLast("STP");
        assertEquals(2, L.size());

        L.removeLast();
        L.removeFirst();
        assertEquals(0, L.size());
    }

    public void randomizedAdd(ArrayDeque<String> AL, LinkedListDeque<String> LL) {
        for (int i = 0; i < 5; i++) {
            int addFOrAddL = StdRandom.uniform(0, 2);
            if (addFOrAddL == 0) {
                AL.addFirst("hello");
                LL.addFirst("hello");
            }
            if (addFOrAddL == 1) {
                AL.addLast("hello");
                LL.addLast("hello");
            }
        }
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        //    System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }

    public void randomizedGet(ArrayDeque<String> AL, LinkedListDeque<String> LL) {
        for (int i = 0; i < 20; i++) {
            int operationNumber = StdRandom.uniform(0, AL.size());
            assertEquals(AL.get(operationNumber), LL.get(operationNumber));
        }
    }

    public void testGetFirstAndGetLast(ArrayDeque<String> AL, LinkedListDeque<String> LL) {
        assertEquals(AL.getLast(), LL.getLast());
        assertEquals(AL.getFirst(), LL.getFirst());
    }

    public void testRemoveFirst(ArrayDeque<String> AL, LinkedListDeque<String> LL) {
        assertEquals(AL.removeFirst(), LL.removeFirst());
    }

    public void testRemoveLast(ArrayDeque<String> AL, LinkedListDeque<String> LL) {
        assertEquals(AL.removeLast(), LL.removeLast());
    }

    public void testUsageRatio(ArrayDeque<String> AL) {
        assertTrue("usage ratio should be greater than 0.25", AL.getUsageRatio() > 0.25);
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100000; i++) {
            lld1.addLast(i);
        }

        int i = 0;
        for (int x : lld1) {
            assertEquals(x, (int) lld1.get(i));
            i++;
        }
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> IntL1 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            IntL1.addLast(i);
        }

        ArrayDeque<Integer> IntL2 = IntL1;

        ArrayDeque<Integer> IntL3 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            IntL1.addLast(i);
        }
        IntL3.removeLast();
        IntL3.addLast(89);

        ArrayDeque<String> StrL = new ArrayDeque<>();
        for (Integer i = 0; i < 1000; i++) {
            StrL.addLast(i.toString());
        }

        assertEquals("IntL1 should equals to IntL2", IntL1, IntL2);
        assertNotEquals("IntL1 should not equals to IntL2", IntL1, IntL3);
        assertNotEquals("IntL1 should not equals to StrL", IntL1, StrL);
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<String> LL = new LinkedListDeque<>();
        ArrayDeque<String> AL = new ArrayDeque<>();
        int N = 100000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                randomizedAdd(AL, LL);
            }
            if (operationNumber == 1) {
                if (LL.size() > 0 && AL.size() > 0)
                    testGetFirstAndGetLast(AL, LL);
            }
            if (operationNumber == 2) {
                if (LL.size() > 0 && AL.size() > 0)
                    randomizedGet(AL, LL);
            }
            if (operationNumber == 3) {
                if (LL.size() > 0 && AL.size() > 0)
                    testRemoveFirst(AL, LL);
            }
            if (operationNumber == 4) {
                if (LL.size() > 0 && AL.size() > 0)
                    testRemoveLast(AL, LL);
            }
            if (operationNumber == 5) {
                if (AL.size() > 4)
                    testUsageRatio(AL);
            }
        }
    }
}
