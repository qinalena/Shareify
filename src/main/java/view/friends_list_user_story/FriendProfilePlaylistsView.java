package view.friends_list_user_story;

import data_access.DBUserDataAccessObject;

import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsController;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for displaying the playlist collection of a friend's profile.
 * This class extends JPanel and implements ActionListener and PropertyChangeListener to handle user interactions and updates from the FriendProfilePlaylistsViewModel.
 */
public class FriendProfilePlaylistsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendProfilePlaylistCollection";

    private FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private FriendProfilePlaylistsController friendProfilePlaylistsController;
    private DBUserDataAccessObject dbUserDataAccessObject;

    // Initialize components
    private final JLabel playlistCollectionName = new JLabel();

    // JList to show the names of the playlists
    private JList<String> playlistCollectionList;
    private final JButton backButton = new JButton("Back");
    private String username;
    private String password;

    /**
     * Constructs a FriendProfilePlaylistsView with the given controller, view model, and data access object.
     *
     * @param friendProfilePlaylistsController The controller for the friend's playlist collection.
     * @param friendProfilePlaylistsViewModel The view model for the friend's playlist collection.
     * @param dbUserDataAccessObject The data access object for user data.
     */
    public FriendProfilePlaylistsView(FriendProfilePlaylistsController friendProfilePlaylistsController,
                                       FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel,
                                       DBUserDataAccessObject dbUserDataAccessObject) {

        this.friendProfilePlaylistsController = friendProfilePlaylistsController;
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.dbUserDataAccessObject = dbUserDataAccessObject;
        this.friendProfilePlaylistsViewModel.addPropertyChangeListener(this);

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        playlistCollectionList = new JList<>(listModel);
        playlistCollectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistCollectionList.setLayoutOrientation(JList.VERTICAL);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set up scroll pane
        final JScrollPane scrollPane = new JScrollPane(playlistCollectionList);

        // Adding components to the frame
        this.add(playlistCollectionName);
        this.add(scrollPane);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(backButton);
        add(buttonPanel);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            friendProfilePlaylistsController.switchToFriendProfileView(username, password);
        }
    }

    /**
     * Handles property change events from the FriendProfilePlaylistsViewModel.
     * Updates the playlist collection and handles any errors.
     *
     * @param e The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        final FriendProfilePlaylistsState state = (FriendProfilePlaylistsState) e.getNewValue();
        updatePlaylistCollection(state);
        updateState(state);
        this.username = state.getUsername();
        this.password = state.getPassword();
        System.out.println("The username was recieved in friends playlist view " + state.getUsername());

        if (state.getPlaylistError() != null) {
            JOptionPane.showMessageDialog(this, state.getPlaylistError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateState(FriendProfilePlaylistsState state) {
        playlistCollectionName.setText("Shareify - " + state.getUsername() + "'s Playlist Collection");
    }

    /**
     * Updates JList playlist collection with the latest playlist data.
     * @param playlistCollectionState is the state of the playlist collection
     */
    private void updatePlaylistCollection(FriendProfilePlaylistsState playlistCollectionState) {
        final DefaultListModel<String> listModel = (DefaultListModel<String>) playlistCollectionList.getModel();
        listModel.clear();

        for (String playlist : playlistCollectionState.getPlaylist()) {
            listModel.addElement(playlist);
        }
    }

    public void setPlaylistCollectionController(FriendProfilePlaylistsController playlistCollectionController) {
        this.friendProfilePlaylistsController = playlistCollectionController;
    }

    public void setDbUserDataAccessObject(DBUserDataAccessObject dbUserDataAccessObject) {
        this.dbUserDataAccessObject = dbUserDataAccessObject;
    }

    public String getViewName() {
        return viewName;
    }
}