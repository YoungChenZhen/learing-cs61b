package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.Deque;
import student.StudentArrayDeque;
public class TestArrayDequeEC {

    public void testAddFirst(StudentArrayDeque<Integer> stuAD,ArrayDequeSolution<Integer> AD,
                             StringBuilder wrongMsg){
        int addNum=StdRandom.uniform(0, 500);
        stuAD.addFirst(addNum);
        AD.addFirst(addNum);
        wrongMsg.append("addLast(").append(addNum).append(")\n");;
        assertEquals(wrongMsg.toString(),
                AD.size(),stuAD.size());
    }

    public void testAddLast(StudentArrayDeque<Integer> stuAD,ArrayDequeSolution<Integer> AD,
                            StringBuilder wrongMsg){
        int addNum=StdRandom.uniform(0, 500);
        stuAD.addLast(addNum);
        AD.addLast(addNum);
        wrongMsg.append("addLast(").append(addNum).append(")\n");
        assertEquals(wrongMsg.toString(),
                AD.size(),stuAD.size());
    }
    public void testRemoveFirst(StudentArrayDeque<Integer> stuAD,ArrayDequeSolution<Integer> AD,
                                StringBuilder wrongMsg){
        Integer stuRMVaule=stuAD.removeFirst();
        Integer adRMVaule=AD.removeFirst();
        wrongMsg.append("RemoveFirst()\n");
        assertEquals(wrongMsg.toString(),
                adRMVaule,stuRMVaule);
    }

    public void testRemoveLast(StudentArrayDeque<Integer> stuAD,ArrayDequeSolution<Integer> AD,
                               StringBuilder wrongMsg){
        Integer stuRMVaule=stuAD.removeLast();
        Integer adRMVaule=AD.removeLast();
         wrongMsg.append("removeLast()\n");
        assertEquals(wrongMsg.toString(),stuRMVaule, adRMVaule);
    }

    public void testGet(StudentArrayDeque<Integer> stuAD,ArrayDequeSolution<Integer> AD,
                        StringBuilder wrongMsg){
        int getIndex=StdRandom.uniform(0, AD.size());
        Integer stuGetNum=stuAD.get(getIndex);
        Integer adGetNum=AD.get(getIndex);
        wrongMsg.append("get()\n");
        assertEquals(wrongMsg.toString(),
                stuGetNum,adGetNum);
    }
    @Test
    public void randomizedTest(){
        StudentArrayDeque<Integer> stuAD=new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> AD=new ArrayDequeSolution<Integer>();
        int N=1000;
        StringBuilder wrongMsg=new StringBuilder();
        for(int i=0;i<N;i++) {
            int opNum = StdRandom.uniform(0, 5);
             if(opNum==0){
                 testAddFirst(stuAD,AD,wrongMsg);
             }
             if(opNum==1){
                 /**call addLast,then compare size.*/
                 testAddLast(stuAD,AD,wrongMsg);
             }
             if(opNum==2&&!stuAD.isEmpty()&&!AD.isEmpty()){
                 //call removeFirst,then compare return value.
                 testRemoveFirst(stuAD,AD,wrongMsg);
             }
            if(opNum==3&&!stuAD.isEmpty()&&!AD.isEmpty()){
                //call removeLast,then compare return value.
                testRemoveLast(stuAD,AD,wrongMsg);
            }
            if(opNum==4&&!stuAD.isEmpty()&&!AD.isEmpty()){
                testGet(stuAD,AD,wrongMsg);
            }
        }
    }
}

