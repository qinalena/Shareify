package view.playlist_collection_user_story;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import java.util.List;

import data_access.DBPlaylistDataAccessObject;
import entity.User;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import use_case.playlist_collection_user_story.add_playlist.AddPlaylistOutputBoundary;
import use_case.user_profile_user_story.note.DataAccessException;

/**
 * The View for when the user is viewing the playlist collection page in the program.
 */

public class PlaylistCollectionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "playlist collection";

    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private PlaylistCollectionController playlistCollectionController;

    private AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private DBPlaylistDataAccessObject dbPlaylistDataAccessObject;
    private String username;
    private String password;

    // Global listModel Variable
    private DefaultListModel<String> listModel;

    private final JLabel playlistCollectionName = new JLabel("Shareify - Playlist Collection");
    // JList to show the names of the playlists
    private final JList<String> playlistCollectionList;
    // Initialize components
    private final JButton backButton = new JButton("Back");
    private final JButton createPlaylistButton = new JButton("Create Playlist");
    private final JButton deletePlaylistButton = new JButton("Delete Playlist");
    private final JButton openPlaylistButton = new JButton("Open Playlist");



    public PlaylistCollectionView(PlaylistCollectionViewModel playlistCollectionViewModel,
                                  DBPlaylistDataAccessObject dbPlaylistDataAccessObject,
                                  AddPlaylistOutputBoundary addPlaylistOutputBoundary) {

        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.dbPlaylistDataAccessObject = dbPlaylistDataAccessObject;
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
        this.playlistCollectionViewModel.addPropertyChangeListener(this);
        System.out.println("Property Change listener registered!");

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing DefaultListModel
        listModel = new DefaultListModel<>();
        playlistCollectionList = new JList<>(listModel);

        // Initialize AddPlaylistInputBoundary after dependencies are set
        playlistCollectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistCollectionList.setLayoutOrientation(JList.VERTICAL);

        // Set up scroll pane
        final JScrollPane scrollPane = new JScrollPane(playlistCollectionList);

        // Add buttons to frame
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(backButton);
        buttons.add(createPlaylistButton);
        buttons.add(deletePlaylistButton);
        buttons.add(openPlaylistButton);

        createPlaylistButton.addActionListener(evt -> {
            if (evt.getSource().equals(createPlaylistButton)) {
                playlistCollectionController.switchToAddPlaylistView();
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
                playlistCollectionController.switchToUserProfileView();
            }
        }
        );

        openPlaylistButton.addActionListener(evt -> {
            if (evt.getSource().equals(openPlaylistButton)) {
                playlistCollectionController.switchToPlaylistView(playlistCollectionList.getSelectedValue());
            }
        }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding components to the frame
        this.add(playlistCollectionName);
        // Add buttons to frame
        this.add(buttons);
        // Add scroll panel to frame for list of playlists created
        this.add(scrollPane);
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
            final User realUser = new User(username, password);
            final List<String> playlists = dbPlaylistDataAccessObject.getPlaylists(realUser.getName());

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
        final int[] selectedIndices = playlistCollectionList.getSelectedIndices();
        if (selectedIndices.length > 0) {
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                listModel.remove(selectedIndices[i]);
                try {
                    final User user = new User(username, password);
                    dbPlaylistDataAccessObject.removePlaylistinDB(user, selectedIndices[i]);
                }
                catch (DataAccessException error) {
                    JOptionPane.showMessageDialog(this,
                            "Error in removing playlist from database: " + error.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(PlaylistCollectionView.this,
                    "Please select a playlist to delete.", "Playlist Error",
                    JOptionPane.ERROR_MESSAGE);
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

        System.out.println("Property change trigger: " + evt.getNewValue());

        updatePlaylistCollection(playlistCollectionState);

        if (!playlistCollectionState.getUsername().equals(this.username)) {
            this.username = playlistCollectionState.getUsername();
            this.password = playlistCollectionState.getPassword();
            populatePlaylistListFromDB();
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
        // add newly added list to listModel
        listModel.addElement(playlistCollectionState.getMostRecentPlaylist());
    }

    public void setPlaylistCollectionController(PlaylistCollectionController playlistCollectionController) {
        this.playlistCollectionController = playlistCollectionController;
    }

    public String getViewName() {
        return viewName;
    }
}
