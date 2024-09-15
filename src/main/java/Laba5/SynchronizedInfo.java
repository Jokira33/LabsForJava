package Laba5;
import java.io.OutputStream;
import java.io.Writer;
public class SynchronizedInfo implements Info {
    private final Info packaged;

    public SynchronizedInfo(Info _package) {
        this.packaged = _package;
    }

    @Override
    public synchronized int getElement(int n) {
        return packaged.getElement(n);
    }

    @Override
    public synchronized void setElement(int n, int k) {
        packaged.setElement(n, k);
    }

    @Override
    public synchronized String getName() {
        return packaged.getName();
    }

    @Override
    public synchronized int getPrice() {
        return packaged.getPrice();
    }

    @Override
    public synchronized int calcPages() throws PagesLessZeroException {
        return packaged.calcPages();
    }

    @Override
    public synchronized String toString() {
        return packaged.toString();
    }

    @Override
    public synchronized boolean equals(Object o) {
        return packaged.equals(o);
    }

    @Override
    public synchronized int getLen() {
        return packaged.getLen();
    }


    @Override
    public synchronized void output(OutputStream out) {
        packaged.output(out);
    }

    @Override
    public synchronized void write(Writer out) {
        packaged.write(out);
    }

    @Override
    public synchronized int hashCode() {
        return packaged.hashCode();
    }
}
