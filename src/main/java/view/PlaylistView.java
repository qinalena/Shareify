package view;

import interface_adapter.playlist.PlaylistController;
import interface_adapter.playlist.PlaylistState;
import interface_adapter.playlist.PlaylistViewModel;
import interface_adapter.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private PlaylistCollectionController playlistCollectionController;

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

        // Add buttons to frame
        final JPanel buttons = new JPanel();

        public PlaylistView(PlaylistViewModel playlistViewModel) {
            this.playlistViewModel = playlistViewModel;
        }
        buttons.add(addSongButton);

        public String getViewName() {
            return viewName;
        }
    }

}
