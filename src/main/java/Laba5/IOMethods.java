package Laba5;
import java.io.*;
import java.util.Scanner;
public class IOMethods {
    public static void outputInt(Info o, OutputStream out) throws IOException {
        if (o.getClass().equals(Store.class)) { out.write(1);}
        else { out.write(2);}
        out.write(o.getName().length());
        out.write(o.getName().getBytes());
        out.write(o.getPrice());
        out.write(o.getLen());
        for (int i = 0; i < o.getLen(); i++) { out.write(o.getElement(i)); }
    }
    public static Info inputInt(InputStream in) throws IOException {
        int type = in.read();
        int nameLength = in.read();
        byte[] nameByte = new byte[nameLength];

        if (nameLength == in.read(nameByte)) {
            String name = new String(nameByte);
            nameByte = null;
            int introduce = in.read();
            int arrayLength = in.read();
            int[] array = new int[arrayLength];
            for (int i = 0; i < arrayLength; i++) array[i] = in.read();

            if (type == 1) return new Store(arrayLength, name, introduce, array);
            else if (type == 2) return new Box(arrayLength, name, introduce, array);
            else throw new IOException();
        } else throw new IOException();
    }
    public static void writeInt(Info o, Writer out) throws IOException {
        if (o.getClass().equals(Store.class)) { out.write(1 + " ");}
        else { out.write(2 + " ");}
        out.write(o.getName() + " ");
        out.write(o.getPrice() + " ");
        out.write(o.getLen() + " ");
        for (int i = 0; i < o.getLen(); i++) { out.write(o.getElement(i) + " "); }
    }

    public static Info readInt(Reader in) throws IOException {
        if (in.ready()) {
            StreamTokenizer stream = new StreamTokenizer(in);
            int currentToken = stream.nextToken();
            if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                int type = (int) stream.nval;
                currentToken = stream.nextToken();
                if (stream.ttype == StreamTokenizer.TT_WORD) {
                    String name = stream.sval;
                    currentToken = stream.nextToken();
                    if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                        int introduce = (int)stream.nval;
                        currentToken = stream.nextToken();
                        if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                            int length = (int)stream.nval;
                            int[] array = new int[length];
                            for (int i = 0; i < array.length; i++) {
                                currentToken = stream.nextToken();
                                if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                                    array[i] = (int)stream.nval;
                                }
                            }
                            if (type == 1) return new Store(length, name, introduce, array);
                            else if (type == 2) return new Box(length, name, introduce, array);
                            else throw new IOException();
                        }
                    }
                }
            }
        }
        else throw new IOException("Поток не задан!");
        throw new IOException("Ошибка чтения!");
    }

    public static void serializeInt(Info o, OutputStream out) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(o);
    }

    public static Info deserializeInt(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objIn = new ObjectInputStream(in);
        return (Info)objIn.readObject();
    }

    public static void writeFormatInt(Info o, Writer out) throws IOException {
        out.write("Название обьекта: '" + o.getName() + "'\n");
//        out.write( "Тип : " + o.getClass() +"\n");
        if (o instanceof Store) {
            out.write("Тип: Склад" + "\n");
        } else if (o instanceof Box) {
            out.write("Тип: Коробка" + "\n");
        }
        out.write( "Количество объектов: '" + o.getLen() + "'\n");
        out.write( "Номера обьектов: \n");
        for (int i = 0; i < o.getLen(); i++) {
            out.write( o.getElement(i) + "  ");
        }
        out.write("\n");
        out.write( "Цена обьекта'" + o.getPrice() + "'\n");
    }

    public static Info readFormatInt(Scanner in) {
        in.useDelimiter("'");
        while (in.hasNext()) { in.next(); }
        return null;
    }

    public static SynchronizedInfo synchronizedInt(Info i) {
        return new SynchronizedInfo(i);
    }
}
