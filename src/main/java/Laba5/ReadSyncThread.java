package Laba5;

public class ReadSyncThread implements Runnable{
    private final InfoSynchronizer obj;
    private final Thread cur;

    public ReadSyncThread(InfoSynchronizer p) {
        obj = p;
        cur = Thread.currentThread();
    }

    @Override
    public void run() {
        while (obj.canRead()) {
            try {
                obj.read();
            } catch (InterruptedException ignored) { }
        }
        cur.interrupt();
    }
}
