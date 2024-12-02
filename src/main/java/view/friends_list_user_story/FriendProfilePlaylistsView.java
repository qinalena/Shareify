package view.friends_list_user_story;

import data_access.DBPlaylistDataAccessObject;

import java.util.List;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsController;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsState;
import data_access.DataAccessException;

public class FriendProfilePlaylistsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendProfilePlaylistCollection";

    private FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private FriendProfilePlaylistsController friendProfilePlaylistsController;
    private DBPlaylistDataAccessObject dbPlaylistDataAccessObject;

    // Initialize components
    private final JLabel playlistCollectionName = new JLabel();
    private DefaultListModel<String> listModel;

    // JList to show the names of the playlists
    private JList<String> playlistCollectionList;
    private final JButton backButton = new JButton("Back");
    private final JButton openPlaylist = new JButton("Open Playlist");
    private String username;
    private String password;

    public FriendProfilePlaylistsView(FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel,
                                      DBPlaylistDataAccessObject dbPlaylistDataAccessObject) {
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.dbPlaylistDataAccessObject = dbPlaylistDataAccessObject;
        this.friendProfilePlaylistsViewModel.addPropertyChangeListener(this);

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistCollectionName.setText("Shareify - Friend's Playlist Collection");

        // Initializing JList
        listModel = new DefaultListModel<>();
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
        if (state.getUsername() != this.username) {
            this.username = state.getUsername();
            this.password = state.getPassword();
            populatePlaylistListFromDB();
        }
        else {
            updatePlaylistCollection(state);
        }

        if (state.getPlaylistError() != null) {
            JOptionPane.showMessageDialog(this, state.getPlaylistError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populate playlist collection from database.
     */
    private void populatePlaylistListFromDB() {
        try {
            final List<String> playlists = dbPlaylistDataAccessObject.getPlaylists(username);

            // Debugging
            System.out.println("Fetching playlist from data base: " + playlists);

            // Populate playlist collection in view
            populatePlaylistList(playlists);
        }
        catch (DataAccessException error) {
            JOptionPane.showMessageDialog(this,
                    "Error getting playlists: " + error.getMessage(), "Error getting playlist",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populates playlist collection with given list of playlist.
     * @param playlists list of playlists to populate
     */
    private void populatePlaylistList(List<String> playlists) {
        // clear current list
        listModel.clear();
        // Add each component to the list
        for (String playlist : playlists) {
            listModel.addElement(playlist);
        }
    }

    private void updatePlaylistCollection(FriendProfilePlaylistsState playlistCollectionState) {
        // Adds all playlist that have been added to the view, including any newly created playlist
        for (String playlist : playlistCollectionState.getPlaylist()) {
            // Only add playlists that are not already in listModel
            if (!listModel.contains(playlist)) {
                listModel.addElement(playlist);
            }
        }
    }

    public void setPlaylistCollectionController(FriendProfilePlaylistsController playlistCollectionController) {
        this.friendProfilePlaylistsController = playlistCollectionController;
    }

    public String getViewName() {
        return viewName;
    }
}
