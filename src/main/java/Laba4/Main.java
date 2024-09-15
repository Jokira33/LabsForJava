package Laba4;
import java.io.*;
import java.util.Scanner;
public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static Info[] array;
    public static void main(String[] s) {

        while (array == null) {
            try {
                System.out.println(" СОЗДАНИЕ ОБЪЕКТОВ ");
                System.out.print("Введите размерность массива: ");
                int count = Integer.parseInt(scan.nextLine());
                array = new Info[count];
            } catch (NumberFormatException e) {
                System.out.println("Ошибка");
                System.out.println("Неверный формат ввода\n");
            } catch (NegativeArraySizeException e) {
                System.out.println("Ошибка");
                System.out.println("Размерность не может быть отрицательной\n");
            }
        }
        for (int i = 0; i < array.length;) {
            System.out.println("1. Склад");
            System.out.println("2. Коробка");
            System.out.printf("Выберите тип %d объекта ", i);
            String name  = scan.nextLine();
            System.out.println();
            switch (name) {
                case "1":
                    try {
                        System.out.print("Введите название склада ");
                        name = scan.nextLine();
                        System.out.print("Введите количество элементов: ");
                        int count = Integer.parseInt(scan.nextLine());
                        System.out.print("Введите цену обьекта ");
                        int introduce = Integer.parseInt(scan.nextLine());

                        array[i] = new Store(count, name, introduce);
                        i++;
                        System.out.println("Объект создан\n");
                    }  catch (NumberFormatException e) {
                        System.out.println("Ошибка");
                        System.out.println("Неверный формат ввода\n");
                    }  catch (RuntimeException e) {
                        System.out.println("Ошибка");
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Введите название коробки: ");
                        name = scan.nextLine();
                        System.out.print("Введите количество элементов: ");
                        int count = Integer.parseInt(scan.nextLine());
                        System.out.print("Введите цену обьекта: ");
                        int introduce = Integer.parseInt(scan.nextLine());

                        array[i] = new Box(count, name, introduce);
                        i++;
                        System.out.println("Объект создан\n");
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка");
                        System.out.println("Неверный формат ввода!\n");
                    } catch (RuntimeException  e) {
                        System.out.println("Ошибка");
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                default:
                    System.out.println("Ошибка");

                    break;
            }
        }
        boolean end = false;
        while (!end) {
            System.out.println("1. Вывод массива в консоль");
            System.out.println("2. Работа с байтовым потоком");
            System.out.println("3. Работа с текстовым потоком");
            System.out.println("4. Сериализация");
            System.out.println("5. Форматный ввод/вывод");
            System.out.println("0. Выход из программы");
            System.out.print("Выберите пункт меню: ");
            switch (scan.nextLine()) {
                case "1":
                    PrintOutArray(array);
                    break;

                case "2":

                    System.out.println("1. Записать массив в байтовый поток");
                    System.out.println("2. Считать массив из байтового потока");
                    System.out.print("Выберите пункт меню: ");
                    String menu = scan.nextLine();
                    if (menu.equals("1")) {
                        try {
                            binaryOutput();
                            System.out.println("Записано в байтовый поток");
                        } catch (IOException e) {

                            System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                        }
                    } else if (menu.equals("2")) {
                        try {
                            array = binaryInput();
                            System.out.println("Считалось из байтового потока");
                        } catch (IOException e) {

                            System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                        }
                    } else {
                        System.out.println("ошибка");

                    }
                    break;
                case "3":

                    System.out.println("1. Записать массив в текстовый поток");
                    System.out.println("2. Считать массив из текстового потока");
                    System.out.print("Выберите пункт меню: ");
                    switch (scan.nextLine()) {
                        case "1":
                            try {
                                textOutput();
                                System.out.println("Записано в текстовый поток");
                            } catch (IOException e) {

                                System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                            }
                            break;
                        case "2":
                            try {
                                array = textInput();
                                System.out.println("Считалось из текстового потока");
                            } catch (IOException e) {

                                System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                            }
                            break;
                    }
                    break;
                case "4":

                    System.out.println("1. Сериализовать массив");
                    System.out.println("2. Десериализовать массив");
                    System.out.print("Выберите пункт меню: ");
                    switch (scan.nextLine()) {
                        case "1":

                            try {
                                serialize();
                                System.out.println("Сериализовано");
                            } catch (IOException e) {
                                System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                            }
                            break;
                        case "2":
                            try {
                                array = deserialize();
                                System.out.println("Десериализовано");
                            } catch (IOException | ClassNotFoundException e) {
                                System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                            }
                            break;
                    }
                    break;
                case "5":

                    System.out.println("1. Форматный вывод в файл");
                    System.out.println("2. Форматный ввод из файла");
                    System.out.print("Выберите пункт меню: ");
                    switch (scan.nextLine()) {
                        case "1":
                            try {
                                writeFormat();
                                System.out.println("Сделан форматный вывод");
                            } catch (Exception e) {
                                System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                            }
                            break;
                        case "2":
                            try {
                                array = readFormat();
                                System.out.println("Сделан ввод из файла");
                            } catch (Exception e) {
                                System.out.println("Возникла ошибка.\n" + e.getLocalizedMessage());
                            }
                            break;
                    }
                    break;

                case "0":
                    System.out.println("Выход");
                    end = true;
                    break;
                default:
                    System.out.println("Ошибка");
                    System.out.println("Введено не правильное значение!\n");
                    break;
            }
        }



    }
    private static Info[] readFormat() throws IOException {

        System.out.println("\nФорматный ввод");
        File file = CreateNewFile("r", ".txt");
        if (file.exists() && file.canRead()) {
            Scanner in = new Scanner(file);
            Info[] res;
            int arrayLength;
            if (in.hasNextInt()) {
                arrayLength = Integer.parseInt(in.nextLine());
                res = new Info[arrayLength];
                for (int i = 0; i < arrayLength; i++) {
                    res[i] = IOMethods.readFormatInt(in);
                }
                return res;
            }
            in.close();
        }
        throw new IOException();
    }
    private static void writeFormat() throws IOException {
        System.out.println("\nФорматный вывод");
        File file = CreateNewFile("w", ".txt");
        while (!(file.exists() && file.canWrite())) {
            if (file.delete()) {
                if (!file.createNewFile()) {
                    throw new IOException("Ошибка создания файла");
                }
            }
        }
        Writer out = new FileWriter(file);
        out.write(array.length + "\n");
        for (Info el: array) {
            IOMethods.writeFormatInt(el, out);
        }
        try {
            out.close();
        } catch (IOException e) {e.getLocalizedMessage();}
    }
    private static Info[] deserialize() throws IOException, ClassNotFoundException {
        System.out.println("\nДесериализация");
        File file = CreateNewFile("r", ".bin");
        if (file.exists() && file.canRead()) {
            InputStream in = new FileInputStream(file);
            int arrayLength = in.read();
            Info[] res = new Info[arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                res[i] = IOMethods.deserializeInt(in);
            }
            try {
                in.close();
            } catch (IOException ignored) {}
            return res;
        }
        else { throw new IOException(); }
    }
    private static void serialize() throws IOException {
        System.out.println("\nСериализация");
        File file = CreateNewFile("w", ".bin");
        OutputStream out = new FileOutputStream(file);
        out.write(array.length);
        for (Info el: array) {
            IOMethods.serializeInt(el, out);
        }
        try {
            out.close();
        } catch (IOException ignored) {}
    }
    private static void binaryOutput() throws IOException {
        System.out.println("\nБинарный вывод");
        File file = CreateNewFile("w", ".bin");
        OutputStream out = new FileOutputStream(file);
        out.write(array.length);
        for (Info el: array) {
            IOMethods.outputInt(el, out);
        }
        try {
            out.close();
        } catch (IOException ignored) {}


    }
    private static File CreateNewFile(String way, String type) throws IOException {
        System.out.print("Введите название файла: ");
        String fileName = scan.nextLine();
        File directory = new File("./test/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(directory, fileName + type);
        if (way.equals("w")) {
            file.createNewFile();
            while (!(file.exists() && file.canWrite())) {
                if (file.delete()) {
                    if (!file.createNewFile()) {
                        throw new IOException("Ошибка создания файла");
                    }
                }
            }
        }
        else {
            if (file.exists()) {
                while(!file.canRead()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {}
                }
            }
            else {
                throw new IOException("Ошибка открытия файла");
            }
        }
        return file;
    }
    private static Info[] binaryInput() throws IOException {
        System.out.println("\nБинарный ввод");
        File file = CreateNewFile("r", ".bin");
        if (file.exists() && file.canRead()) {
            InputStream in = new FileInputStream(file);
            int arrayLength = in.read();
            Info[] res = new Info[arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                res[i] = IOMethods.inputInt(in);
            }

            try {
                in.close();
            } catch (IOException ignored) {}
            return res;
        }
        else { throw new IOException(); }


    }
    private static Info[] textInput() throws IOException {
        System.out.println("\nТекстовыйй ввод");
        File file = CreateNewFile("r", ".txt");
        if (file.exists() && file.canRead()) {
            Reader in = new FileReader(file);
            StreamTokenizer stream = new StreamTokenizer(in);
            stream.nextToken();
            Info[] res;
            int arrayLength;
            if (stream.ttype == StreamTokenizer.TT_NUMBER) {
                arrayLength = (int)stream.nval;
                res = new Info[arrayLength];
                for (int i = 0; i < arrayLength; i++) {
                    res[i] = IOMethods.readInt(in);
                }
                return res;
            }
            try {
                in.close();
            } catch (IOException e ) {e.getLocalizedMessage();}
        }
        throw new IOException();
    }
    private static void textOutput() throws IOException {
        System.out.println("\nТекстовый вывод");
        File file = CreateNewFile("w", ".txt");
        Writer out = new FileWriter(file);
        out.write(array.length + "\n");
        for (Info el: array) {
            IOMethods.writeInt(el, out);
            out.write("\n");
        }
        try {
            out.close();
        } catch (IOException e) {e.getLocalizedMessage();}
    }
    private static void PrintOutArray(Info[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Объект " + (i + 1));
            System.out.println(array[i].toString());
        }
    }

}