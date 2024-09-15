package Laba5;
import java.io.OutputStream;
import java.io.Writer;
public interface Info {
    int getElement(int n);
    void setElement(int n, int k);
    String getName();
    int getPrice();
    int calcPages() throws PagesLessZeroException;
    boolean equals(Object o);
    int getLen();
    void output(OutputStream out);
    void write(Writer out);
}
