package view.user_profile_user_story;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.user_profile_user_story.change_password.ChangePasswordController;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;

public class ChangePasswordView extends JPanel implements PropertyChangeListener {
    public static final int MARGIN = 20;
    public static final int MARGIN10 = 10;
    public static final int MARGIN5 = 5;
    public static final int SIZE = 16;
    public static final int SIZE1 = 12;
    private final String viewName = "Change Password";
    private final String fontName = "Arial";

    private final UserProfileViewModel userProfileViewModel;
    private ChangePasswordController changePasswordController;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;
    private final JLabel username;
    private final JButton back;

    public ChangePasswordView(UserProfileViewModel userProfileViewModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);

        // Main layout
        this.setLayout(new BorderLayout(MARGIN10, MARGIN10));
        this.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));

        getTitlePanel();

        // Form panel
        final JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(MARGIN10, 0, MARGIN10, 0));
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(MARGIN10, MARGIN10, MARGIN10, MARGIN10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label
        username = new JLabel();
        getUsernameLabel(gbc, formPanel);

        // Password input
        final JLabel passwordLabel = new JLabel("Password:");
        getPasswordLabel(passwordLabel, gbc, formPanel);

        passwordInputField.setMargin(new Insets(MARGIN5, MARGIN5, MARGIN5, MARGIN5));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordInputField, gbc);

        this.add(formPanel, BorderLayout.CENTER);

        // Button panel
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, MARGIN, MARGIN10));

        changePassword = new JButton(viewName);
        back = new JButton("Back");

        buttonPanel.add(changePassword);
        buttonPanel.add(back);

        this.add(buttonPanel, BorderLayout.SOUTH);

        pwdInputFieldGetDocument();

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final UserProfileState currentState = userProfileViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getCurrentUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        back.addActionListener(
                evt -> {
                    if (evt.getSource().equals(back)) {
                        changePasswordController.switchToUserProfileView();
                    }
                }
        );
    }

    private void getTitlePanel() {
        // Title panel
        final JLabel titleLabel = new JLabel(viewName);
        titleLabel.setFont(new Font(fontName, Font.BOLD, SIZE));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.NORTH);
    }

    private void pwdInputFieldGetDocument() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final UserProfileState currentState = userProfileViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                userProfileViewModel.setState(currentState);
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

    private void getPasswordLabel(JLabel passwordLabel, GridBagConstraints gbc, JPanel formPanel) {
        passwordLabel.setFont(new Font(fontName, Font.PLAIN, SIZE1));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(passwordLabel, gbc);
    }

    private void getUsernameLabel(GridBagConstraints gbc, JPanel formPanel) {
        username.setFont(new Font(fontName, Font.PLAIN, SIZE1));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(username, gbc);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final UserProfileState state = (UserProfileState) evt.getNewValue();
            username.setText(state.getCurrentUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final UserProfileState state = (UserProfileState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getCurrentUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
