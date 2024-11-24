package view.user_profile_user_story;

import entity.User;
import interface_adapter.user_profile_user_story.user_profile.UserProfileController;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the User had logged in, displaying their User Profile.
 */
public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "user profile";

    private final UserProfileViewModel userProfileViewModel;

    // Hardcoded example user
    private final User user = new User("newUserName3", "password123");

    private JLabel username = new JLabel();
    private final JLabel note = new JLabel(user.getNote());

    private final JButton editProfileButton = new JButton("Edit Profile");
    private final JButton playlistsButton = new JButton("Playlists");
    private final JButton friendsButton = new JButton("Friends");
    private final JButton logoutButton = new JButton("Logout");

    private UserProfileController userProfileController;

    public UserProfileView(UserProfileViewModel userProfileViewModel) {

        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);

        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(editProfileButton);
        buttons.add(playlistsButton);
        buttons.add(friendsButton);
        buttons.add(logoutButton);

        editProfileButton.addActionListener(new ActionListener() {
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
                        userProfileController.switchToFriendsListView();

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(username);
        this.add(note);
        this.add(buttons);
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
            setFields(state);
            }
//        if (state.getError() != null) {
//            JOptionPane.showMessageDialog(this, state.getError(),
//                    "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    private void setFields(UserProfileState state) {
        username.setText("Shareify - " + state.getUsername());
    }

    public void setUserProfileController(UserProfileController controller) {
        this.userProfileController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}

