package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int counts=0;
        int N=1000;
        SLList<Integer> L=new SLList<>();
        AList<Integer> Ns=new AList<>();
        AList<Double> times=new AList<>();
        AList<Integer> opCounts=new AList<>();
        int M=10000;
        while(counts<8){
            int callCounts=0;
            for(int i=0;i<N;i++){
                L.addLast(i);
            }
            Stopwatch T=new Stopwatch();
            for(int j=0;j<M;j++){
                L.getLast();
                callCounts++;
            }
            opCounts.addLast(callCounts);
            Ns.addLast(N);
            times.addLast(T.elapsedTime());
            counts++;
            N=N*2;
        }
        printTimingTable(Ns,times,opCounts);
    }

}
