package Laba2;
import java.util.Scanner;
public class Vector {


    private double[] array;

    public Vector(int len)
    {
        array = new double[len];

    }

    public void InputFromConsole(){
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < array.length; i++) {
            try {
                System.out.print("Введите элемент под индексом " + i + ": ");
                array[i] = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                i--; // Repeat the loop for the same index
                scanner.nextLine(); // Clear the invalid input from the buffer
            }
        }
    }

    public double[] getArray(){
        return array;
    }

    public int getSize(){
        return array.length;

    }

    public void setElement(int index, double value) {
        array[index] = value;
    }

    public double getElement(int index) {
        return array[index];
    }



    public void sumVectors(Vector vector2) {
        if (getSize() == vector2.getSize()) {
            for (int i = 0; i < getSize(); i++) {
                array[i] += vector2.getElement(i);
            }
        } else {
            System.out.println("Vector dimensions do not match. Cannot perform addition.");
        }
    }

    public double calculateScalarProduct(Vector vector2) {
        if (getSize() != vector2.getSize()) {
            System.out.println("Vector dimensions do not match. Cannot calculate scalar product.");
            return 0; // Assuming scalar product is 0 when dimensions do not match
        }

        double result = 0;
        for (int i = 0; i < getSize(); i++) {
            result += array[i] * vector2.getElement(i);
        }

        return result;
    }

    public void changeElement(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите индекс элемента для замены ");
            int index = scanner.nextInt();

            if (index >= 0 && index < array.length) {
                System.out.print("Введите новый элемент ");
                double newValue = scanner.nextDouble();
                setElement(index, newValue);
            } else {
                System.out.println("Не правильный индекс. Введите корректный индекс");
            }
        } catch (Exception e) {
            System.out.println("Неправильный ввод. Введите корректный индекс и элемент");
        }

    }

    public double getElementFromIndex(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите индекс элемента, значение которого хотите узнать ");
            int index = scanner.nextInt();

            if (index >= 0 && index < array.length) {
                return getElement(index);
            } else {
                System.out.println("Не правильный индекс. Введите корректный индекс");
            }
        } catch (Exception e) {
            System.out.println("Неправильный ввод. Введите корректный индекс");
        }
        return 0;
    }

    public void sort(){
        for (int i = 0; i<array.length-1; i++){
            for (int j = 0; j < array.length - 1- i; j++){
                if (array[j] > array[j+1]){
                    double temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    public void vectorNum(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите число на которое умножите вектор ");
            double scalar = scanner.nextDouble();
            for (int i = 0; i < getSize(); i++) {
                array[i] *= scalar;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input from the buffer
        }
    }

    public double findMin() {
        if (array.length == 0) {
            throw new IllegalStateException("Вектор пустой");
        }

        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public double evklidNorm(){
        double sumOfSquares = 0;
        for(double el : array){
            sumOfSquares += el * el;

        }
        return Math.sqrt(sumOfSquares);
    }
    public double findMax() {
        if (array.length == 0) {
            throw new IllegalStateException("Вектор пустой");
        }

        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static void addVectors(Vector vector1, Vector vector2) {
        if (vector1.getSize() != vector2.getSize()) {
            System.out.println("Размерности векторов не совпадают");
            return;
        }

        for (int i = 0; i < vector1.getSize(); i++) {
            vector1.setElement(i, vector1.getElement(i) + vector2.getElement(i));
        }
    }

    public static double calculateScalar(Vector vector1, Vector vector2) {
        if (vector1.getSize() != vector2.getSize()) {
            System.out.println("Размерности векторов не совпадают");
            return 0;
        }

        double result = 0;
        for (int i = 0; i < vector1.getSize(); i++) {
            result += vector1.getElement(i) * vector2.getElement(i);
        }

        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Вектор { ");
        for (int i = 0; i < array.length; i++){
            stringBuilder.append(array[i]);
            if (i < array.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }


}
