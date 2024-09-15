package Laba5;
import java.util.Random;
public class WriteSyncThread implements Runnable{
    private final InfoSynchronizer obj;
    private final Thread cur;

    public WriteSyncThread(InfoSynchronizer p) {
        obj = p;
        cur = Thread.currentThread();
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (obj.canWrite()) try {
            obj.write(rand.nextInt(1, 10));
        } catch (InterruptedException ignored) { }
        cur.interrupt();
    }
}
