package entity;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * The representation of a password-protected user for our program.
 */
public class User {

    private final String name;
    private final String password;
    private Map<String, Object> info;

    public User(String name, String password, JSONObject info) {
        this.name = name;
        this.password = password;
        this.info = info.toMap();
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
    public Map<String, Object> getInfo() {
        return info;
    }

    // Setter for info (if you want to allow setting it later)
    public void setInfo(Map<String, Object > info) {
        this.info = info;
    }

}
