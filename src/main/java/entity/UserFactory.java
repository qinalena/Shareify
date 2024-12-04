package entity;

public class UserFactory implements UserFactoryInter {
    @Override
    public User createUser(String username, String password) {
        return new User(username, password);
    }
}
