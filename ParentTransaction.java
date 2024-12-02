package day5.Tugas;

public abstract class ParentTransaction {
    private int[] id;
    private String[] name;

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public void display(String name) {
        System.out.println("=== List " + name + " ===");
    }
}
