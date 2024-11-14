package use_case.note.signup;

import entity.UserInter;

public interface SignupUserDataAccessInterface {
    boolean nameExists(String name);
    void save(UserInter user);
}
