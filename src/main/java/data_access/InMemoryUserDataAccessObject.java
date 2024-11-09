package data_access;

import entity.UserInter;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface{

    private final Map<String, UserInter> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean nameExists(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(UserInter user) {

        users.put(user.getName(), user);
    }

}
