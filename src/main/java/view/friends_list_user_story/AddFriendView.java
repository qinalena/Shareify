package view.friends_list_user_story;

import interface_adapter.friends_list_user_story.add_friend.AddFriendController;
import interface_adapter.friends_list_user_story.add_friend.AddFriendViewModel;
import interface_adapter.friends_list_user_story.add_friend.AddFriendState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * The view for adding a friend to the user's friend list.
 * This class extends JPanel and implements PropertyChangeListener to handle updates from the AddFriendViewModel.
 */
public class AddFriendView extends JPanel implements ActionListener, PropertyChangeListener {
    private final DefaultListModel<String> friendsListModel = new DefaultListModel<>();
    private final AddFriendViewModel addFriendViewModel;
    private AddFriendController addFriendController;
    private final JTextField friendNameField;
    private JButton saveButton;
    private JButton back;
    private String username;
    private String password;
    private String foundUsername;

    /**
     * Constructs an AddFriendView with the given friends list model, add friend view model, and friends list controller.
     *
     * @param addFriendViewModel The view model for adding a friend.
     */
    public AddFriendView(AddFriendViewModel addFriendViewModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.addFriendViewModel.addPropertyChangeListener(this);

        setSize(300, 150);
        setLayout(new FlowLayout());

        friendNameField = new JTextField(20);
        saveButton = new JButton("Save");

        add(new JLabel("Friend's Username:"));
        add(friendNameField);
        add(saveButton);
        back = new JButton("Back");
        add(back);

        // Action listener to add friend to the list if they exist
        saveButton.addActionListener(e -> {
            addFriend();
            friendNameField.setText("");
        });
        back.addActionListener(this);
    }

    /**
     * Adds a friend to the friends list if the friend exists in the database.
     */
    private void addFriend() {
        String friendName = friendNameField.getText();
        if (!friendName.isEmpty()) {
            try {
                addFriendController.executeGetUserByUsername(friendName);
                System.out.println("executeGetUserByUsername passes");

                if (foundUsername != null) {
                    // Add friend to the list
                    DefaultListModel<String> listModel = friendsListModel;
                    listModel.addElement(friendName);
                    addFriendController.addFriend(friendName);
                    addFriendController.executeAddFriendinDB(username, password, friendName);
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
        if (state.getUsername() != null) {
            this.username = state.getUsername();
            this.password = state.getPassword();
        }
        this.foundUsername = state.getDbUsername();

//        if (state.getError() != null) {
//            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
        // Already covered by the interactor
    }

    /**
     * Sets the AddFriendController for this view.
     *
     * @param controller The AddFriendController to set.
     */
    public void setAddFriendController(AddFriendController controller) {
        this.addFriendController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == back) {
            addFriendController.switchToFriendsListView();
        }
    }
}
