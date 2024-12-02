package day5.Tugas;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction extends ParentTransaction {
    private String[][] transaction;

    public String[][] getTransaction() {
        return transaction;
    }

    public void setTransaction(String[][] transaction) {
        this.transaction = transaction;
    }

    @Override
    public void display(String name) {
        super.display(name);
    }

    public int getIndexNotNull() {
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

    public void addTransaction(int paymentMethodId, int paymentStatusId,
            float totalTransaction, float fee, float totalFee, float totalTransactionWithFee) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String[][] listTransaction = getTransaction();

        int lastTransaction = getIndexNotNull();

        listTransaction[lastTransaction][0] = date;
        listTransaction[lastTransaction][1] = String.valueOf(totalTransaction);
        listTransaction[lastTransaction][2] = String.valueOf(fee);
        listTransaction[lastTransaction][3] = String.valueOf(totalFee);
        listTransaction[lastTransaction][4] = String.valueOf(totalTransactionWithFee);
        listTransaction[lastTransaction][5] = String.valueOf(paymentStatusId);
        listTransaction[lastTransaction][6] = String.valueOf(paymentMethodId);
        this.transaction = listTransaction;
    }
}