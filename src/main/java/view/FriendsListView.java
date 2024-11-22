package view;

import data_access.DBNoteDataAccessObject;
import entity.User;
import interface_adapter.add_friend.AddFriendState;
import interface_adapter.add_friend.AddFriendViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.friends_list.FriendsListState;
import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInteractor;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.note.DataAccessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class FriendsListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendsList";
    private final FriendsListViewModel viewModel;
    private FriendsListController friendsListController;
    private AddFriendOutputBoundary addFriendOutputBoundary;
    private DBNoteDataAccessObject dbNoteDataAccessObject;
    private String username;
    private String password;
    private AddFriendViewModel addFriendViewModel = new AddFriendViewModel();
    private AddFriendState addFriendState = addFriendViewModel.getState();
    private List<String> currentFriends = new ArrayList<>();

    // Global listModel variable
    private DefaultListModel<String> listModel;

    // Moved initialization to the constructor to prevent premature calls with uninitialized dependencies
    private AddFriendInputBoundary addFriendInputBoundary;

    // UI components
    private final JLabel friendsListName = new JLabel("Friends List");
    private final JList<String> friendsList;
    private final JButton addFriendButton = new JButton("Add Friend");
    private final JButton deleteFriendButton = new JButton("Delete Friend");
    private final JButton viewFriendButton = new JButton("View Friend");

    public FriendsListView(FriendsListController controller, FriendsListViewModel viewModel,
                           DBNoteDataAccessObject dbNoteDataAccessObject, AddFriendOutputBoundary addFriendOutputBoundary) {
        this.friendsListController = controller;
        this.viewModel = viewModel;
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
        this.addFriendOutputBoundary = addFriendOutputBoundary;
        this.viewModel.addPropertyChangeListener(this);

        friendsListName.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(friendsListName);

        // Initialize listModel here
        listModel = new DefaultListModel<>();
        friendsList = new JList<>(listModel);

        // Initialize AddFriendInputBoundary here after dependencies are set
        if (this.dbNoteDataAccessObject != null && this.addFriendOutputBoundary != null) {
            this.addFriendInputBoundary = new AddFriendInteractor(
                    dbNoteDataAccessObject,
                    addFriendOutputBoundary,
                    friendsListModelToList()
            );
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Layout for the Friends List view
        friendsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friendsList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scrollPane = new JScrollPane(friendsList);

        // Buttons for adding and deleting friends
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
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
    }

    private void populateFriendsList(List<String> friends) {
        listModel.clear();
        for (String friend : friends) {
            listModel.addElement(friend);
        }
    }

    private void populateFriendsListFromDatabase() {
        try {
            User realUser = new User(username, password);
            List<String> friends = dbNoteDataAccessObject.getFriends(realUser.getName());
            populateFriendsList(friends);
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Error fetching friends: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addFriendButton) {
            friendsListController.switchToAddFriendView();
        }
        else if (evt.getSource() == deleteFriendButton) {
            int[] selectedIndices = friendsList.getSelectedIndices();
            if (selectedIndices.length > 0) {
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    listModel.remove(selectedIndices[i]);

                    // Call removeFriendinDB to remove the friend from the database
                    try {
                        User user = new User(username, password);
                        dbNoteDataAccessObject.removeFriendinDB(user, selectedIndices[i]);
                    } catch (DataAccessException e) {
                        JOptionPane.showMessageDialog(this, "Error removing friend from database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a friend to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (evt.getSource() == viewFriendButton) {
            friendsListController.switchToFriendProfileView();
        }
    }

    private List<String> friendsListModelToList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listModel.size(); i++) {
            list.add(listModel.get(i));
        }
        return list;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // React to changes in the view model (e.g., when the friends list or errors change)
        final FriendsListState state = (FriendsListState) evt.getNewValue();
        updateFriendsList(state);
        currentFriends = state.getFriends();
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

    public void setDbNoteDataAccessObject(DBNoteDataAccessObject dbNoteDataAccessObject) {
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
        // Reinitialize AddFriendInputBoundary if dependencies change
        if (this.addFriendOutputBoundary != null) {
            this.addFriendInputBoundary = new AddFriendInteractor(
                    dbNoteDataAccessObject,
                    addFriendOutputBoundary,
                    friendsListModelToList()
            );
        }
    }

    public void setAddFriendOutputBoundary(AddFriendOutputBoundary addFriendOutputBoundary) {
        this.addFriendOutputBoundary = addFriendOutputBoundary;
        // Reinitialize AddFriendInputBoundary if dependencies change
        if (this.dbNoteDataAccessObject != null) {
            this.addFriendInputBoundary = new AddFriendInteractor(
                    dbNoteDataAccessObject,
                    addFriendOutputBoundary,
                    friendsListModelToList()
            );
        }
    }
}