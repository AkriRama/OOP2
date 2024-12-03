package day5.Tugas;

public abstract class Transactions {
    private int[] id;

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }

    public void display(String name) {
        System.out.println("=== List " + name + " ===");
    }
}
