package view;

import entity.User;
import interface_adapter.friend_profile.FriendProfileController;
import interface_adapter.friend_profile.FriendProfileState;
import interface_adapter.friend_profile.FriendProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when a friend's profile is opened, displaying their profile.
 */
public class FriendView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendProfile";

    private final FriendProfileViewModel friendViewModel;

    // Hardcoded example friend (replace with actual friend data)
    private final User friend = new User("testFriend", "password123");

    private final JLabel friendUsername = new JLabel("Shareify - " + friend.getName());
    private final JLabel friendNote = new JLabel();

    private final JButton viewPlaylists = new JButton("View Playlists");
    private final JButton viewFriends = new JButton("View Friends");
    private final JButton sendMessage = new JButton("Send Message");

    private FriendProfileController friendProfileController;

    public FriendView(FriendProfileViewModel friendViewModel) {
        this.friendViewModel = friendViewModel;
        this.friendViewModel.addPropertyChangeListener(this);

        friendUsername.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(viewPlaylists);
        buttons.add(viewFriends);
        buttons.add(sendMessage);

        viewPlaylists.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        friendProfileController.switchToNoteView();
                    }
                }
        );

        viewFriends.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        friendProfileController.switchToFriendsListView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(friendUsername);
        this.add(friendNote);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        final FriendProfileState state = (FriendProfileState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setFriendProfileController(FriendProfileController controller) {
        this.friendProfileController = controller;
    }

    public String getViewName() {
        return viewName;
    }

    private void setFields(FriendProfileState state) {
    }
}