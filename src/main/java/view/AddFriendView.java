package view;

import entity.User;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.add_friend.AddFriendViewModel;
import interface_adapter.add_friend.AddFriendState;
import data_access.DBNoteDataAccessObject;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class AddFriendView extends JFrame implements PropertyChangeListener {
    private DefaultListModel<String> friendsListModel; // This is the list we update
    private AddFriendViewModel addFriendViewModel;
    private AddFriendController addFriendController;
    private JTextField friendNameField;
    private JButton saveButton;
    private DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject();

    public AddFriendView(DefaultListModel<String> friendsListModel, AddFriendViewModel addFriendViewModel) {
        this.friendsListModel = friendsListModel;
        this.addFriendViewModel = addFriendViewModel;

        // Set up UI components
        setTitle("Add Friend");
        setSize(300, 150);
        setLayout(new FlowLayout());

        friendNameField = new JTextField(20);
        saveButton = new JButton("Save");

        add(new JLabel("Friend's Name:"));
        add(friendNameField);
        add(saveButton);

        // Action listener to add friend to the list if they exist
        saveButton.addActionListener(e -> addFriend());

        addFriendViewModel.addPropertyChangeListener(this); // Add as listener

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addFriend() {
        String friendName = friendNameField.getText();
        if (!friendName.isEmpty()) {
            try {
                // Check if the user exists in the database
                String foundUsername = dbNoteDataAccessObject.getUserByUsername(friendName);

                if (foundUsername != null) { // User exists
                    // Add friend to the list
                    DefaultListModel<String> listModel = friendsListModel;
                    listModel.addElement(friendName);
                    addFriendViewModel.setNewFriend(friendName); // Store the new friend's username
                    dbNoteDataAccessObject.addFriendinDB(new User("newUserName8", "password123"), foundUsername);

                    dispose(); // Close the dialog after successful addition
                } else { // User does not exist
                    JOptionPane.showMessageDialog(this, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) { // Handle any exceptions
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddFriendState state = (AddFriendState) evt.getNewValue();

        // Handle success or failure
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Update the list of friends when the state changes
            friendsListModel.clear();
            for (String friend : state.getFriendsList()) {
                friendsListModel.addElement(friend);
            }
            // Optionally, close the dialog after successful addition
            dispose();
        }
    }

    public void setAddFriendController(AddFriendController controller) {
        this.addFriendController = controller;
    }
}