package day5.Tugas;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import day5.Tugas.repository.IndexInterface;
import day5.Tugas.utils.FormatCurrency;

public class Transaction extends Transactions implements IndexInterface {
    private String[][] transaction = new String[50][6];

    public String[][] getTransaction() {
        return transaction;
    }

    public void setTransaction(String[][] transaction) {
        this.transaction = transaction;
    }

    @Override
    public int getIndex() {
        int n = 0;
        for (String[] list : getTransaction()) {
            for (String i : list) {
                if (i == null) {
                    return n;
                }
            }
            n++;
        }
        return 1;
    }

    public Float sumTotal(TransactionDetail transactionDetail) {
        Float total = 0f;
        for (Float i : transactionDetail.getTotalPriceItem()) {
            total += i;
        }
        return total;
    }

    public Float sumTotal(float totalTransaction, Float fee) {
        return totalTransaction * (fee / 100);
    }

    public Float sumTotal(float totalTransaction, float totalFee) {
        return totalTransaction + totalFee;
    }

    public void addTransaction(int paymentMethodId, int paymentStatusId) {
        String[][] listTransaction = getTransaction();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        int lastTransaction = getIndex();

        listTransaction[lastTransaction][0] = date;
        listTransaction[lastTransaction][1] = String.valueOf(0);
        listTransaction[lastTransaction][2] = String.valueOf(0);
        listTransaction[lastTransaction][3] = String.valueOf(0);
        listTransaction[lastTransaction][4] = String.valueOf(paymentStatusId);
        listTransaction[lastTransaction][5] = String.valueOf(paymentMethodId);
        this.transaction = listTransaction;
    }

    public void updateTotalTransaction(Float fee, TransactionDetail transactionDetail) {
        String[][] listTransaction = getTransaction();

        int lastTransaction = getIndex() - 1;
        float totalTransaction = sumTotal(transactionDetail);
        float totalFee = sumTotal(totalTransaction, fee);

        listTransaction[lastTransaction][1] = String.valueOf(totalTransaction);
        listTransaction[lastTransaction][2] = String.valueOf(totalFee);
        listTransaction[lastTransaction][3] = String.valueOf(sumTotal(totalTransaction, totalFee));
        this.transaction = listTransaction;
    }

    public void display(PaymentMethod paymentMethods) {
        System.out.println("=== List Transaction ===");

        if (getIndex() == 0) {
            System.out.println("NO TRANSACTION HISTORY");
        } else {
            for (int i = 0; i < getIndex(); i++) {
                System.out.println("Transaction Number : " + (i + 1));
                System.out.println("Date\t\t: " + getTransaction()[i][0]);
                System.out.println(
                        "Payment Method\t: " + paymentMethods.getName()[Integer.valueOf(getTransaction()[i][5])]);
                // System.out.println("Payment Status\t: " +
                // listPaymentStatus[Integer.valueOf(listTransactions[i][5])]);
                System.out.println("Total Transaction\t\t: "
                        + FormatCurrency.setFormatCurrency(Float.parseFloat(getTransaction()[i][1])));
                System.out.println("Transaction Fee (Rp)\t\t: "
                        + FormatCurrency.setFormatCurrency(Float.parseFloat(getTransaction()[i][2])));
                System.out.println("Grand Total\t\t\t: "
                        + FormatCurrency.setFormatCurrency(Float.parseFloat(getTransaction()[i][3])));
                System.out.println();
            }
        }
    }

    public void display(Transaction transaction, TransactionDetail transactionDetail, PaymentMethod paymentMethod,
            Product product, String paymentStatus, String userF, int transactionId) {
        int j = 1;

        System.out.println("=====Detail Transaction=====");
        System.out.println("Username\t: " + userF);
        System.out.println("Date\t\t: " + transaction.getTransaction()[transactionId
                - 1][0]);
        System.out.println("Payment Method\t: "
                +
                paymentMethod.getName()[Integer.valueOf(transaction.getTransaction()[transactionId
                        - 1][5])]);
        System.out.println("Payment Status\t: " + paymentStatus);
        System.out.println("Name\t\tQuantity\tTotal Price Item");
        for (int k = 0; k < transactionDetail.getIndex(); k++) {
            if (Integer.valueOf(transactionDetail.getTransactionDetail()[k][4]) == transactionId) {
                System.out.println(
                        j + ". " + transactionDetail.getTransactionDetail()[k][1]
                                + "\t" + transactionDetail.getTransactionDetail()[k][2] + "\t\t" +
                                FormatCurrency.setFormatCurrency(
                                        Float.parseFloat(transactionDetail.getTransactionDetail()[k][3])));
                j++;
            }

        }
        System.out.println("Total Transaction\t\t: "
                + FormatCurrency.setFormatCurrency(Float.parseFloat(getTransaction()[transactionId - 1][1])));
        System.out.println("Transaction Fee (Rp)\t\t: "
                + FormatCurrency.setFormatCurrency(Float.parseFloat(getTransaction()[transactionId - 1][2])));
        System.out.println("Grand Total\t\t\t: "
                + FormatCurrency.setFormatCurrency(Float.parseFloat(getTransaction()[transactionId - 1][3])));
    }

}
