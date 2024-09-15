package Laba3;

import java.util.Arrays;
import java.util.Objects;

public class Box implements Info{
    String[] items; //массив обьектов в коробке
    String name; // название коробки
    int quantity; //количество обьектов в коробке
    double[] prices; //массив цен коробок

    public Box(String[] items, String name, int quantity, double[] prices){
        this.items = items;
        this.name = name;
        this.quantity = quantity;
        this.prices= prices;


    }
    public Box(){
        this.items = new String[0];
        this.name = " Коробка ";
        this.quantity = 0;
        this.prices = new double[0];

    }
    public double[] getPrices(){
        return prices;
    }
    public Object getPrice(int index){
        if(index>=0 && index < prices.length){
            return prices[index];
        }else{
            return null;
        }

    }
    public String[] getItems(){
        return items;
    }
    public String getName(){
        return name;
    }
    public int getQuantity(){
        return  quantity;
    }

    @Override


    public int getLen(){
        return items.length;
    }
    public String getItem(int index){
        if (index >= 0 && index < items.length) {
            return items[index];
        } else {
            return null;
        }
    }

    public double averagePrice(){
        int totalItems = 0;
        double totalPrice = 0.0;
        if (items.length == 0){
            System.out.println("Объект с таким именем не найден");
        }
        try {
            for (int i = 0; i < items.length;i++){
                totalPrice += prices[i] * items.length;

            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }  finally {
            System.out.println();
        }

        return totalPrice ;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Box item = (Box) obj;
        return quantity == item.quantity
                && (items == item.items
                || (items != null && Arrays.equals(items, item.getItems()))) && (Objects.equals(name, item.name)
                || (name != null && name.equals(item.getName())));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((items == null) ? 0 : Arrays.hashCode(items));
        result = prime * result + quantity;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Название объекта: ").append(name).append('\n');
        sb.append("кол-во объектов: ").append(quantity).append('\n');
        sb.append("Объекты: ").append('\n');
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i]);
            if (i < items.length - 1) {
                sb.append(", ");
            }
        }
        sb.append('\n');
        return sb.toString();
    }
    @Override
    public void getInfo() {
        System.out.println(" Коробка ");
    }
}

