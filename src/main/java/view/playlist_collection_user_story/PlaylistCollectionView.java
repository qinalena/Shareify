package view.playlist_collection_user_story;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import data_access.DBPlaylistDataAccessObject;
import data_access.DataAccessException;
import entity.User;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;

/**
 * The View for when the user is viewing the playlist collection page in the program.
 */

public class PlaylistCollectionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "playlist collection";

    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private PlaylistCollectionController playlistCollectionController;

    private DBPlaylistDataAccessObject dbPlaylistDataAccessObject;
    private String username;
    private String password;

    // Global listModel Variable
    private DefaultListModel<String> listModel;

    private final JLabel playlistCollectionName = new JLabel("Shareify - Playlist Collection");

    // Initialize components
    private final JButton backButton = new JButton("Back");
    private final JButton createPlaylistButton = new JButton("Create Playlist");
    private final JButton deletePlaylistButton = new JButton("Delete Playlist");
    private final JButton openPlaylistButton = new JButton("Open Playlist");

    // JList to show the names of the playlists
    private final JList<String> playlistCollectionList = new JList<>(new DefaultListModel<>());

    public PlaylistCollectionView(PlaylistCollectionViewModel playlistCollectionViewModel,
                                  DBPlaylistDataAccessObject dbPlaylistDataAccessObject) {

        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.dbPlaylistDataAccessObject = dbPlaylistDataAccessObject;
        this.playlistCollectionViewModel.addPropertyChangeListener(this);

        // Debugging
        // System.out.println("Property Change listener registered!");

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing DefaultListModel
        listModel = new DefaultListModel<>();
        playlistCollectionList.setModel(listModel);

        // Initialize AddPlaylistInputBoundary after dependencies are set
        playlistCollectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistCollectionList.setLayoutOrientation(JList.VERTICAL);

        // Set up scroll pane
        final JScrollPane scrollPane = new JScrollPane(playlistCollectionList);

        // Add buttons to frame
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttons.add(backButton);
        buttons.add(createPlaylistButton);
        buttons.add(deletePlaylistButton);
        buttons.add(openPlaylistButton);

        createPlaylistButton.addActionListener(evt -> {
            if (evt.getSource().equals(createPlaylistButton)) {
                this.playlistCollectionController.switchToAddPlaylistView();
            }
        }
        );

        deletePlaylistButton.addActionListener(evt -> {
            if (evt.getSource().equals(deletePlaylistButton)) {
                deletePlaylistLogic();
            }
        }
        );

        backButton.addActionListener(evt -> {
            if (evt.getSource().equals(backButton)) {
                this.playlistCollectionController.switchToUserProfileView();
            }
        }
        );

        openPlaylistButton.addActionListener(evt -> {
            if (evt.getSource().equals(openPlaylistButton)) {
                this.playlistCollectionController.switchToPlaylistView(playlistCollectionList.getSelectedValue());
            }
        }
        );

        this.setLayout(new BorderLayout());

        // Adding components to the frame
        this.add(playlistCollectionName, BorderLayout.NORTH);
        // Add buttons to frame
        this.add(buttons, BorderLayout.SOUTH);
        // Add scroll panel to frame for list of playlists created
        this.add(scrollPane, BorderLayout.CENTER);
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
     * Logic for deleting a playlist button.
     */
    private void deletePlaylistLogic() {
        final String selectedPlaylist = playlistCollectionList.getSelectedValue();
        if (selectedPlaylist != null) {
            try {
                dbPlaylistDataAccessObject.removePlaylistinDB(new User(username, password),
                        selectedPlaylist);

                // Remove playlist from the list model after successful deletion
                listModel.removeElement(selectedPlaylist);
                playlistCollectionViewModel.getState().removePlaylist(selectedPlaylist);
                playlistCollectionViewModel.firePropertyChanged();
                System.out.println("Deleted playlist: " + selectedPlaylist);
            }
            catch (DataAccessException error) {
                JOptionPane.showMessageDialog(this, "Error in removing playlist from database: " + error.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please selected a playlist to delete.",
                    "Playlist error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * React to a button click that results in evt.
     * @param e the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PlaylistCollectionState playlistCollectionState = (PlaylistCollectionState) evt.getNewValue();

        // Debugging
        // System.out.println("Property change trigger: " + evt.getNewValue());

        if (!playlistCollectionState.getUsername().equals(this.username)) {
            this.username = playlistCollectionState.getUsername();
            this.password = playlistCollectionState.getPassword();
            // clear listmodel and repopulate
            populatePlaylistListFromDB();
        }
        else {
            // Update view using state without duplication
            updatePlaylistCollection(playlistCollectionState);
        }
        if (playlistCollectionState.getPlaylistError() != null) {
            JOptionPane.showMessageDialog(this, playlistCollectionState.getPlaylistError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates JList playlist collection with the latest playlist data.
     */
    private void updatePlaylistCollection(PlaylistCollectionState playlistCollectionState) {
        // Adds all playlist that have been added to the view, including any newly created playlist
        for (String playlist : playlistCollectionState.getPlaylist()) {
            // Only add playlists that are not already in listModel
            if (!listModel.contains(playlist)) {
                listModel.addElement(playlist);
            }
        }
    }

    public void setPlaylistCollectionController(PlaylistCollectionController playlistCollectionController) {
        this.playlistCollectionController = playlistCollectionController;
    }

    public String getViewName() {
        return viewName;
    }
}
