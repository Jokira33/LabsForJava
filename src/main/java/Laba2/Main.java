package Laba2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите длину вектора");
        int len = in.nextInt();
        Vector vector = new Vector(len);
        vector.InputFromConsole();
        System.out.println(vector);



        boolean stop = false;
        while (!stop) {
            Scanner input = new Scanner(System.in);

            System.out.println("Введите номер задания \n");
            System.out.println("1 Вывести длину вектора ");
            System.out.println("2 Изменить значение вектора");
            System.out.println("3 Получить элемент вектора по индексу");
            System.out.println("4 Поиск мин и макс");
            System.out.println("5 Сортировка по возрастанию");
            System.out.println("6 Евклидовая норма");
            System.out.println("7 умножение вектора на число");
            System.out.println("8 сложение двух векторов");
            System.out.println("9 скалярное произведение векторов");
            System.out.println("0 выход из программы");

            int number = input.nextInt();

            try {
                switch (number) {
                    case 1:
                        System.out.println("Исходный " + vector);
                        System.out.println("Длина вектора: " + vector.getSize());
                        break;
                    case 2:
                        System.out.println("Изменить значение вектора по индексу");
                        System.out.println("Исходный " + vector);
                        vector.changeElement();
                        System.out.println(vector);

                        break;

                    case 3:
                        System.out.println("Исходный " + vector);
                        double elem = vector.getElementFromIndex();
                        System.out.println("Элемент по индексу : " + elem);
                        break;
                    case 4:
                        System.out.println("Исходный " + vector);
                        System.out.println("Минимальный элемент " + vector.findMin());
                        System.out.println("Максимальный элемент " + vector.findMax());

                        break;
                    case 5:

                        System.out.println("Исходный " + vector);
                        vector.sort();
                        System.out.println("Отсортированный по возрастанию " + vector);
                        break;
                    case 6:
                        System.out.println("Исходный " + vector);
                        System.out.println("Евкидова норма вектора " + vector.evklidNorm());
                        break;
                    case 7:
                        System.out.println("Умножение вектора на число: ");
                        System.out.println("Исходный " + vector);
                        vector.vectorNum();
                        System.out.println("Новый " + vector);
                        break;
                    case 8:
                        System.out.println("Сложение двух векторов: \n");
                        System.out.println("Введите первый вектор ");
                        System.out.println("Введите длину вектора");
                        int len1 = in.nextInt();
                        Vector vector1 = new Vector(len1);
                        vector1.InputFromConsole();

                        System.out.println("Введите второй вектор ");
                        System.out.println("Введите длину вектора");
                        int len2 = in.nextInt();
                        Vector vector2 = new Vector(len2);
                        vector2.InputFromConsole();

                        System.out.println("Первый " + vector1);
                        System.out.println("Второй " + vector2);

                        vector1.sumVectors(vector2);

                        System.out.println("Сумма: " + vector1);


                        break;
                    case 9:
                        System.out.println("Скалярное произведение векторов: \n");
                        System.out.println("Введите первый вектор ");
                        System.out.println("Введите длину вектора");
                        int len3 = in.nextInt();
                        Vector vector3 = new Vector(len3);
                        vector3.InputFromConsole();

                        System.out.println("Введите второй вектор ");
                        System.out.println("Введите длину вектора");
                        int len4 = in.nextInt();
                        Vector vector4 = new Vector(len4);
                        vector4.InputFromConsole();

                        System.out.println("Первый " + vector3);
                        System.out.println("Второй " + vector4);

                        double scalarProduct = vector3.calculateScalarProduct(vector4);
                        System.out.println("Скалярное произведение: " + scalarProduct);
                        break;
                    case 0:
                        stop=true;
                        input.close();
                    default:
                        System.out.println("Ошибка. Введите корректный номер ");
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Введите номер задания ");
                System.out.println(e);
            }


        }


    }
//    private static String arrayToString(double[] array) {
//        StringBuilder result = new StringBuilder("{");
//        for (double value : array) {
//            result.append(value).append(", ");
//        }
//        if (result.length() > 1) {
//            result.delete(result.length() - 2, result.length());
//        }
//        result.append("}");
//        return result.toString();
//    }

}