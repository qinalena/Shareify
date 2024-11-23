package view;

import java.awt.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import data_access.DBNoteDataAccessObject;
import entity.User;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.add_friend.AddFriendState;
import interface_adapter.add_friend.AddFriendViewModel;
import interface_adapter.friends_list.FriendsListController;

/**
 * The view for adding a friend to the user's friend list.
 * This class extends JPanel and implements PropertyChangeListener to handle updates from the AddFriendViewModel.
 */
public class AddFriendView extends JPanel implements PropertyChangeListener {
    private final DefaultListModel<String> friendsListModel;
    private final AddFriendViewModel addFriendViewModel;
    private AddFriendController addFriendController;
    private final JTextField friendNameField;
    private JButton saveButton;
    private FriendsListController friendsListcontroller;
    private DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject();
    private String username;
    private String password;

    /**
     * Constructs an AddFriendView with the given friends list model, add friend view model, and friends list controller.
     *
     * @param friendsListModel The model for the friends list.
     * @param addFriendViewModel The view model for adding a friend.
     * @param friendsListcontroller The controller for the friends list view.
     */
    public AddFriendView(DefaultListModel<String> friendsListModel, AddFriendViewModel addFriendViewModel, FriendsListController friendsListcontroller) {
        this.friendsListModel = friendsListModel;
        this.addFriendViewModel = addFriendViewModel;
        this.friendsListcontroller = friendsListcontroller;
        this.addFriendViewModel.addPropertyChangeListener(this);

        setSize(300, 150);
        setLayout(new FlowLayout());

        friendNameField = new JTextField(20);
        saveButton = new JButton("Save");

        add(new JLabel("Friend's Username:"));
        add(friendNameField);
        add(saveButton);

        // Action listener to add friend to the list if they exist
        saveButton.addActionListener(e -> {
            addFriend();
            friendNameField.setText("");
        });
    }

    /**
     * Adds a friend to the friends list if the friend exists in the database.
     */
    private void addFriend() {
        String friendName = friendNameField.getText();
        if (!friendName.isEmpty()) {
            try {
                // Check if the user exists in the database
                String foundUsername = dbNoteDataAccessObject.getUserByUsername(friendName);

                if (foundUsername != null) {
                    // Add friend to the list
                    DefaultListModel<String> listModel = friendsListModel;
                    listModel.addElement(friendName);
                    addFriendViewModel.setNewFriend(friendName);
                    friendsListcontroller.addFriend(friendName);
                    dbNoteDataAccessObject.addFriendinDB(new User(username, password), foundUsername);
                    // Update FriendsListView with this friend that was added follow AddPlaylistView
                    addFriendController.switchToFriendsListView();
                } else {
                    JOptionPane.showMessageDialog(this, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles property change events from the AddFriendViewModel.
     * Updates the username and password based on the new state.
     *
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddFriendState state = (AddFriendState) evt.getNewValue();
        this.username = state.getUsername();
        this.password = state.getPassword();
    }

    /**
     * Sets the AddFriendController for this view.
     *
     * @param controller The AddFriendController to set.
     */
    public void setAddFriendController(AddFriendController controller) {
        this.addFriendController = controller;
    }
}
