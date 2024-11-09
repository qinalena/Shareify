package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String loginError;
    private String password = "";

    public void setUsername(String username) {
        this.username = username;
    }
}
