package view.playlist_user_story;

import entity.Playlist;
import entity.Track;
import interface_adapter.playlist_user_story.PlaylistController;
import interface_adapter.playlist_user_story.PlaylistState;
import interface_adapter.playlist_user_story.PlaylistViewModel;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View for when a playlist is clicked on in PlaylistCollection View.
 */
public class PlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "playlist";

    private final PlaylistViewModel playlistViewModel;

    private PlaylistController playlistController;

    private JLabel playlistTitle = new JLabel();

    private final JButton backButton = new JButton("Back");
    private final JButton searchButton = new JButton("Search Tracks");

    private JList<String> tracks = new JList<>(new DefaultListModel<>());

    public PlaylistView(PlaylistViewModel playlistViewModel) {
        this.playlistViewModel = playlistViewModel;
        this.playlistViewModel.addPropertyChangeListener(this);

        playlistTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(searchButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playlistController.switchToPlaylistCollectionView();
            }
        }
        );

        tracks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tracks.setLayoutOrientation(JList.VERTICAL);
        final JScrollPane scrollPane = new JScrollPane(tracks);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(playlistTitle);
        this.add(buttons);
        this.add(scrollPane);

    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click" + evt.getActionCommand());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PlaylistState state = (PlaylistState) evt.getNewValue();
        setFields(state);

    }

    private void setFields(PlaylistState state) {
        final Playlist currentPlaylist = state.getCurrentPlaylist();
        playlistTitle.setText(currentPlaylist.getName());

        final DefaultListModel<String> listModel = (DefaultListModel<String>) tracks.getModel();
        listModel.clear();

        for (Track track : currentPlaylist.getTracks()) {
            listModel.addElement(track.getName() + " - " + track.getArtist());
        }
    }

    public void setPlaylistController(PlaylistController playlistController) {
        this.playlistController = playlistController;
    }

    public String getViewName() {
        return viewName;
    }
}