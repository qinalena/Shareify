package view.friends_list_user_story;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import entity.Playlist;
import entity.Song;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistController;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistState;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistViewModel;

/**
 * The View for when a playlist is clicked on in PlaylistCollection View.
 */
public class FriendPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendPlaylist";

    private final FriendPlaylistViewModel friendPlaylistViewModel;

    private FriendPlaylistController friendPlaylistController;

    private JLabel playlistTitle = new JLabel();

    private final JButton backButton = new JButton("Back");
    private final JButton comment = new JButton("Comment");
    private String friendUsername;
    private String friendPassword;
    private String playlistName;

    private JList<String> songs = new JList<>(new DefaultListModel<>());

    public FriendPlaylistView(FriendPlaylistViewModel friendPlaylistViewModel) {
        this.friendPlaylistViewModel = friendPlaylistViewModel;
        this.friendPlaylistViewModel.addPropertyChangeListener(this);

        playlistTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(comment);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                friendPlaylistController.switchToPlaylistCollectionView();
            }
        });

        comment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                friendPlaylistController.switchToCommentView(friendUsername, playlistName);
            }
        });

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
        final FriendPlaylistState state = (FriendPlaylistState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(FriendPlaylistState state) {
        final Playlist currentPlaylist = state.getCurrentPlaylist();
        playlistTitle.setText(currentPlaylist.getName());

        final DefaultListModel<String> listModel = (DefaultListModel<String>) songs.getModel();
        listModel.clear();

        for (Song song : currentPlaylist.getSongs()) {
            listModel.addElement(song.toString());
        }
        if (this.friendUsername != state.getFriendUsername()) {
            this.friendUsername = state.getFriendUsername();
            this.friendPassword = state.getFriendPassword();
            this.playlistName = state.getCurrentPlaylist().getName();
            playlistTitle.setText(this.friendUsername + "'s Playlist: " + state.getCurrentPlaylist().getName());
        }
    }

    public void setPlaylistController(FriendPlaylistController controller) {
        this.friendPlaylistController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}
