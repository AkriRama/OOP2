package day5.Tugas;

import java.util.Scanner;

public class OOPTask {
    // Products Array
    static String[] listItems = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
    static int[] listStockItems = { 10, 9, 5, 4, 7 };
    static Float[] listPriceItems = { 10000f, 20000f, 30000f, 25000f, 55000f };

    // PaymentMethods Array
    static String[] listPaymentMethods = { "cash", "bank transfer", "e-wallet" };
    static Float[] listFeePaymentMethods = { 2f, 1.5f, 3f };

    // MENU ARRAY
    static String[] listMenus = { "SEE PRODUCTS", "DO TRANSACTIONS", "HISTORY TRANSACTION", "EXIT OR LOGOUT" };

    // USER ARRAY
    static String[] listUsers = { "Fahri", "Rizal", "Adi", "Rani", "Ridwan" };
    static String[] listPasswords = { "Fahri", "Rizal", "Adi", "Rani", "Ridwan" };

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        loginProccess();
    }

    public static void loginProccess() {
        User user = new User();
        user.setName(listUsers);
        user.setPassword(listPasswords);

        boolean isValid = false;
        while (!isValid) {
            System.out.print("Masukkan Username : ");
            String userF = in.nextLine();
            System.out.print("Masukkan Password : ");
            String passwordF = in.nextLine();

            if (user.isValidUser(userF, passwordF)) {
                System.out.println("Login Success. Hi, user " + userF);
                displayMenu();
            } else {
                System.out.println("Username or Password is Invalid. Please, try again!!!");
            }
        }

    }

    public static void displayMenu() {
        String[][] listTransaction = new String[50][7];
        String[][] listDetailTransaction = new String[50][5];

        int n = 1;
        int menu;
        boolean hasMenu = true;

        while (hasMenu) {
            System.out.println("===MENU===");
            for (String i : listMenus) {
                if (n == listMenus.length) {
                    n = 0;
                }
                System.out.println(n + ". " + i);
                n++;
            }

            System.out.print("Input Menu : ");
            menu = Integer.valueOf(in.nextLine());

            switch (menu) {
                case 1:
                    Product product = new Product();
                    product.display("Product");
                    hasMenu = backToMenu();
                    break;
                case 2:
                    // displayProccessTransaction(listTransaction, listDetailTransaction);
                    hasMenu = backToMenu();
                    break;
                case 3:
                    // displayTransaction(listTransaction);
                    hasMenu = backToMenu();
                    break;
                case 0:
                    // System.exit(0);
                    hasMenu = false;
                    break;
                default:
                    System.out.println("Invalid Menu!!!\nTry again!");
                    break;
            }

        }
    }

    public static boolean backToMenu() {
        System.out.print("Input '0' to back to menu : ");
        int input = Integer.valueOf(in.nextLine());
        if (input == 0) {
            return true;
        }
        return false;
    }

    public static void displayProccessTransaction(Product product, PaymentMethod paymentMethods) {
        Transaction transaction = new Transaction();
        TransactionDetail transactionDetail = new TransactionDetail();
        product.display("Product");

        System.out.print("Input all total product want to buy : ");
        int totalProduct = Integer.valueOf(in.nextLine());

        // FROM USER INPUT
        String[] itemBuy = new String[totalProduct];
        int[] quantity = new int[totalProduct];
        float[] totalPriceItem = new float[itemBuy.length];
        inputItem(itemBuy, quantity, totalProduct);
        for (int i = 0; i < itemBuy.length; i++) {
            if (!isAvailable(itemBuy[i], quantity[i])) {
                itemBuy[i] = "";
                quantity[i] = 0;
            }
            quantity[i] = getReplace(itemBuy[i], quantity[i]);
        }

        paymentMethods.display("Payment Method");
        System.out.print("Input Payment Method : ");
        int paymentMethod = Integer.valueOf(in.nextLine());

        float totalTransaction = sumTotalTransaction(totalPriceItem);
        float fee = listFeePaymentMethods[(paymentMethod - 1)];
        float totalFee = sumTotalFee(totalTransaction, fee);
        float totalTransactionWithFee = sumTotalTransactionWithFee(totalTransaction,
                totalFee);

        transaction.addTransaction(paymentMethod - 1,
                2,
                totalTransaction, fee, totalFee,
                totalTransactionWithFee);
        transactionDetail.addTransactionDetail(getIndex(listUsers, user),
                itemBuy, quantity, totalPriceItem,
                getIndexNotNull(listTransaction));

        displayDetailTransaction(user, listPaymentMethods[paymentMethod - 1],
                itemBuy, quantity,
                totalPriceItem,
                totalTransaction, fee, totalFee, totalTransactionWithFee,
                listPaymentStatus[2]);
    }

    public static void inputItem(String[] itemBuy, int[] quantity, int totalProduct) {
        boolean isFinish = false;
        int n = 0;
        while (!isFinish && n < totalProduct) {
            if (n > 0) {
                System.out.print(
                        "Max Item " + n + "/" + itemBuy.length
                                + ". Do you want to stop add item (input : 'yes') or ");
            }
            System.out.print("Input Item " + (n + 1) + " name : ");
            itemBuy[n] = in.nextLine();
            if (itemBuy[n].equalsIgnoreCase("Yes")) {
                isFinish = true;
            } else {
                if (itemBuy[n].equalsIgnoreCase("")) {
                    quantity[n] = 0;
                } else {
                    System.out.print("Input quantity item " + (n + 1) + " : ");
                    quantity[n] = Integer.valueOf(in.nextLine());
                }
                n++;
            }
        }
    }
}
