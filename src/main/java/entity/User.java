package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a password-protected user for our program.
 */
public class User {

    private final String name;
    private final String password;
    private List<String> info;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.info = new ArrayList<>();
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Getter for info
    public List<String> getInfo() {
        return info;
    }

    // Setter for info (if you want to allow setting it later)
    public void setInfo(List<String> info) {
        this.info = info;
    }
}
