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
import use_case.playlist_collection_user_story.add_playlist.AddPlaylistInputBoundary;
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
    private AddPlaylistInputBoundary addPlaylistInputBoundary;
    private DBPlaylistDataAccessObject dbPlaylistDataAccessObject;
    private String username;
    private String password;

    private DefaultListModel<String> listModel;

    private final JLabel playlistCollectionName = new JLabel("Shareify - Playlist Collection");

    // Initialize components
    private JButton backButton = new JButton("Back");
    private JButton createPlaylistButton = new JButton("Create Playlist");
    private JButton deletePlaylistButton = new JButton("Delete Playlist");
    private JButton openPlaylistButton = new JButton("Open Playlist");

    // JList to show the names of the playlists
    private JList<String> playlistCollectionList = new JList<>(new DefaultListModel<>());

    public PlaylistCollectionView(PlaylistCollectionController playlistCollectionController,
                                  PlaylistCollectionViewModel playlistCollectionViewModel,
                                  DBPlaylistDataAccessObject dbPlaylistDataAccessObject,
                                  AddPlaylistOutputBoundary addPlaylistOutputBoundary) {

        this.playlistCollectionController = playlistCollectionController;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.dbPlaylistDataAccessObject = dbPlaylistDataAccessObject;
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
        this.playlistCollectionViewModel.addPropertyChangeListener(this);

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing DefaultListModel
        listModel = new DefaultListModel<>();
        playlistCollectionList.setModel(listModel);

        // Initialize AddPlaylistInputBoundary after dependencies are set
        playlistCollectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistCollectionList.setLayoutOrientation(JList.VERTICAL);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding components to the frame
        this.add(playlistCollectionName);
        // Add buttons to frame
        this.add(buttons);
        // Add scroll panel to frame for list of playlists created
        this.add(scrollPane);
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
     * Populates playlist collection with given list of playlist.
     * @param playlists list of playlists to populate
     */
    private void populatePlaylistList(List<String> playlists) {
        // clear current list
        listModel.clear();
        for (String playlist : playlists) {
            listModel.addElement(playlist);
        }
        // refresh UI
        playlistCollectionList.revalidate();
        playlistCollectionList.repaint();
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
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getNewValue() instanceof PlaylistCollectionState) {
            final PlaylistCollectionState playlistCollectionState = (PlaylistCollectionState) e.getNewValue();

            System.out.println("Propety change trigger: " + e.getNewValue());

            // updatePlaylistCollection(playlistCollectionState);

            // Check for changes in username
            if (!playlistCollectionState.getUsername().equals(this.username)) {
                this.username = playlistCollectionState.getUsername();
                this.password = playlistCollectionState.getPassword();

                // Fetch playlist from DB for updated username
                System.out.println("Fetching playlist for: " + this.username);
                populatePlaylistListFromDB();
            }
            else if (playlistCollectionState.getMostRecentPlaylist() != null) {
                // Add most recent playlist to the view
                System.out.println("Adding recent playlists: " + playlistCollectionState.getMostRecentPlaylist());
                updatePlaylistCollection(playlistCollectionState);
            }
            else if (playlistCollectionState.getPlaylistError() != null) {
                JOptionPane.showMessageDialog(this, playlistCollectionState.getPlaylistError(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Updates JList playlist collection with the latest playlist data.
     * @param playlistCollectionState playlist collection state
     */
    private void updatePlaylistCollection(PlaylistCollectionState playlistCollectionState) {
        // Add only most recent playlist to avoid redundant population
        if (playlistCollectionState.getMostRecentPlaylist() != null) {
            listModel.addElement(playlistCollectionState.getMostRecentPlaylist());
        }
    }

    public void setPlaylistCollectionController(PlaylistCollectionController playlistCollectionController) {
        this.playlistCollectionController = playlistCollectionController;
    }

    public String getViewName() {
        return viewName;
    }
}
