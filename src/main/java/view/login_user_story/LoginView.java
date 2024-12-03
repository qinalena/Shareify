package view.login_user_story;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_user_story.login.LoginController;
import interface_adapter.login_user_story.login.LoginState;
import interface_adapter.login_user_story.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;
    private LoginController loginController;
    private final ViewManagerModel viewManagerModel;

    private final String fontName = "Arial";
    private final int borderMargin = 5;
    private final int buttonWidth = 100;
    private final int buttonHeight = 30;
    private final int passwordSize = 16;
    private final int fontSize = 20;
    private final int titleMargin = 20;
    private final int titleFont = 28;
    private final int buttonSize = 50;

    public LoginView(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {

        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        final JLabel title = getTitle();

        // Username field
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        formatUsernameInputField();

        // Password field
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);
        formatPasswordInputField();

        // Buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, buttonSize, 0));
        logIn = new JButton("log in");
        logIn.setFont(new Font(fontName, Font.BOLD, fontSize));
        logIn.setBackground(Color.WHITE);
        logIn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttons.add(logIn);

        cancel = new JButton("cancel");
        cancel.setFont(new Font(fontName, Font.BOLD, fontSize));
        cancel.setBackground(Color.WHITE);
        cancel.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttons.add(cancel);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            final LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            viewManagerModel.setState("Welcome");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        getAddDocumentListener();

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(buttons);
    }

    private void getAddDocumentListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void formatPasswordInputField() {
        passwordInputField.setFont(new Font(fontName, Font.PLAIN, passwordSize));
        passwordInputField.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));
        passwordInputField.setToolTipText("Enter your password");
    }

    private void formatUsernameInputField() {
        usernameInputField.setFont(new Font(fontName, Font.PLAIN, passwordSize));
        usernameInputField.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));
        usernameInputField.setToolTipText("Enter your username");
    }

    private JLabel getTitle() {
        final JLabel title = new JLabel("Log in");
        title.setFont(new Font(fontName, Font.BOLD, titleFont));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(titleMargin, 0, titleMargin, 0));
        return title;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
