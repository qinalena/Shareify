package view.login_user_story;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_user_story.welcome.WelcomeViewModel;

public class WelcomeView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final Color COLOR = new Color(30, 215, 96);
    public static final int TOP = 10;
    public static final int LEFT = 100;
    public static final int BOTTOM = 50;
    public static final int RIGHT = 100;
    public static final int HEIGHT1 = 40;
    public static final int SIZE = 14;
    public static final int WIDTH1 = 120;
    public static final int SIZE1 = 20;
    public static final int HGAP = 50;
    private final String viewName = "Welcome";
    private final WelcomeViewModel welcomeViewModel;
    private JButton signUpButton;
    private JButton loginButton;
    private final ViewManagerModel viewManagerModel;
    private final String fontName = "Arial";

    public WelcomeView(WelcomeViewModel welcomeViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        welcomeViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(TOP, LEFT, BOTTOM, RIGHT));

        // Add logo
        addLogo();

        // Add sentence above the button
        final JLabel loginText = new JLabel("Have an account?                    New User?    ");
        loginText.setFont(new Font(fontName, Font.PLAIN, SIZE));
        loginText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(loginText);

        // Add buttons
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, 0));
        buttons.setBackground(Color.WHITE);

        loginButton = getLoginButton();
        buttons.add(loginButton);

        signUpButton = getSignUpButton();
        signUpButton.setForeground(COLOR);

        buttons.add(signUpButton);

        signUpButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUpButton)) {
                            viewManagerModel.setState("sign up");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(Color.WHITE);
            }
        });

        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(loginButton)) {
                            viewManagerModel.setState("log in");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.WHITE);
            }
        });

        this.add(buttons);
    }

    private JButton getSignUpButton() {
        signUpButton = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
        signUpButton.setFont(new Font(fontName, Font.BOLD, SIZE1));
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setBorder(new LineBorder(COLOR));
        signUpButton.setPreferredSize(new Dimension(WIDTH1, HEIGHT1));
        return signUpButton;
    }

    private JButton getLoginButton() {
        loginButton = new JButton(WelcomeViewModel.TO_LOGIN_BUTTON_LABEL);
        loginButton.setFont(new Font(fontName, Font.BOLD, SIZE1));
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(COLOR);
        loginButton.setBorder(new LineBorder(COLOR));
        loginButton.setPreferredSize(new Dimension(WIDTH1, HEIGHT1));
        return loginButton;
    }

    private void addLogo() {
        final ImageIcon logoIcon = new ImageIcon("src/main/java/view/login_user_story/Logo.png");
        final JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logoLabel);
        this.add(Box.createVerticalStrut(HEIGHT1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();

        if (source == loginButton) {
            navigateToLogin();
        }
        else if (source == signUpButton) {
            navigateToSignUp();
        }
    }

    private void navigateToLogin() {
        System.out.println("Navigating to Login View...");
    }

    private void navigateToSignUp() {
        System.out.println("Navigating to Sign-Up View...");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

}
