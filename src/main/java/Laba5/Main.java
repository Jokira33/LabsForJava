package Laba5;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] s) {
        System.out.println("Лабораторная работа 5");
        boolean end = false;
        while (!end) {
            System.out.print("Выберете пункт меню: \n");
            System.out.println("1. Параллельные запись/чтение массива (Задание 1)");
            System.out.println("2. Последовательные запись/чтение массива (Задание 2)");
            System.out.println("3. Последовательные запись/чтение массива (Задание 3)");
            System.out.println("0. Выход из программы");
            Info obj;

            try {
                while (!scan.hasNext()) {
                    Thread.sleep(200);
                }
            } catch (InterruptedException ignored) { }
            String choice = scan.nextLine();
            switch (choice) {
                case "1":
                    obj = CreateEssays();
                    System.out.println("Успешно\n");
                    Thread readThread = new ReadThread(obj);
                    Thread writeThread = new WriteThread(obj);
                    writeThread.start();
                    readThread.start();
                    try {
                        readThread.join();
                    } catch (InterruptedException ignored) {
                        scan.nextLine();
                    }
                    try {
                        writeThread.join();
                    } catch (InterruptedException ignored) {
                    }
                    break;
                case "2":
                    obj = CreateEssays();
                    System.out.println("успешно\n");
                    InfoSynchronizer infoSynchronizer = new InfoSynchronizer(obj);
                    Thread read = new Thread(new ReadSyncThread(infoSynchronizer));
                    Thread write = new Thread(new WriteSyncThread(infoSynchronizer));
                    read.start();
                    write.start();
                    try {
                        read.join();
                    } catch (InterruptedException ignored) {}
                    try {
                        write.join();
                    } catch (InterruptedException ignored) {}
                    break;
                case "3":
                    obj = CreateEssays();
                    System.out.println("УСПЕШНО\n");
                    SynchronizedInfo synchronizedInt = IOMethods.synchronizedInt(obj);

                    Thread thread1 = new WriteThread(synchronizedInt);
                    thread1.setName("WriteThread");
                    Thread thread2 = new ReadThread(synchronizedInt);
                    thread2.setName("ReadThread");

                    Thread checkThread1 = new CheckThread(thread1);
                    Thread checkThread2 = new CheckThread(thread2);

                    thread1.start();
                    thread2.start();

                    checkThread1.start();
                    checkThread2.start();

                    try {
                        thread2.join();
                    } catch (InterruptedException ignored) {}
                    try {
                        thread1.join();
                    } catch (InterruptedException ignored) {}
                    try {
                        checkThread1.join();
                    } catch (InterruptedException ignored) {}
                    try {
                        checkThread2.join();
                    } catch (InterruptedException ignored) {}
                    break;
                case "0":
                    System.out.println("\nЗАВЕРШЕНИЕ РАБОТЫ");
                    end = true;
                    break;
                default:
                    System.out.println("\nОштбка");
                    System.out.println("Введено неправильное значение!\n");
                    break;
            }

        }
    }

    private static void PrintOutArray(Info[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("====ЭЛЕМЕНТ #" + (i + 1) + "====");
            System.out.println(array[i].toString());
        }
    }


    private static Info CreateEssays() {
        System.out.println("\n Создание объекта ");
        System.out.print("Введите длину массива: ");
        int length = scan.nextInt();
        return new Store(length);
    }


}