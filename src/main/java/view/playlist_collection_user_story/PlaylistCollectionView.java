package view.playlist_collection_user_story;

import data_access.DBUserDataAccessObject;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistController;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistPresenter;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import use_case.playlist_collection_user_story.add_playlist.AddPlaylistInputBoundary;
import use_case.playlist_collection_user_story.add_playlist.AddPlaylistInteractor;
import use_case.playlist_collection_user_story.add_playlist.AddPlaylistOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The View for when the user is viewing the playlist collection page in the program.
 */

public class PlaylistCollectionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "playlist collection";

    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private PlaylistCollectionController playlistCollectionController;
    private AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private DBUserDataAccessObject dbUserDataAccessObject;

    private AddPlaylistInputBoundary addPlaylistInputBoundary;

    private final JLabel playlistCollectionName = new JLabel("Shareify - Playlist Collection");

    // Initialize components
    private JButton backButton = new JButton("Back");
    private JButton createPlaylistButton = new JButton("Create Playlist");
    private JButton deletePlaylistButton = new JButton("Delete Playlist");

    // JList to show the names of the playlists
    private JList<String> playlistCollectionList = new JList<>(new DefaultListModel<>());
    private String username;
    private String password;

    public PlaylistCollectionView(PlaylistCollectionController playlistCollectionController,
                                  PlaylistCollectionViewModel playlistCollectionViewModel,
                                  DBUserDataAccessObject dbUserDataAccessObject,
                                  AddPlaylistOutputBoundary addPlaylistOutputBoundary) {

        this.playlistCollectionController = playlistCollectionController;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.dbUserDataAccessObject = dbUserDataAccessObject;
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
        this.playlistCollectionViewModel.addPropertyChangeListener(this);

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing JList
        playlistCollectionList = new JList<>(new DefaultListModel<>());

        // Initialize AddPlaylistInputBoundary after dependencies are set
        playlistCollectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistCollectionList.setLayoutOrientation(JList.VERTICAL);

        if (this.dbUserDataAccessObject != null && this.addPlaylistOutputBoundary != null) {
            this.addPlaylistInputBoundary = new AddPlaylistInteractor(
                    dbUserDataAccessObject, addPlaylistOutputBoundary, playlistListModelToList()
            );
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set up scroll pane
        final JScrollPane scrollPane = new JScrollPane(playlistCollectionList);

        // Add buttons to frame
        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(createPlaylistButton);
        buttons.add(deletePlaylistButton);

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

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playlistCollectionController.switchToUserProfileView();
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
     * Logic for deleting a playlist button.
     */
    private void deletePlaylistLogic() {
        final int[] selectedIndices = playlistCollectionList.getSelectedIndices();
        if (selectedIndices.length > 0) {
            final DefaultListModel<String> listModel = (
                    DefaultListModel<String>) playlistCollectionList.getModel();
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                listModel.remove(selectedIndices[i]);
            }
        }
        else {
            JOptionPane.showMessageDialog(PlaylistCollectionView.this,
                    "Please select a playlist to delete.", "Playlist Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<String> playlistListModelToList() {
        final DefaultListModel<String> playlistListModel = (DefaultListModel<String>) playlistCollectionList.getModel();
        final List<String> playlistList = new ArrayList<>();
        for (int i = 0; i < playlistListModel.size(); i++) {
            playlistList.add(playlistListModel.get(i));
        }
        return playlistList;
    }

    private void populatePlaylistList(List<String> playlists) {
        playlistListModelToList().clear();
        for (String playlist : playlists) {
            playlistListModelToList().add(playlist);
        }
    }

    private void populatePlaylistListFromDB() {
        try {
            final User realUser = new User(username, password);
            final List<String> playlists = dbUserDataAccessObject.getPlaylists(realUser.getName());
            populatePlaylistList(playlists);
        }
        catch (DataAccessException error) {
            JOptionPane.showMessageDialog(this,
                    "Error getting playlists: " + error.getMessage(), "Error getting playlist",
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
        final PlaylistCollectionState playlistCollectionState = (PlaylistCollectionState) e.getNewValue();
        updatePlaylistCollection(playlistCollectionState);

        if (playlistCollectionState.getPlaylistError() != null) {
            JOptionPane.showMessageDialog(this, playlistCollectionState.getPlaylistError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates JList playlist collection with the latest playlist data.
     * @param playlistCollectionState output data
     */
    private void updatePlaylistCollection(PlaylistCollectionState playlistCollectionState) {
        final DefaultListModel<String> listModel = (DefaultListModel<String>) playlistCollectionList.getModel();
        listModel.clear();

        for (String playlist : playlistCollectionState.getPlaylist()) {
            listModel.addElement(playlist);
        }
    }

    public void setPlaylistCollectionController(PlaylistCollectionController playlistCollectionController) {
        this.playlistCollectionController = playlistCollectionController;
    }

    public String getViewName() {
        return viewName;
    }
}
