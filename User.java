package day5.Tugas;

public class User extends ParentTransaction {
    private String[] password;
    private String[] role;

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

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    public boolean isValidUser(String userF, String passwordF) {
        for (int i = 0; i < getName().length; i++) {
            if (getName()[i].equalsIgnoreCase(userF) && this.password[i].equalsIgnoreCase(passwordF)) {
                return true;
            }
        }
        return false;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public int getIndex(String find) {
        int n = 0;
        for (String i : getName()) {
            if (i.equalsIgnoreCase(find)) {
                return n;
            }
            n++;
        }
        return 0;
    }
}
