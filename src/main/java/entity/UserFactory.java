package entity;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserFactory implements UserFactoryInter{
    public User createUser(String username, String password, JSONObject info) {
        return new User(username, password, info);
    }

}
