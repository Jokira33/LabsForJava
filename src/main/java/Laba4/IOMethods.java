package Laba4;
import java.io.*;
import java.util.Scanner;
public class IOMethods {

    public static void outputInt(Info o, OutputStream out) throws IOException {
        o.output(out);
    }
    public static Info inputInt(InputStream in) throws IOException {
        int type = in.read();
        int nameLength = in.read();
        byte[] nameByte = new byte[nameLength];

        if (nameLength == in.read(nameByte)) {
            String name = new String(nameByte);
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
        o.write(out);
    }
    public static Info readInt(Reader in) throws IOException {
        if (in.ready()) {
            StreamTokenizer stream = new StreamTokenizer(in);
            stream.nextToken();
            if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                int type = (int) stream.nval;
                stream.nextToken();
                if (stream.ttype == StreamTokenizer.TT_WORD || stream.ttype == StreamTokenizer.TT_NUMBER) {
                    String name = "";
                    if (stream.sval == null) {
                        name = String.valueOf(stream.nval);
                    }
                    else {
                        name = stream.sval;
                    }
                    stream.nextToken();
                    if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                        int introduce = (int)stream.nval;
                        stream.nextToken();
                        if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                            int length = (int)stream.nval;
                            int[] array = new int[length];
                            for (int i = 0; i < array.length; i++) {
                                stream.nextToken();
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
        String name = "";
        int len = 0;
        int[] elements;
        int introduce = 0;
        String nextLine;

        if (in.hasNextLine()) {
            nextLine = in.nextLine();
            name = nextLine.substring("Название обьекта: '".length(), nextLine.length() - 1);
        }

        in.nextLine();

        if (in.hasNextLine()) {
            nextLine = in.nextLine();
            len = Integer.parseInt(nextLine.substring("Количество обьектов: '".length(), nextLine.length() - 1));
        }

        in.nextLine();

        elements = new int[len];
        if (in.hasNextLine()) {
            String[] pages = in.nextLine().split(" ");
            for (int i = 0; i < len; i++) {
                elements[i] = Integer.parseInt(pages[i]);
            }
        }

        if (in.hasNextLine()) {
            nextLine = in.nextLine();
            introduce = Integer.parseInt(nextLine.substring("Цена обьекта '".length(), nextLine.length() - 1));
        }
        return new Box(elements.length, name, introduce, elements);
    }
}
