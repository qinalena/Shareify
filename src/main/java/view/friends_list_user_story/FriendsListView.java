package view.friends_list_user_story;

import interface_adapter.friends_list_user_story.friends_list.FriendsListController;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View for when a friend's list is opened for the logged-in user.
 */
public class FriendsListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendsList";
    private final FriendsListViewModel viewModel;
    private FriendsListController friendsListController;
    private String username;
    private String password;
    private String selectedFriend;
    private String selectedFriendPw;
    private String selectedFriendUsername;

    // Global listModel variable
    private DefaultListModel<String> listModel;

    // UI components
    private final JLabel friendsListName = new JLabel("Shareify - Friends List");
    private final JList<String> friendsList;
    private final JButton addFriendButton = new JButton("Add Friend");
    private final JButton deleteFriendButton = new JButton("Delete Friend");
    private final JButton viewFriendButton = new JButton("View Friend");
    private final JButton backButton = new JButton("Back");

    /**
     * Constructs a FriendsListView with the given controller, view model, data access object, and output boundary.
     *
     * @param viewModel The view model for the friends list.
     */
    public FriendsListView(FriendsListViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        friendsListName.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(friendsListName);

        // Initialize listModel here
        listModel = new DefaultListModel<>();
        friendsList = new JList<>(listModel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Layout for the Friends List view
        friendsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friendsList.setLayoutOrientation(JList.VERTICAL);
        final JScrollPane scrollPane = new JScrollPane(friendsList);

        // Buttons for adding and deleting friends
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(addFriendButton);
        buttonPanel.add(deleteFriendButton);
        buttonPanel.add(viewFriendButton);

        // Add components to panel
        add(scrollPane);
        add(buttonPanel);

        // Set up button actions
        addFriendButton.addActionListener(this);
        deleteFriendButton.addActionListener(this);
        viewFriendButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * Populates the friends list with the given list of friends.
     *
     * @param friends The list of friends to populate the JList with.
     */
    private void populateFriendsList(List<String> friends) {
        listModel.clear();
        for (String friend : friends) {
            listModel.addElement(friend);
        }
    }

    /**
     * Handles action events from the add, remove and view friend buttons.
     *
     * @param evt The action event.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addFriendButton) {
            friendsListController.switchToAddFriendView();
        }
        else if (evt.getSource() == deleteFriendButton) {
            final int[] selectedIndices = friendsList.getSelectedIndices();
            if (selectedIndices.length > 0) {
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    listModel.remove(selectedIndices[i]);
                    friendsListController.executeRemoveFriendInDB(username, password, selectedIndices[i]);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a friend to delete.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (evt.getSource() == viewFriendButton) {
            final int selectedIndex = friendsList.getSelectedIndex();
            if (selectedIndex != -1) {
                this.selectedFriend = friendsList.getSelectedValue();
                System.out.println("The friend you selected to view: " + selectedFriend);

                // GET THE SELECTED USER'S PASSWORD
                friendsListController.executeGetPasswordByUserName(selectedFriend);
                System.out.println("This is the friend's password: " + selectedFriendPw);
                friendsListController.switchToFriendProfileView(selectedFriend, selectedFriendPw);
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a friend to view.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (evt.getSource() == backButton) {
            friendsListController.switchToUserProfileView();
        }
    }

    /**
     * Handles property change events from the FriendsListViewModel.
     * Updates the friends list and handles any errors.
     *
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // React to changes in the view model
        final FriendsListState state = (FriendsListState) evt.getNewValue();

        if (state.getUsername() != this.username) {
            // Handle view switch or initial population
            this.username = state.getUsername();
            this.password = state.getPassword();
            friendsListController.executeGetFriends(username);
            populateFriendsList(state.getFriends());
        }

        if (state.getFriendPassword() != this.selectedFriendPw) {
            this.selectedFriendPw = state.getFriendPassword();
        }

        if (state.getFriendUsername() != this.selectedFriendUsername) {
            updateFriendsList(state);
            this.selectedFriendUsername = state.getFriendUsername();
        }

        // Handle errors
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFriendsList(FriendsListState state) {
        if (state.getFriends().isEmpty() || state.getMostRecentFriend() != null) {
            listModel.addElement(state.getFriendUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setFriendsListController(FriendsListController controller) {
        this.friendsListController = controller;
    }
}
