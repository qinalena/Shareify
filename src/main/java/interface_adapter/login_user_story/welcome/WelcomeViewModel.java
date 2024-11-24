package interface_adapter.login_user_story.welcome;

import interface_adapter.ViewModel;

public class WelcomeViewModel extends ViewModel<WelcomeState> {

    public static final String TO_LOGIN_BUTTON_LABEL = "Log in";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";

    public WelcomeViewModel() {
        super("Welcome");
        setState(new WelcomeState());
    }
}
