package view.user_profile_user_story;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data_access.DataAccessException;
import interface_adapter.user_profile_user_story.logout.LogoutController;
import interface_adapter.user_profile_user_story.user_profile.UserProfileController;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;

/**
 * The View for when the User had logged in, displaying their User Profile.
 */
public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "user profile";

    private final UserProfileViewModel userProfileViewModel;
    private JLabel username = new JLabel();
    private final JLabel note = new JLabel();

    private final JPanel buttons = new JPanel();
    private final JButton editBio = new JButton("Edit Bio");
    private final JButton playlistsButton = new JButton("Playlists");
    private final JButton friendsButton = new JButton("Friends");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton changePasswordButton = new JButton("Change Password");
    private final JButton nightModeButton = new JButton("Night Mode");
    private boolean isNightMode;

    private UserProfileController userProfileController;
    private LogoutController logoutController;

    private String stateUserName;
    private String statePassword;

    public UserProfileView(UserProfileViewModel userProfileViewModel) {

        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);
        isNightMode = false;

        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        note.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(editBio);
        buttons.add(playlistsButton);
        buttons.add(friendsButton);
        buttons.add(changePasswordButton);
        buttons.add(logoutButton);
        editBio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                userProfileController.switchToNoteView();
            }
        }
        );

        playlistsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                userProfileController.switchToPlaylistCollectionView();
            }
        }
        );

        friendsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        userProfileController.switchToFriendsListView(stateUserName, statePassword);

                    }
                }
        );

        logoutButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final UserProfileState currentState = userProfileViewModel.getState();

                        logoutController.execute(currentState.getCurrentUsername());
                    }
                }
        );

        changePasswordButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        userProfileController.switchToChangePasswordView();
                    }
                }
        );

        nightModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                toggleNightMode();
                if (isNightMode) {
                    switchNight();
                }
                else {
                    switchLight();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        nightModeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(username);
        this.add(note);
        this.add(buttons);
        this.add(nightModeButton);
    }

    private void switchLight() {
        buttons.setBackground(Color.WHITE);
        changePasswordButton.setBackground(Color.WHITE);
        changePasswordButton.setForeground(Color.BLACK);
        editBio.setBackground(Color.WHITE);
        editBio.setForeground(Color.BLACK);
        playlistsButton.setBackground(Color.WHITE);
        playlistsButton.setForeground(Color.BLACK);
        friendsButton.setBackground(Color.WHITE);
        friendsButton.setForeground(Color.BLACK);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(Color.BLACK);
        nightModeButton.setBackground(Color.WHITE);
        nightModeButton.setForeground(Color.BLACK);
    }

    private void switchNight() {
        buttons.setBackground(Color.BLACK);
        changePasswordButton.setBackground(Color.BLACK);
        changePasswordButton.setForeground(Color.WHITE);
        editBio.setBackground(Color.BLACK);
        editBio.setForeground(Color.WHITE);
        playlistsButton.setBackground(Color.BLACK);
        playlistsButton.setForeground(Color.WHITE);
        friendsButton.setBackground(Color.BLACK);
        friendsButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLACK);
        logoutButton.setForeground(Color.WHITE);
        nightModeButton.setBackground(Color.BLACK);
        nightModeButton.setForeground(Color.WHITE);
    }

    private void toggleNightMode() {
        if (isNightMode) {
            this.setBackground(Color.WHITE);
            username.setForeground(Color.BLACK);
            note.setForeground(Color.BLACK);
            nightModeButton.setText("Night Mode");
        }
        else {
            this.setBackground(Color.DARK_GRAY);
            username.setForeground(Color.WHITE);
            note.setForeground(Color.WHITE);
            nightModeButton.setText("Light Mode");
        }
        isNightMode = !isNightMode;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final UserProfileState state = (UserProfileState) evt.getNewValue();
            try {
                setFields(state);
            }
            catch (DataAccessException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    private void setFields(UserProfileState state) throws DataAccessException {
        username.setText("Shareify - " + state.getCurrentUsername());
        this.statePassword = state.getPassword();
        this.stateUserName = state.getCurrentUsername();
        username.setText("Shareify - " + state.getCurrentUsername());
        note.setText("Bio: " + state.getBio());
    }

    public void setUserProfileController(UserProfileController controller) {
        this.userProfileController = controller;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public String getViewName() {
        return viewName;
    }
}
