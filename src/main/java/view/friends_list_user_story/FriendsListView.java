package view.friends_list_user_story;

import data_access.DBNoteDataAccessObject;
import interface_adapter.friends_list_user_story.friends_list.FriendsListController;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListState;
import use_case.friends_list_user_story.add_friend.AddFriendOutputBoundary;
import entity.User;
import use_case.DataAccessException;

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
    private AddFriendOutputBoundary addFriendOutputBoundary;
    private DBNoteDataAccessObject dbNoteDataAccessObject;
    private String username;
    private String password;
    private String selectedFriend;

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
     * @param dbNoteDataAccessObject The data access object for notes and user data.
     * @param addFriendOutputBoundary The output boundary for adding friends.
     */
    public FriendsListView(FriendsListViewModel viewModel,
                           DBNoteDataAccessObject dbNoteDataAccessObject,
                           AddFriendOutputBoundary addFriendOutputBoundary) {
        this.viewModel = viewModel;
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
        this.addFriendOutputBoundary = addFriendOutputBoundary;
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
        buttonPanel.add(addFriendButton);
        buttonPanel.add(deleteFriendButton);
        buttonPanel.add(viewFriendButton);
        buttonPanel.add(backButton);

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
     * Populates the friends list from the database.
     */
    private void populateFriendsListFromDatabase() {
        try {
            final User realUser = new User(username, password);
            final List<String> friends = dbNoteDataAccessObject.getFriends(realUser.getUsername());
            populateFriendsList(friends);
        }
        catch (DataAccessException error) {
            JOptionPane.showMessageDialog(this, "Error fetching friends: " + error.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
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
                    try {
                        final User user = new User(username, password);
                        dbNoteDataAccessObject.removeFriendinDB(user, selectedIndices[i]);
                    }
                    catch (DataAccessException error) {
                        JOptionPane.showMessageDialog(this, "Error removing friend from database:"
                                + " " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
                try {
                    final String selectedFriendPw = dbNoteDataAccessObject.getPasswordByUserName(selectedFriend);
                    System.out.println("This is the friend's password: " + selectedFriendPw);
                    friendsListController.switchToFriendProfileView(selectedFriend, selectedFriendPw);
                }
                catch (DataAccessException error) {
                    throw new RuntimeException(error);
                }
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
        // React to changes in the view model (e.g., when the friends list or errors change)
        final FriendsListState state = (FriendsListState) evt.getNewValue();
        updateFriendsList(state);
        if (state.getUsername() != this.username) {
            this.username = state.getUsername();
            this.password = state.getPassword();
            populateFriendsListFromDatabase();
        }

        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFriendsList(FriendsListState state) {
        listModel.addElement(state.getMostRecentFriend());
    }

    public String getViewName() {
        return viewName;
    }

    public void setFriendsListController(FriendsListController controller) {
        this.friendsListController = controller;
    }
}
