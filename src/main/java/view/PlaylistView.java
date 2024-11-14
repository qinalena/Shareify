package view;

import interface_adapter.playlist.PlaylistController;
import interface_adapter.playlist.PlaylistState;
import interface_adapter.playlist.PlaylistViewModel;
import interface_adapter.playlist.PlaylistController;
import interface_adapter.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection.PlaylistCollectionState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for when the user is viewing a playlist.
 */

public class PlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    private final PlaylistViewModel playlistViewModel;

    private final String userPlaylistName = "Playlist 1";

    private final JLabel playlistName = new JLabel(userPlaylistName);

    // Initialize components
    private JButton backButton = new JButton("Back");
    private JButton addSongButton = new JButton("Add Song");

    // JList to show the names of the songs within the playlist
    private JList<String> songList;
    private PlaylistController playlistCollectionController;

    public PlaylistView(PlaylistViewModel playlistViewModel) {

        // Setting label properties
        playlistName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing JList
        songList = new JList<>();

        this.playlistViewModel = playlistViewModel;
        this.songList.addPropertyChangeListener(this);

        // Add buttons to frame
        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(addSongButton);

        // Add scroll panel to frame for list of playlists created
        final JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.add(new JScrollPane(songList));

        // Action listener to add a song

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding components to the frame
        this.add(playlistName);
        this.add(buttons);
        this.add(scrollPanel);
    }

    @Override
    public void actionPerformed (ActionEvent e){
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange (PropertyChangeEvent e){
        final PlaylistCollectionState playlistCollectionState = (PlaylistCollectionState) e.getNewValue();
        setFields(playlistCollectionState);
        if (playlistCollectionState != null) {
            JOptionPane.showMessageDialog(this, playlistCollectionState.getPlaylistError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(PlaylistCollectionState playlistCollectionState) {
    }

    public void setPlaylistController(PlaylistController playlistController) {
        this.playlistController = playlistController;
    }

    public String getViewName() {
        return playlistViewModel.getViewName();
    }
}
