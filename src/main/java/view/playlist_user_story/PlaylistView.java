package view.playlist_user_story;

import interface_adapter.playlist_user_story.PlaylistController;
import interface_adapter.playlist_user_story.PlaylistViewModel;

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

    // Hardcoded example
    private JLabel playlistTitle = new JLabel("Playlist 1");

    private final JButton backButton = new JButton("Back");
    private final JButton searchButton = new JButton("Search Tracks");

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(playlistTitle);
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

    }

    public void setPlaylistController(PlaylistController playlistController) {
        this.playlistController = playlistController;
    }

    public String getViewName() {
        return viewName;
    }
}
