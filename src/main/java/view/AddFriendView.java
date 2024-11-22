package view;

import entity.User;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.add_friend.AddFriendViewModel;
import interface_adapter.add_friend.AddFriendState;
import data_access.DBNoteDataAccessObject;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class AddFriendView extends JPanel implements PropertyChangeListener {
    private DefaultListModel<String> friendsListModel;
    private AddFriendViewModel addFriendViewModel;
    private AddFriendController addFriendController;
    private JTextField friendNameField;
    private JButton saveButton;
    private FriendsListController friendsListcontroller;
    private DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject();

    public AddFriendView(DefaultListModel<String> friendsListModel, AddFriendViewModel addFriendViewModel, FriendsListController friendsListcontroller) {
        this.friendsListModel = friendsListModel;
        this.addFriendViewModel = addFriendViewModel;
        this.friendsListcontroller = friendsListcontroller;

        // Set up UI components
//        setTitle("Add Friend");
        setSize(300, 150);
        setLayout(new FlowLayout());

        friendNameField = new JTextField(20);
        saveButton = new JButton("Save");

        add(new JLabel("Friend's Name:"));
        add(friendNameField);
        add(saveButton);

        // Action listener to add friend to the list if they exist
        saveButton.addActionListener(e -> addFriend());

        addFriendViewModel.addPropertyChangeListener(this);

//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

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
                    dbNoteDataAccessObject.addFriendinDB(new User("newUserName7", "password123"), foundUsername);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddFriendState state = (AddFriendState) evt.getNewValue();
        System.out.println("This is your username in AddFriendView: " + state.getUsername());
        // Update the list of friends when the state changes
        friendsListModel.clear();
        for (String friend : state.getFriendsList()) {
            friendsListModel.addElement(friend);
        }
        // Optionally, close the dialog after successful addition
//        dispose();
    }

    public void setAddFriendController(AddFriendController controller) {
        this.addFriendController = controller;
    }
}