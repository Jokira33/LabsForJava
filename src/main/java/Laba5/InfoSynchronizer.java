package Laba5;

public class InfoSynchronizer {
    private final Info obj;
    private volatile int current = 0;
    private final Object isLocked = new Object();
    private boolean set = false;

    public InfoSynchronizer(Info i) {
        this.obj = i;
    }

    public int read() throws InterruptedException {
        int val;
        synchronized(isLocked) {
            if (!canRead()) throw new InterruptedException();
            while (!set)
                isLocked.wait();
            val = obj.getElement(current++);
            System.out.printf("Read: " + val + " from position " + (current - 1) + "\n");
            set = false;
            isLocked.notifyAll();
        }
        return val;
    }

    public void write(int val) throws InterruptedException {
        synchronized(isLocked) {
            if (!canWrite()) throw new InterruptedException();
            while (set)
                isLocked.wait();
            obj.setElement(current, val);
            System.out.printf("Write: " + val + " to position " + current + "\n");
            set = true;
            isLocked.notifyAll();
        }
    }

    public boolean canRead() {
        return current < obj.getLen();
    }

    public boolean canWrite() {
        return (!set && current < obj.getLen()) || (set && current < obj.getLen() - 1);
    }
}
