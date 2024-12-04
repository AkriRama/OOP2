package day5.Tugas;

import java.util.Scanner;

public class OOPTask {
    // Products Array
    static String[] listItems = new String[100];
    static int[] listStockItems = new int[100];
    static Float[] listPriceItems = new Float[100];

    // PaymentMethods Array
    static String[] listPaymentMethods = { "cash", "bank transfer", "e-wallet" };
    static Float[] listFeePaymentMethods = { 2f, 1.5f, 3f };

    // MENU ARRAY
    static String[] listMenus = { "SEE PRODUCTS", "DO TRANSACTIONS",
            "HISTORY TRANSACTION",
            "HISTORY TRANSACTION DETAIL", "EXIT OR LOGOUT" };

    // USER ARRAY
    static String[] listUsers = { "Fahri", "Rizal", "Adi", "Rani", "Ridwan" };
    static String[] listPasswords = { "Fahri", "Rizal", "Adi", "Rani", "Ridwan" };
    static String[] listRoles = { "admin", "user", "user", "user", "user" };

    static Scanner in = new Scanner(System.in);
    static String userF;

    public static void main(String[] args) {
        loginProccess();
    }

    public static void loginProccess() {
        User user = new User();
        user.setName(listUsers);
        user.setPassword(listPasswords);
        user.setRole(listRoles);

        boolean isValid = false;
        while (!isValid) {
            System.out.print("Masukkan Username : ");
            userF = in.nextLine();
            user.setUserF(userF);
            System.out.print("Masukkan Password : ");
            String passwordF = in.nextLine();

            if (user.isValid(passwordF)) {
                System.out.println("Login Success. Hi, user " + userF);
                displayMenu(user);
            } else {
                System.out.println("Username or Password is Invalid. Please, try again!!!");
            }
        }

    }

    public static void itemIn() {
        listItems[0] = "Item 1";
        listItems[1] = "Item 2";
        listItems[2] = "Item 3";
        listItems[3] = "Item 4";
        listItems[4] = "Item 4";

        listStockItems[0] = 10;
        listStockItems[1] = 9;
        listStockItems[2] = 5;
        listStockItems[3] = 4;
        listStockItems[4] = 7;

        listPriceItems[0] = 10000f;
        listPriceItems[1] = 20000f;
        listPriceItems[2] = 30000f;
        listPriceItems[3] = 25000f;
        listPriceItems[4] = 55000f;
    }

    // METHOD VOID DISPLAY MENU
    public static void displayMenu(User user) {
        itemIn();
        Product product = new Product();
        product.setName(listItems);
        product.setStock(listStockItems);
        product.setPrice(listPriceItems);

        Transaction transaction = new Transaction();
        TransactionDetail transactionDetail = new TransactionDetail();
        PaymentMethod paymentMethods = new PaymentMethod();

        paymentMethods.setName(listPaymentMethods);
        paymentMethods.setFee(listFeePaymentMethods);

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
                    product.display();
                    if (user.isValid()) {
                        displayManage(product);
                    }
                    hasMenu = backToMenu();
                    break;
                case 2:
                    displayProccessTransaction(user, product, transaction, transactionDetail, paymentMethods);
                    hasMenu = backToMenu();
                    break;
                case 3:
                    transaction.display(paymentMethods);
                    hasMenu = backToMenu();
                    break;
                case 4:
                    transactionDetail.display();
                    hasMenu = backToMenu();
                    break;
                case 0:
                    hasMenu = false;
                    break;
                default:
                    System.out.println("Invalid Menu!!!\nTry again!");
                    break;
            }

        }
    }

    // METHOD BACK TO MENU
    public static boolean backToMenu() {
        System.out.print("Input '0' to back to menu : ");
        int input = Integer.valueOf(in.nextLine());
        if (input == 0) {
            return true;
        }
        return false;
    }

    // METHOD VOID DISPLAY PROCESS TRANSACTION
    public static void displayProccessTransaction(User user, Product product, Transaction transaction,
            TransactionDetail transactionDetail, PaymentMethod paymentMethods) {

        product.display();

        System.out.print("Input all total product want to buy : ");
        int totalProduct = Integer.valueOf(in.nextLine());

        // FROM USER INPUT
        String[] itemBuy = new String[totalProduct];
        int[] quantity = new int[totalProduct];
        inputItem(itemBuy, quantity, totalProduct);

        paymentMethods.display();
        System.out.print("Input Payment Method : ");
        int paymentMethod = Integer.valueOf(in.nextLine());

        float fee = paymentMethods.getFee()[(paymentMethod - 1)];

        transaction.addTransaction(paymentMethod - 1, 2);
        transactionDetail.countTotalPriceItem(itemBuy, quantity, product);
        transactionDetail.addTransactionDetail(user.getIndex(),
                itemBuy, quantity, transaction.getIndex());
        transaction.updateTotalTransaction(fee, transactionDetail);

        transaction.display(transaction, transactionDetail, paymentMethods, product,
                "Completed", userF,
                transaction.getIndex());
    }

    // METHOD VOID INPUT ITEM
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

    public static void displayManage(Product product) {
        boolean isValidNumber = false;
        while (!isValidNumber) {
            System.out.print("Input '0' to 'Back To Menu' or '1' to 'Add' Product (Max : " + product.getIndex() + "/"
                    + product.getName().length + ")" + " : ");
            int i = Integer.valueOf(in.nextLine());
            switch (i) {
                case 1:
                    inputProduct(product, 3);
                    break;
                case 0:
                    isValidNumber = true;
                    break;
                default:
                    System.out.println("Invalid Input!!!. Try Again!");
                    break;
            }
        }
    }

    public static void inputProduct(Product product, int n) {
        System.out.print("Input Item's name : ");
        String name = in.nextLine();
        System.out.print("Input Item's stock : ");
        int stock = Integer.valueOf(in.nextLine());
        System.out.print("Input Item's price : ");
        Float price = Float.parseFloat(in.nextLine());

        product.addProduct(name, stock, price);
    }
}
