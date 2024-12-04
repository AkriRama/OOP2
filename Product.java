package day5.Tugas;

import day5.Tugas.repository.IndexInterface;
import day5.Tugas.utils.FormatCurrency;

public class Product extends Transactions implements IndexInterface {
    private String[] name = new String[100];
    private int[] stock = new int[100];
    private Float[] price = new Float[100];

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public int[] getStock() {
        return stock;
    }

    public void setStock(int[] stock) {
        this.stock = stock;
    }

    public Float[] getPrice() {
        return price;
    }

    public void setPrice(Float[] price) {
        this.price = price;
    }

    public void display() {
        System.out.println("=== List Product ===");

        if (getIndex() > 0) {
            System.out.println("Name\t\tStock\tPrice");
            for (int i = 0; i < getName().length; i++) {
                if (getStock()[i] > 0) {
                    System.out.println(
                            i + 1 + ". " + getName()[i] + "\t" + getStock()[i] + "\t"
                                    + FormatCurrency.setFormatCurrency(getPrice()[i]));
                }
            }
        } else {
            System.out.println("No Product");
        }
    }

    public boolean isAvailableStock(String itemBuy, int quantity) {
        for (int i = 0; i < getName().length; i++) {
            if (getName()[i].equalsIgnoreCase(itemBuy)) {
                return getStock()[i] > 0;
            }
        }
        return false;
    }

    public void updateStockTransaction(String[] itemBuy, int[] quantity) {
        int[] currentStock = getStock();
        for (int i = 0; i < itemBuy.length; i++) {
            for (int j = 0; j < getName().length; j++) {
                if (itemBuy[i].equalsIgnoreCase(getName()[j])) {
                    currentStock[j] -= quantity[i];
                    break;
                }
            }
        }
        setStock(currentStock);
    }

    @Override
    public int getIndex() {
        int n = 0;
        for (String i : getName()) {
            if (i == null) {
                return n;
            }
            n++;
        }
        return 1;
    }

    public void addProduct(String name, int stock, Float price) {
        String[] listName = getName();
        int[] listStock = getStock();
        Float[] listPrice = getPrice();

        int lastProduct = getIndex();
        listName[lastProduct] = name;
        listStock[lastProduct] = stock;
        listPrice[lastProduct] = price;

        setName(listName);
        setStock(listStock);
        setPrice(listPrice);
    }

}
