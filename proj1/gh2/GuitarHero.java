package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
public class GuitarHero {
    public static final double CONCERT = 440.0;
    public GuitarString[] buffer;

    public GuitarHero(){
        GuitarString[] buffer1=new GuitarString [37];
        for(int i=0;i<37;i++){
            buffer1[i]=new GuitarString(CONCERT* Math.pow(2, (i-3.0) / 12.0));
        }
        buffer=buffer1;
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarHero chenzhen=new GuitarHero();
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
                char key = StdDraw.nextKeyTyped();
                int keynum=keyboard.indexOf(key);
                if(keynum>0)
                    chenzhen.buffer[keynum].pluck();
            }

            /* compute the superposition of samples */
            double sample=0.0;
            for(int i=0;i<chenzhen.buffer.length;i++)
                sample+=chenzhen.buffer[i].sample();
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(GuitarString s: chenzhen.buffer)
                s.tic();
        }
    }
}
