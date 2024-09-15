package Laba5;
import java.util.Random;
public class WriteThread extends Thread{
    private final Info object;
    private final Random rand = new Random();


    public WriteThread(Info _obj) {
        if (_obj != null) {
            object = _obj;
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public void run() {
        for (int i = 0; i < object.getLen(); i++) {
            int l = rand.nextInt(100);
            object.setElement(i, l);
            if (Thread.currentThread().getState() == State.WAITING) {
                System.out.println(Thread.currentThread().getName() + "is waiting");
            }
            System.out.printf("Write: " + l + " to position " + i + "\n");
        }
    }

    @Override
    public void start() {
        System.out.println(getClass().getName() + " started");
        super.start();
    }
}
