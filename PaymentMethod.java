package day5.Tugas;

public class PaymentMethod extends ParentTransaction {
    private Float[] fee;

    public Float[] getFee() {
        return fee;
    }

    public void setFee(Float[] fee) {
        this.fee = fee;
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
        System.out.println("No\tTransation Fee\t\tName");
        for (int i = 0; i < getName().length; i++) {
            System.out.println(
                    (i + 1) + ". \t" + getFee()[i] + "\t\t\t"
                            + getName()[i]);
        }
    }

}
