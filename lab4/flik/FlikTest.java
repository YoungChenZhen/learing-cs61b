package flik;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void test() {
        for (int i = 0, j = 0; i < 100000; i++, j++) {
            boolean IsSameNum = Flik.isSameNumber(i, j);
            assertTrue(i + " should equals to " + j, IsSameNum);
        }
    }
}
