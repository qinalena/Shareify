package view.friends_list_user_story;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import interface_adapter.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListController;
import interface_adapter.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListState;
import interface_adapter.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListViewModel;

/**
 * The View for when a friend's list is opened for the logged-in user, displaying only the list of friends.
 */
public class FriendProfileFriendsListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendProfileFriendsList";
    private final FriendProfileFriendsListViewModel viewModel;
    private FriendProfileFriendsListController friendsListController;
    private String username;
    private String password;

    // Global listModel variable
    private DefaultListModel<String> listModel;

    // UI components
    private final JLabel friendsListName = new JLabel();
    private final JList<String> friendsList;
    private final JButton backButton = new JButton("Back");

    public FriendProfileFriendsListView(FriendProfileFriendsListViewModel viewModel) {
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

        // Add components to panel
        add(scrollPane);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(backButton);
        add(buttonPanel);
        backButton.addActionListener(this);
    }

    private void populateFriendsList(List<String> friends) {
        listModel.clear();
        for (String friend : friends) {
            listModel.addElement(friend);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // React to changes in the view model (e.g., when the friends list or errors change)
        final FriendProfileFriendsListState state = (FriendProfileFriendsListState) evt.getNewValue();
        setFields(state);
        System.out.println("Creds received in FriendProfileFriendsListView: " + state.getUsername());
        if (state.getUsername() != this.username) {
            this.username = state.getUsername();
            this.password = state.getPassword();
            friendsListController.executeGetFriends(username);
            populateFriendsList(state.getFriends());
        }

        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(FriendProfileFriendsListState state) {
        friendsListName.setText("Shareify - " + state.getUsername() + "'s Friends List");
    }

    public String getViewName() {
        return viewName;
    }

    public void setFriendsListController(FriendProfileFriendsListController friendProfileFriendsListController) {
        this.friendsListController = friendProfileFriendsListController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            friendsListController.switchToFriendProfileView(username, password);
        }
    }
}
