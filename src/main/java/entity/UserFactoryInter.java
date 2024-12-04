package entity;

public interface UserFactoryInter {
    /**
     * Create a user.
     * @param username the username of the user
     * @param password the password of the user
     * @return User return the user created.
     */
    User createUser(String username, String password);
}
