package view.user_profile_user_story;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.user_profile_user_story.change_password.ChangePasswordController;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import view.login_user_story.LabelTextPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangePasswordView extends JPanel implements PropertyChangeListener {
    private final String viewName = "Change Password";

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
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title panel
        JLabel titleLabel = new JLabel("Change Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label
        username = new JLabel();
        username.setFont(new Font("Arial", Font.PLAIN, 12));
        username.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(username, gbc);

        // Password input
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(passwordLabel, gbc);

        passwordInputField.setMargin(new Insets(5, 5, 5, 5));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordInputField, gbc);

        this.add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        changePassword = new JButton("Change Password");
        back = new JButton("Back");

        buttonPanel.add(changePassword);
        buttonPanel.add(back);

        this.add(buttonPanel, BorderLayout.SOUTH);



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

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final UserProfileState currentState = userProfileViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
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

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final UserProfileState state = (UserProfileState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final UserProfileState state = (UserProfileState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
