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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data_access.DataAccessException;
import interface_adapter.ViewManagerModel;
import interface_adapter.login_user_story.signup.SignupController;
import interface_adapter.login_user_story.signup.SignupState;
import interface_adapter.login_user_story.signup.SignupViewModel;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private SignupController signupController;
    private final ViewManagerModel viewManagerModel;

    private final JButton signUp;
    private final JButton cancel;
    private JButton toLogin;

    private final String fontName = "Arial";
    private final int borderMargin = 5;
    private final int buttonWidth = 100;
    private final int buttonHeight = 30;
    private final int passwordSize = 12;
    private final int fontSize = 18;
    private final int titleMargin = 20;
    private final int titleFont = 28;
    private final int buttonSize = 50;

    public SignupView(SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        signupViewModel.addPropertyChangeListener(this);

        // Title
        final JLabel title = getTitle();

        // Username field
        final LabelTextPanel usernameInfo = getUsernameInputField();

        // Password field
        final LabelTextPanel passwordInfo = getPasswordInputField();

        // Repeated Password field
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        getRepeatedPwdField();

        // Buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, buttonSize, 0));
        toLogin = getToLogin();
        buttons.add(toLogin);

        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        getSignUp();
        buttons.add(signUp);

        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        getCancel();
        buttons.add(cancel);

        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            final SignupState currentState = signupViewModel.getState();

                            try {
                                signupController.execute(
                                        currentState.getUsername(),
                                        currentState.getPassword(),
                                        currentState.getRepeatPassword()
                                );
                            }
                            catch (DataAccessException exception) {
                                throw new RuntimeException(exception);
                            }
                        }
                    }
                }
        );

        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        signupController.switchToLoginView();
                    }
                }
        );

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    viewManagerModel.setState("Welcome");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    private void getRepeatedPwdField() {
        repeatPasswordInputField.setFont(new Font(fontName, Font.PLAIN, passwordSize));
        repeatPasswordInputField.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));
        repeatPasswordInputField.setToolTipText("Enter your password again");
    }

    private void getCancel() {
        cancel.setFont(new Font(fontName, Font.BOLD, fontSize));
        cancel.setBackground(Color.WHITE);
        cancel.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    }

    private void getSignUp() {
        signUp.setFont(new Font(fontName, Font.BOLD, fontSize));
        signUp.setBackground(Color.WHITE);
        signUp.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    }

    private JButton getToLogin() {
        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        toLogin.setFont(new Font(fontName, Font.BOLD, fontSize));
        toLogin.setBackground(Color.WHITE);
        toLogin.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        return toLogin;
    }

    private LabelTextPanel getPasswordInputField() {
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        passwordInputField.setFont(new Font(fontName, Font.PLAIN, passwordSize));
        passwordInputField.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));
        passwordInputField.setToolTipText("Enter your password");
        return passwordInfo;
    }

    private LabelTextPanel getUsernameInputField() {
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        usernameInputField.setFont(new Font(fontName, Font.PLAIN, passwordSize));
        usernameInputField.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));
        usernameInputField.setToolTipText("Enter your username");
        return usernameInfo;
    }

    private JLabel getTitle() {
        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setFont(new Font(fontName, Font.BOLD, titleFont));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(titleMargin, 0, titleMargin, 0));
        return title;
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
