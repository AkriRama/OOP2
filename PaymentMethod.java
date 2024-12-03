package day5.Tugas;

public class PaymentMethod extends Transactions {
    private String[] name;
    private Float[] fee;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public Float[] getFee() {
        return fee;
    }

    public void setFee(Float[] fee) {
        this.fee = fee;
    }

    public void display(String name) {
        super.display(name);
        System.out.println("No\tTransation Fee\t\tName");
        for (int i = 0; i < getName().length; i++) {
            System.out.println(
                    (i + 1) + ". \t" + getFee()[i] + "\t\t\t"
                            + getName()[i]);
        }
    }

}
