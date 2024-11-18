package entity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserFactoryInter {
    User createUser(String username, String password, JSONObject info);
}
