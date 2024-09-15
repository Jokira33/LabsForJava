package Laba5;

public class ReadThread extends Thread{
    private final Info object;


    public ReadThread(Info _obj) {
        if (_obj != null) {
            object = _obj;
        }
        else throw new IllegalArgumentException();
    }
    @Override
    public void run() {
        for (int i = 0; i < object.getLen(); i++) {
            if (Thread.currentThread().getState() == State.WAITING) {
                System.out.println(Thread.currentThread().getName() + "is waiting");
            }
            System.out.printf("Read: " + object.getElement(i) + " from position " + i + "\n");
        }
    }
    @Override
    public void start() {
        System.out.println(getClass().getName() + " started");
        super.start();
    }
}
