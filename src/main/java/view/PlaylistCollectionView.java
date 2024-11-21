package view;

import data_access.DBUserDataAccessObject;
import interface_adapter.add_playlist.AddPlaylistController;
import interface_adapter.add_playlist.AddPlaylistPresenter;
import interface_adapter.add_playlist.AddPlaylistViewModel;
import interface_adapter.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;
import use_case.add_playlist.AddPlaylistInputBoundary;
import use_case.add_playlist.AddPlaylistInteractor;
import use_case.add_playlist.AddPlaylistOutputBoundary;

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
    private JButton createPlaylistButton = new JButton("Create Playlist");
    private JButton deletePlaylistButton = new JButton("Delete Playlist");

    // JList to show the names of the playlists
    private JList<String> playlistCollectionList = new JList<>(new DefaultListModel<>());

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
        buttons.setLayout(new FlowLayout());
        buttons.add(createPlaylistButton);
        buttons.add(deletePlaylistButton);

        // Set up button actions
        createPlaylistButton.addActionListener(this);
        deletePlaylistButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding components to the frame
        this.add(playlistCollectionName);
        this.add(buttons);
        // Add scroll panel to frame for list of playlists created
        this.add(scrollPane);
    }

    private List<String> playlistListModelToList() {
        final DefaultListModel<String> playlistListModel = (DefaultListModel<String>) playlistCollectionList.getModel();
        final List<String> playlistList = new ArrayList<>();
        for (int i = 0; i < playlistListModel.size(); i++) {
            playlistList.add(playlistListModel.get(i));
        }
        return playlistList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Button to open Add Playlist screen
        if (e.getSource() == createPlaylistButton) {
            this.addPlaylistInputBoundary = new AddPlaylistInteractor(
                    dbUserDataAccessObject, addPlaylistOutputBoundary, playlistListModelToList()
            );

            // Create ViewModel
            final AddPlaylistViewModel addPlaylistViewModel = new AddPlaylistViewModel();

            // Create Presenter
            final AddPlaylistPresenter addPlaylistPresenter = new AddPlaylistPresenter(addPlaylistViewModel);

            // Create Controller
            final AddPlaylistController addPlaylistController = new AddPlaylistController(addPlaylistInputBoundary);

            // Create + configure AddPlaylistView
            final AddPlaylistView addPlaylistView = new AddPlaylistView(
                    (DefaultListModel<String>) playlistCollectionList.getModel(),
                    addPlaylistViewModel
            );
            // Injects the controller
            addPlaylistView.setAddPlaylistController(addPlaylistController);

            // Displays the AddPlaylist window
            addPlaylistView.setVisible(true);
        }
        // Button to delete the selected playlist
        else if (e.getSource() == deletePlaylistButton) {
            final int[] selectedIndices = playlistCollectionList.getSelectedIndices();
            if (selectedIndices.length > 0) {
                final DefaultListModel<String> listModel = (DefaultListModel<String>) playlistCollectionList.getModel();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    listModel.remove(selectedIndices[i]);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a playlist to delete.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
     * @param playlistCollectionState
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

    /**
     * Set up DbUserDAO + reinitialize AddPlaylistInputBoundary if dependencies change.
     * @param dbUserDataAccessObject output data
     */
    public void setDbUserDataAccessObject(DBUserDataAccessObject dbUserDataAccessObject) {
        this.dbUserDataAccessObject = dbUserDataAccessObject;

        if (this.addPlaylistOutputBoundary != null) {
            this.addPlaylistInputBoundary = new AddPlaylistInteractor(
                    dbUserDataAccessObject, addPlaylistOutputBoundary, playlistListModelToList()
            );
        }
    }

    /**
     * Set up addPlaylistOutputBoundary + reinitialize AddPlaylistInputBoundary if dependencies change.
     * @param addPlaylistOutputBoundary output data
     */
    public void setAddPlaylistOutputBoundary(AddPlaylistOutputBoundary addPlaylistOutputBoundary) {
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;

        if (this.dbUserDataAccessObject != null) {
            this.addPlaylistInputBoundary = new AddPlaylistInteractor(
                    dbUserDataAccessObject, addPlaylistOutputBoundary, playlistListModelToList()
            );
        }
    }

    public String getViewName() {
        return viewName;
    }
}
