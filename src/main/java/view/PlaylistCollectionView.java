package view;

import interface_adapter.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is viewing the playlist collection page in the program.
 */

public class PlaylistCollectionView extends JPanel implements ActionListener, PropertyChangeListener {

    private final PlaylistCollectionViewModel playlistCollectionViewModel;

    private final JLabel playlistCollectionName = new JLabel("Shareify - Playlist Collection");

    // Initialize components
    private JButton createPlaylistButton = new JButton("Create Playlist");
    // JList to show the names of the playlists
    private JList<String> playlistCollectionList;
    private PlaylistCollectionController playlistCollectionController;

    public PlaylistCollectionView(PlaylistCollectionViewModel playlistCollectionViewModel) {

        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing JList
        playlistCollectionList = new JList<>();

        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.playlistCollectionList.addPropertyChangeListener(this);

        // Add buttons to frame
        final JPanel buttons = new JPanel();
        buttons.add(createPlaylistButton);

        // Add scroll panel to frame for list of playlists created
        final JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.add(new JScrollPane(playlistCollectionList));

        // Action listener to create a playlist
        createPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlaylist();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding components to the frame
        this.add(playlistCollectionName);
        this.add(buttons);
        this.add(scrollPanel);
    }

    // Action method to handle the creation of a new playlist
    private void createPlaylist() {
        playlistCollectionViewModel.createNewPlaylist();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        final PlaylistCollectionState playlistCollectionState = (PlaylistCollectionState) e.getNewValue();
        setFields(playlistCollectionState);
        if (playlistCollectionState != null) {
            JOptionPane.showMessageDialog(this, playlistCollectionState.getPlaylistError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(PlaylistCollectionState playlistCollectionState) {
    }

    public void setPlaylistCollectionController(PlaylistCollectionController playlistCollectionController) {
        this.playlistCollectionController = playlistCollectionController;
    }

    public String getViewName() {
        return playlistCollectionViewModel.getViewName();
    }
}
