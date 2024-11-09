package use_case.signup;

import entity.UserInter;

public interface SignupUserDataAccessInterface {
    boolean nameExists(String name);
    void save(UserInter user);
}
