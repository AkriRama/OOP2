package day5.Tugas;

public class TransactionDetail {
    private String[][] transactionDetail;
    private User[] user;
    private String[] itemName;
    private int[] quantity;
    private Float[] totalPriceItem;

    public User[] getUser() {
        return user;
    }

    public void setUser(User[] user) {
        this.user = user;
    }

    public String[] getItemName() {
        return itemName;
    }

    public void setItemName(String[] itemName) {
        this.itemName = itemName;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public Float[] getTotalPriceItem() {
        return totalPriceItem;
    }

    public void setTotalPriceItem(Float[] totalPriceItem) {
        this.totalPriceItem = totalPriceItem;
    }

    public void countTotalPriceItem(String[] itemBuy, int[] quantity) {
        Float[] total = new Float[itemBuy.length];
        Product product = new Product();

        for (int i = 0; i < itemBuy.length; i++) {
            for (int j = 0; j < product.getName().length; j++) {
                if (itemBuy[i].equalsIgnoreCase(product.getName()[j])) {
                    total[i] = quantity[i] * product.getPrice()[j];
                }
            }
        }
        this.totalPriceItem = total;
    }

    public void addTransactionDetail(int userId, String[] itemBuy,
            int[] quantity, float[] totalPriceItem, int transactionId) {

        for (int i = 0; i < itemBuy.length; i++) {
            for (int j = 0; j < itemBuy.length; j++) {
                transactionDetail[i][0] = String.valueOf(userId);
                transactionDetail[i][1] = itemBuy[j];
                transactionDetail[i][2] = String.valueOf(quantity[j]);
                transactionDetail[i][3] = String.valueOf(totalPriceItem[j]);
                transactionDetail[i][4] = String.valueOf(transactionId);
            }
        }
    }

    public String[][] getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String[][] transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

}
