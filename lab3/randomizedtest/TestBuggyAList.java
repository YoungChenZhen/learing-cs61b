package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    //TODO: YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        for (int i = 0; i < 100; i++) {
            correctList.addLast(i);
            buggyList.addLast(i);
        }
        assertEquals(correctList.size(), buggyList.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(correctList.getLast(), buggyList.getLast());
            assertEquals(correctList.removeLast(), buggyList.removeLast());
        }
    }
    @Test
    public void randomizedTest() {

        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1=new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L1.addLast(randVal);
                L.addLast(randVal);
               // System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeL1=L1.size();
               // System.out.println("sizeL: " + sizeL+" sizeL1: "+sizeL1);
                assertEquals(sizeL,sizeL1);
            }else if(operationNumber==2){
                if(L.size()>0&&L1.size()>0) {
                    //System.out.println("getLast(" + L.getLast() + ")");
                    assertEquals(L.getLast(),L1.getLast());
                }
            }else if(operationNumber==3){
                if(L.size()>0&&L1.size()>0) {
                   // System.out.println("removeLast(" + L.removeLast() + ")");
                    assertEquals(L.removeLast(),L1.removeLast());
                }
            }

        }
    }
}
