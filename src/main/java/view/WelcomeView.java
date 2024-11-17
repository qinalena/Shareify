package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.welcome.WelcomeViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.border.Border;

public class WelcomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Welcome";
    private final WelcomeViewModel welcomeViewModel;
    private final JButton SignUpButton;
    private final JButton LoginButton;
    private final ViewManagerModel viewManagerModel;

    public WelcomeView(WelcomeViewModel welcomeViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        welcomeViewModel.addPropertyChangeListener(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10, 100, 50, 100));

        // Add logo
        ImageIcon logoIcon = new ImageIcon("src/main/java/view/Logo.png"); // Replace with your logo path
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logoLabel);
        this.add(Box.createVerticalStrut(40));

        // Add sentence above the button
        JLabel LoginText = new JLabel("Have an account?                    New User?    ");
        LoginText.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size
        LoginText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(LoginText);


        // Add buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttons.setBackground(Color.WHITE);

        LoginButton = new JButton(WelcomeViewModel.TO_LOGIN_BUTTON_LABEL);
        LoginButton.setFont(new Font("Arial", Font.BOLD, 20));
        LoginButton.setBackground(Color.WHITE);
        LoginButton.setForeground(new Color(30,215,96));
        LoginButton.setBorder(new LineBorder(new Color(30,215,96)));
        LoginButton.setPreferredSize(new Dimension(120, 40));
        buttons.add(LoginButton);


        SignUpButton = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
        SignUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        SignUpButton.setBackground(Color.WHITE);
        SignUpButton.setBorder(new LineBorder(new Color(30,215,96)));
        SignUpButton.setPreferredSize(new Dimension(120, 40));
        SignUpButton.setForeground(new Color(30,215,96));

        buttons.add(SignUpButton);



        SignUpButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(SignUpButton)) {
                            viewManagerModel.setState("sign up");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        SignUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SignUpButton.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                SignUpButton.setBackground(Color.WHITE);
            }
        });

        LoginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(LoginButton)) {
                            viewManagerModel.setState("log in");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginButton.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginButton.setBackground(Color.WHITE);
            }
        });


        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == LoginButton) {
            // Navigate to login view
            navigateToLogin();
        } else if (source == SignUpButton) {
            // Navigate to sign-up view
            navigateToSignUp();
        }
    }

    private void navigateToLogin() {
        System.out.println("Navigating to Login View...");
    }

    private void navigateToSignUp() {
        System.out.println("Navigating to Sign-Up View...");
    }

    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

}
