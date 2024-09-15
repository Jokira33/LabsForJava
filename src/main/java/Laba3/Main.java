package Laba3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Info> item = new ArrayList<>();

        boolean key = true;
        while (key == true){

            System.out.println("Выберетите пункт ");
            System.out.println("1 - Создать склад");
            System.out.println("2 - Создать корорбку ");
            System.out.println("3 - Вывести информацию о всех объектах ");
            System.out.println("4 - Посчитать конечную стоимость объекта");
            System.out.println("5 - Найти объекты с одинаковым результатом бизнес метода");
            System.out.println("6 - Разделить объекты одного типа на два массива");
            System.out.println("0 - Конец работы программы");


            String num = scanner.nextLine();

            switch (num){
                case "1":
                    addStoreItem(item, scanner);
                    break;
                case "2":
                    addBoxItem(item, scanner);
                    break;
                case "3":
                    outputItems(item);
                    break;
                case "4":
                    avarageCost(item,scanner);
                    break;
                case "5":
                    findItemsSameResults(item);

                    break;
                case "6":
                    splitArrays(item);
                    break;
                case "0":
                    key = false;
                    System.out.println("Конец работы программы");
                    break;
            }

        }



    }
    private static void addStoreItem(ArrayList<Info> item, Scanner scanner){
        System.out.println("Добавляем объект...");
        try {

            System.out.println("Введите название склада ");
            String name = scanner.nextLine();
            System.out.println("Введите количество элементов на складе ");
            int k = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Введите элементы склада ");
            String[] storeItems = new String[k];
            double[] storePrices = new double[k];
            for (int i = 0; i < k; i++) {
                System.out.println("Введите элемент " + (i + 1) + ": ");
                storeItems[i] = scanner.nextLine();

                System.out.println("Введите цену " + (i + 1) + ": ");
                storePrices[i] = scanner.nextDouble();
                scanner.nextLine();


            }
            System.out.print("Введите количество: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline

            item.add(new Store(storeItems, name, quantity, storePrices));
        }catch (InputMismatchException e) {
            System.out.println("Ошибка. Введите корректное значение");
        } catch (ItemAdditionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    private static void addBoxItem(ArrayList<Info> item, Scanner scanner){
        System.out.println("Добавляем объект...");
        try {
            System.out.println("Введите название коробки");
            String name = scanner.nextLine();
            System.out.println("Введите количество элементов в коробке ");
            int k = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Введите элементы коробки ");
            String[] boxItems = new String[k];
            double[] boxPrices = new double[k];
            for (int i = 0; i < k; i++) {
                System.out.println("Введите элемент " + (i + 1) + ": ");
                boxItems[i] = scanner.nextLine();

                System.out.println("Введите цену " + (i + 1) + ": ");
                boxPrices[i] = scanner.nextDouble();
                scanner.nextLine();


            }
            System.out.print("Введите количество: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline

            item.add(new Box(boxItems, name, quantity, boxPrices));

        }catch (InputMismatchException e) {
            System.out.println("Ошибка. Введите корректное значение");
        } catch (ItemAdditionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    private static void findItemsSameResults(ArrayList<Info> item){

        ArrayList<Info> sameResults = new ArrayList<>();
        for (int i = 0; i < item.size(); i++){
            Info it = item.get(i);
            for (int j = i+1; j < item.size(); j++){
                Info otherIt = item.get(j);
                if (it.averagePrice() == otherIt.averagePrice()){
                    sameResults.add(it);
                    sameResults.add(otherIt);
                    System.out.println(" Объекты с одинаковым результатом конечной стоимости " );
                    outputItems(new ArrayList<>(sameResults));
                } else {
                    System.out.println(" Объектов с одинаковым результатом конечной стоимости не найдено" );
                }
            }
        }

    }

    private static void splitArrays(ArrayList<Info> item){
        ArrayList<Store> stores = new ArrayList<>();
        ArrayList<Box> boxes = new ArrayList<>();

        for(Info ite : item){
            if (ite instanceof Store){
                stores.add((Store) ite);
            } else if (ite instanceof Box){
                boxes.add((Box) ite);
            }
        }
        System.out.println("Склады: ______________________________");
        outputItems(new ArrayList<>(stores));

        System.out.println("Коробки: _____________________________");
        outputItems(new ArrayList<>(boxes));
    }
    private static void avarageCost(ArrayList<Info> item, Scanner scanner){
        System.out.println("Введите название объекта ");
        String name = scanner.nextLine();

        boolean found = false;
        for(Info oneItem : item){
            if(oneItem.getName().equals(name)){
                found = true;
                try {
                    double totCost = oneItem.averagePrice();
                    System.out.println("Конечная стоимость объекта " + name + ": " + totCost);
                } catch (Exception e)   {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
        }
        if(!found){
            System.out.println("Объект с таким именем не найден");
        }
    }

    private static void outputItems(ArrayList<Info> item){
        System.out.println("Все объекты");
        for(Info object : item){
            System.out.println("_________  " + object.getName() + " __________");
            if (object instanceof Store) {
                System.out.println("Тип: Склад");
            } else if (object instanceof Box) {
                System.out.println("Тип: Коробка");
            }
            System.out.println("Элементы: ");
            String[] items = object.getItems();
            double[] prices = object.getPrices();
            for (int i = 0; i < items.length; i++) {
                System.out.println("  Объект: " + items[i] + " - Цена: руб." + prices[i]);
            }

            System.out.println("Количество: " + items.length);
            System.out.println();
        }

    }
}

 class ItemAdditionException extends RuntimeException {
    public ItemAdditionException(String message) {
        super(message);
    }
}