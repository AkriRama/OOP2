package day5.Tugas;

import day5.Tugas.repository.IndexInterface;

public class User extends Transactions implements IndexInterface {
    private String[] name;
    private String[] password;
    private String[] role;
    private String userF;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getPassword() {
        return password;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    public String getUserF() {
        return userF;
    }

    public void setUserF(String userF) {
        this.userF = userF;
    }

    // METHOD BOOLEAN TO FIND VALID USER (ADMIN AND USER)
    public boolean isValid(String passwordF) {
        for (int i = 0; i < getName().length; i++) {
            if (getName()[i].equalsIgnoreCase(userF) && this.password[i].equalsIgnoreCase(passwordF)) {
                return true;
            }
        }
        return false;
    }

    // METHOD BOOLEAN TO FIND VALID ADMIN
    public boolean isValid() {
        for (int i = 0; i < getName().length; i++) {
            if (getName()[i].equalsIgnoreCase(userF) && getRole()[i].equalsIgnoreCase("Admin")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getIndex() {
        int n = 0;
        for (String i : getName()) {
            if (i.equalsIgnoreCase(getUserF())) {
                return n;
            }
            n++;
        }
        return 0;
    }

}
