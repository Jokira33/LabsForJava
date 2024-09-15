package Laba5;

public class CheckThread extends Thread{
    Thread checkedThread;

    public CheckThread(Thread _thread) {
        this.checkedThread = _thread;
    }

    @Override
    public void run() {
        boolean waiting = false;
        while (checkedThread.isAlive()) {
            if (checkedThread.getState() == State.WAITING && !waiting){
                System.out.println(checkedThread.getName() + " is waiting");
                waiting = true;
            } else if (checkedThread.getState() == State.RUNNABLE && waiting) {
                waiting = false;
            }
        }
    }
    @Override
    public void start() {
        super.start();
    }
}
