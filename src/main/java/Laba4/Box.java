package Laba4;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;
public class Box implements Info, Serializable{

    private final int[] items;
    private final String name;
    private final int price;



    public Box(int _count, String _name, int _introduce, int[] array ) {
        items = Arrays.copyOf(array, _count);

        this.name = _name;
        this.price = _introduce;


    }

    public Box(int _count, String _name, int _introduce) {
        if (_count >= 0) {
            items = new int[_count];
            int check;
            Scanner scan = new Scanner(System.in);
            for (int i = 0; i < items.length;) {
                System.out.printf("Введите номер %d объекта: ", i+1);
                check = Integer.parseInt(scan.nextLine());
                if (check < 0) { throw new RuntimeException("Объектов не может быть ноль"); }
                else {
                    items[i] = check;
                    i++;
                }
            }
        }
        else { throw new RuntimeException("Размер массива не может быть отрицательным!"); }

        if (!_name.isEmpty()) { this.name = _name; }
        else { throw new RuntimeException("Имя не может быть пустым!"); }

        if (_introduce >= 0) { this.price = _introduce; }
        else { throw new RuntimeException("Цены не могут быть отрицательны!"); }
    }

    @Override
    public int getElement(int n) {
        if ( (n >= 0) && (n < items.length) ) { return items[n]; }
        else { throw new IndexOutOfBoundsException(); }
    }

    @Override
    public void setElement(int n, int k) {
        if ( (n >= 0) && (n < items.length) ) { items[n] = k; }
        else { throw new IndexOutOfBoundsException(); }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int calcPages() throws PagesLessZeroException {
        int res = 0;
        int midTerm = 0;
        for (int page: items) {
            midTerm = page - price;
            if (midTerm > -1) { res += midTerm; }
            else { throw new PagesLessZeroException("Цена меньше нуля!"); }
        }
        return res;
    }


    @Override
    public String toString() {
        String res = "Название: " + name + "\n";
        res += "Тип: Коробка\n";
        res += "Количество объектов: " + items.length + "\n";
        res += "Номера обьектов в коробке:\n";
        for (int page : items) res += page + " , " ;

        res += "\nЦена коробки " + price + "\n";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            Box cmp = (Box)o;
            if (name.equals(cmp.name)) {
                if (price == cmp.getPrice()) {
                    if (items.length == cmp.getLen()) {
                        for (int i = 0; i < items.length; ) {
                            try {
                                if (items[i] == cmp.getElement(i)) { i++; }
                                else { return false; }
                            }
                            catch (Exception e) { return false; }
                        } return true;
                    } else { return false; }
                } else { return false; }
            } else { return false; }
        } else { return false; }
    }

    @Override
    public int getLen() {
        return items.length;
    }

    @Override
    public void output(OutputStream out) {
        try {
            out.write(2);
            out.write(name.length());
            out.write(name.getBytes());
            out.write(price);
            out.write(items.length);
            for (int page : items) { out.write(page); }
        } catch (IOException e) { throw new RuntimeException(e.getMessage()); }
    }

    @Override
    public void write(Writer out) {
        try {
            out.write(2 + " ");
            out.write(name + " ");
            out.write(price + " ");
            out.write(items.length + " ");
            for (int page : items) { out.write(page + " "); }
        }
        catch (IOException e) {throw new RuntimeException(e.getMessage()); }
    }

    @Override
    public int hashCode() {
        return price + name.hashCode() + items.length * 1000;
    }
}
