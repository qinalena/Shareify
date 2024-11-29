package view.user_profile_user_story;

import data_access.DBNoteDataAccessObject;
import data_access.DBUserDataAccessObject;
import interface_adapter.user_profile_user_story.logout.LogoutController;
import interface_adapter.user_profile_user_story.user_profile.UserProfileController;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import use_case.DataAccessException;

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
    private DBUserDataAccessObject dbUserDataAccessObject;
    private DBNoteDataAccessObject dbNoteDataAccessObject;

    private JLabel username = new JLabel();
    private final JLabel note = new JLabel();

    private final JButton editBio = new JButton("Edit Bio");
    private final JButton playlistsButton = new JButton("Playlists");
    private final JButton friendsButton = new JButton("Friends");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton changePasswordButton = new JButton("Change Password");

    private UserProfileController userProfileController;
    private LogoutController logoutController;

    private String stateUserName;
    private String statePassword;

    public UserProfileView(UserProfileViewModel userProfileViewModel, DBUserDataAccessObject userDataAccessObject, DBNoteDataAccessObject dbNoteDataAccessObject) {

        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);
        this.dbUserDataAccessObject = userDataAccessObject;
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;

        username.setAlignmentX(Component.CENTER_ALIGNMENT);


        final JPanel buttons = new JPanel();
        buttons.add(editBio);
        buttons.add(playlistsButton);
        buttons.add(friendsButton);
        buttons.add(logoutButton);
        buttons.add(changePasswordButton);

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
            try {
                setFields(state);
            } catch (DataAccessException e) {
                throw new RuntimeException(e);
            }
        }
//        if (state.getError() != null) {
//            JOptionPane.showMessageDialog(this, state.getError(),
//                    "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    private void setFields(UserProfileState state) throws DataAccessException {
        username.setText("Shareify - " + state.getCurrentUsername());
        this.statePassword = state.getPassword();
        this.stateUserName = state.getCurrentUsername();
        username.setText("Shareify - " + state.getCurrentUsername());
        try{
            note.setText("Bio: " + dbNoteDataAccessObject.loadNote(dbUserDataAccessObject.getUser(state.getCurrentUsername())));
        } catch (RuntimeException e) {
            note.setText("Bio: " + "Hi! I'm new to Shareify! :)");
        }
//        note.setText("Bio: " + dbUserDataAccessObject.get(state.getFriendUsername()).getNote());
    }

    public void setUserProfileController(UserProfileController controller) {
        this.userProfileController = controller;
    }

    public void setLogoutController(LogoutController logoutController) {
        // TODO: save the logout controller in the instance variable.
        this.logoutController = logoutController;
    }

    public String getViewName() {
        return viewName;
    }

}

