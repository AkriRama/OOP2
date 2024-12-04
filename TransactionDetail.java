package day5.Tugas;

import day5.Tugas.repository.IndexInterface;
import day5.Tugas.utils.FormatCurrency;

public class TransactionDetail extends Transactions implements IndexInterface {
    private String[][] transactionDetail = new String[50][5];
    private Float[] totalPriceItem;

    public String[][] getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String[][] transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public Float[] getTotalPriceItem() {
        return totalPriceItem;
    }

    public void setTotalPriceItem(Float[] totalPriceItem) {
        this.totalPriceItem = totalPriceItem;
    }

    public void countTotalPriceItem(String[] itemBuy, int[] quantity, Product product) {
        Float[] total = new Float[itemBuy.length];

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
            int[] quantity, int transactionId) {
        String[][] listTransactionDetail = getTransactionDetail();
        int transactionLength = getIndex() + itemBuy.length;

        int j = 0;
        for (int i = getIndex(); i < transactionLength; i++) {
            listTransactionDetail[i][0] = String.valueOf(userId);
            listTransactionDetail[i][1] = itemBuy[j];
            listTransactionDetail[i][2] = String.valueOf(quantity[j]);
            listTransactionDetail[i][3] = String.valueOf(getTotalPriceItem()[j]);
            listTransactionDetail[i][4] = String.valueOf(transactionId);
            j++;
        }
        this.transactionDetail = listTransactionDetail;
    }

    @Override
    public int getIndex() {
        int n = 0;
        for (String[] list : getTransactionDetail()) {
            for (String i : list) {
                if (i == null) {
                    return n;
                }
            }
            n++;
        }
        return 1;
    }

    public void display() {
        System.out.println("=== List Transaction Detail ===");
        if (getIndex() == 0) {
            System.out.println("NO TRANSACTION DETAIL HISTORY");
        } else {
            System.out.println("Transaction ID\tName\t\tQuantity\tTotal Price Item");
            for (int i = 0; i < getIndex(); i++) {
                System.out.println(
                        getTransactionDetail()[i][4] + "\t\t"
                                + getTransactionDetail()[i][1]
                                + "\t\t"
                                + getTransactionDetail()[i][2] + "\t\t" +
                                FormatCurrency.setFormatCurrency(Float.parseFloat(getTransactionDetail()[i][3])));
            }
        }
    }

}
