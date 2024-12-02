package day5.Tugas;

public class Product extends ParentTransaction {
    private int[] stock;
    private Float[] price;

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

    @Override
    public int[] getId() {
        return super.getId();
    }

    @Override
    public String[] getName() {
        return super.getName();
    }

    @Override
    public void setId(int[] id) {
        super.setId(id);
    }

    @Override
    public void setName(String[] name) {
        super.setName(name);
    }

    public void display(String name) {
        super.display(name);
        System.out.println("Name\t\tStock\tPrice");
        for (int i = 0; i < getName().length; i++) {
            if (getStock()[i] > 0) {
                System.out.println(
                        i + 1 + ". " + getName()[i] + "\t" + getStock()[i] + "\t" + getPrice()[i]);
            }
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

}
