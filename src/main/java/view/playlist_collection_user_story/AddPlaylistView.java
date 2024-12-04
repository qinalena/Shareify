package view.playlist_collection_user_story;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import entity.User;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistController;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistState;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionController;
import view.login_user_story.LabelTextPanel;

/**
 * The View for when the user is creating a playlist and naming it in the program.
 */
public class AddPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    // This is the list we update
    private DefaultListModel<String> playlistCollectionModel;

    private String viewName = "add playlist";

    private AddPlaylistViewModel addPlaylistViewModel = new AddPlaylistViewModel();
    private AddPlaylistController addPlaylistController;
    private PlaylistCollectionController playlistCollectionController;

    private final int columnNum = 20;
    private JTextField playlistNameField = new JTextField(columnNum);

    private final JButton saveButton = new JButton("Save");
    private final JButton cancelButton = new JButton("Cancel");

    private String username;
    private String password;

    /**
     * Constructs AddPlaylistView.
     * @param playlistCollectionModel model for playlist collection
     * @param addPlaylistViewModel view model for adding a playlist
     */
    public AddPlaylistView(DefaultListModel<String> playlistCollectionModel,
                           AddPlaylistViewModel addPlaylistViewModel) {

        this.playlistCollectionModel = playlistCollectionModel;
        this.addPlaylistViewModel = addPlaylistViewModel;
        this.addPlaylistViewModel.addPropertyChangeListener(this);

        // UI components
        final LabelTextPanel playlistName = new LabelTextPanel(
                new JLabel("Enter playlist name: "), playlistNameField);

        // Add components to the frame
        final JPanel buttons = new JPanel();
        buttons.add(cancelButton);
        buttons.add(saveButton);

        buttons.setLayout(new FlowLayout());

        // Action listener to add friend to the list
        saveButton.addActionListener(evt -> {
            if (evt.getSource().equals(saveButton)) {
                addPlaylist();
                // Clear text field after saving
                playlistNameField.setText("");
            }
        }
        );

        cancelButton.addActionListener(evt -> {
            if (evt.getSource().equals(cancelButton)) {
                // switch to playlist collection view
                this.addPlaylistController.switchToPlaylistCollectionView();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add the field and buttons
        this.add(playlistName);
        this.add(buttons);
    }

    /**
     * Adds playlist to the playlist collection if user exists.
     */
    private void addPlaylist() {
        // Sets name of playlist as inputted name by user
        final String playlistName = playlistNameField.getText();
        if (!playlistName.isEmpty()) {
            // Construct User object from stored username and password
            final User user = new User(username, password);

            // Notify controller
            playlistCollectionController.addPlaylist(user, playlistName);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please enter a name.",
                    "Error playlistName", JOptionPane.ERROR_MESSAGE);
        }
        addPlaylistController.switchToPlaylistCollectionView();
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddPlaylistState addPlaylistState = (AddPlaylistState) evt.getNewValue();
        this.username = addPlaylistState.getUsername();
        this.password = addPlaylistState.getPassword();
    }

    /**
     * Sets the AddPlaylistController for this view.
     * @param addPlaylistController the controller to set
     */
    public void setAddPlaylistController(AddPlaylistController addPlaylistController) {
        this.addPlaylistController = addPlaylistController;
    }

    public void setPlaylistCollectionController(PlaylistCollectionController playlistCollectionController) {
        this.playlistCollectionController = playlistCollectionController;
    }
}
