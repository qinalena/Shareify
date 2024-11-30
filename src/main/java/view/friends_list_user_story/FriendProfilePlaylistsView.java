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
    private final JButton openPlaylist = new JButton("Open Playlist");
    private String username;
    private String password;

    public FriendProfilePlaylistsView(FriendProfilePlaylistsController friendProfilePlaylistsController,
                                      FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel,
                                      DBUserDataAccessObject dbUserDataAccessObject) {

        this.friendProfilePlaylistsController = friendProfilePlaylistsController;
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.dbUserDataAccessObject = dbUserDataAccessObject;
        this.friendProfilePlaylistsViewModel.addPropertyChangeListener(this);

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistCollectionName.setText("Shareify - Friend's Playlist Collection"); // Default text

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
        buttonPanel.add(openPlaylist);
        add(buttonPanel);

        // Set up button actions
        openPlaylist.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            friendProfilePlaylistsController.switchToFriendProfileView(username, password);
        }
        else if (evt.getSource() == openPlaylist) {
            friendProfilePlaylistsController.switchToPlaylistView(playlistCollectionList.getSelectedValue(),
                    username, password);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        final FriendProfilePlaylistsState state = (FriendProfilePlaylistsState) e.getNewValue();
        updatePlaylistCollection(state);
        updateState(state);
        this.username = state.getUsername();
        this.password = state.getPassword();

        if (state.getPlaylistError() != null) {
            JOptionPane.showMessageDialog(this, state.getPlaylistError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateState(FriendProfilePlaylistsState state) {
        playlistCollectionName.setText("Shareify - " + state.getUsername() + "'s Playlist Collection");
    }

    private void updatePlaylistCollection(FriendProfilePlaylistsState playlistCollectionState) {
        final DefaultListModel<String> listModel = (DefaultListModel<String>) playlistCollectionList.getModel();
        listModel.clear();

        for (String playlist : playlistCollectionState.getPlaylist()) {
            listModel.addElement(playlist);
        }
        listModel.addElement("test1");
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