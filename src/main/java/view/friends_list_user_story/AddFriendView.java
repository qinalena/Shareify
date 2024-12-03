package view.friends_list_user_story;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.friends_list_user_story.add_friend.AddFriendController;
import interface_adapter.friends_list_user_story.add_friend.AddFriendState;
import interface_adapter.friends_list_user_story.add_friend.AddFriendViewModel;

/**
 * The view for adding a friend to the user's friend list.
 * This class extends JPanel and implements PropertyChangeListener to handle updates from the AddFriendViewModel.
 */
public class AddFriendView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;
    private static final int TEXT_FIELD_COLUMNS = 20;
    private static final String ERROR_MESSAGE = "Error";

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
     * Constructs an AddFriendView with the given friends list model, add friend view model, friends list controller.
     *
     * @param addFriendViewModel The view model for adding a friend.
     */
    public AddFriendView(AddFriendViewModel addFriendViewModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.addFriendViewModel.addPropertyChangeListener(this);

        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());

        friendNameField = new JTextField(TEXT_FIELD_COLUMNS);
        saveButton = new JButton("Save");

        add(new JLabel("Friend's Username:"));
        add(friendNameField);
        add(saveButton);
        back = new JButton("Back");
        add(back);

        // Action listener to add friend to the list if they exist
        saveButton.addActionListener(event -> {
            addFriend();
            friendNameField.setText("");
        });
        back.addActionListener(this);
    }

    /**
     * Adds a friend to the friends list if the friend exists in the database.
     */
    private void addFriend() {
        final String friendName = friendNameField.getText();
        if (!friendName.isEmpty()) {
            addFriendController.executeGetUserByUsername(friendName);
            System.out.println("executeGetUserByUsername passes");

            if (foundUsername != null) {
                // Add friend to the list
                final DefaultListModel<String> listModel = friendsListModel;
                listModel.addElement(friendName);
                addFriendController.addFriend(friendName);
                addFriendController.executeAddFriendinDB(username, password, friendName);
                addFriendController.switchToFriendsListView();
            }
            else {
                JOptionPane.showMessageDialog(this, "User does not exist.", ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please enter a name.", ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
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
