package view.playlist_user_story;

import interface_adapter.playlist_user_story.playlist.PlaylistController;
import interface_adapter.playlist_user_story.playlist.PlaylistState;
import interface_adapter.playlist_user_story.playlist.PlaylistViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when a playlist is clicked on in PlaylistCollection View.
 */
public class PlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "playlist";

    private final PlaylistViewModel playlistViewModel;

    private PlaylistController playlistController;

    private JLabel playlistTitle = new JLabel();

    private final JButton backButton = new JButton("Back");
    private final JButton removeSongButton = new JButton("Remove Song");
    private final JButton searchButton = new JButton("Search Song");

    private JList<String> songs = new JList<>(new DefaultListModel<>());

    public PlaylistView(PlaylistViewModel playlistViewModel) {
        this.playlistViewModel = playlistViewModel;
        this.playlistViewModel.addPropertyChangeListener(this);

        playlistTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(removeSongButton);
        buttons.add(searchButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playlistController.switchToPlaylistCollectionView();
            }
        }
        );

        removeSongButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (songs.getSelectedIndex() != -1) {
                    playlistController.removeSong(playlistViewModel.getState().getCurrentPlaylistName(),
                            songs.getSelectedIndex());
                }
            }
        }
        );

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playlistController.switchToSearchSongView(playlistViewModel.getState().getCurrentPlaylistName());
            }
        }
        );

        songs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songs.setLayoutOrientation(JList.VERTICAL);
        final JScrollPane scrollPane = new JScrollPane(songs);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(playlistTitle);
        this.add(scrollPane);
        this.add(buttons);

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
        final String currentPlaylistName = state.getCurrentPlaylistName();
        playlistTitle.setText(currentPlaylistName);

        final DefaultListModel<String> listModel = (DefaultListModel<String>) songs.getModel();
        listModel.clear();

        for (String song : state.getSongs()) {
            listModel.addElement(song);
        }
    }

    public void setPlaylistController(PlaylistController playlistController) {
        this.playlistController = playlistController;
    }

    public String getViewName() {
        return viewName;
    }
}
