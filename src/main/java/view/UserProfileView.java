package view;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;

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

    private final UserProfileViewModel userViewModel;

    // Hardcoded example user
    private final User user = new User("newUserName3", "password123");

    private final JLabel username = new JLabel("Shareify - " + user.getName());
    private final JLabel note = new JLabel();
    private final JLabel playlist = new JLabel();

    private final JButton editProfile = new JButton("Edit Profile");
    private final JButton playlists = new JButton("Playlists");
    private final JButton friends = new JButton("Friends");

    private UserProfileController userProfileController;
    private ViewManagerModel viewManagerModel;

    public UserProfileView(UserProfileViewModel userViewModel) {
        this.viewManagerModel = viewManagerModel;
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.userViewModel = userViewModel;
        this.userViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();
        buttons.add(editProfile);
        buttons.add(playlists);
        buttons.add(friends);

        editProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                userProfileController.switchToNoteView();

            }
        }
        );

        playlists.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                userProfileController.switchToPlaylistView();
            }
        }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(username);
        this.add(note);
        this.add(playlist);
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
        final UserProfileState state = (UserProfileState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(UserProfileState state) {
    }

    public void setUserProfileController(UserProfileController controller) {
        this.userProfileController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}

