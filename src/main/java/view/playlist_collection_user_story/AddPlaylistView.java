package view.playlist_collection_user_story;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import entity.User;
import data_access.DBPlaylistDataAccessObject;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistController;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistViewModel;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistState;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionController;
import use_case.user_profile_user_story.note.DataAccessException;
import view.login_user_story.LabelTextPanel;

/**
 * The View for when the user is creating a playlist and naming it in the program.
 */
public class AddPlaylistView extends JPanel implements PropertyChangeListener {
    // This is the list we update
    private DefaultListModel<String> playlistModel;

    private String viewName = "add playlist";

    private AddPlaylistViewModel addPlaylistViewModel;
    private AddPlaylistController addPlaylistController;

    private JTextField playlistNameField = new JTextField(20);

    private final JButton saveButton = new JButton("Save");
    private final JButton cancelButton = new JButton("Cancel");
    private PlaylistCollectionController playlistCollectionController;
    private final DBPlaylistDataAccessObject dbPlaylistDataAccessObject = new DBPlaylistDataAccessObject();
    private String username;
    private String password;

    /**
     * Constructs AddPlaylistView.
     * @param playlistListModel model for playlist collection
     * @param addPlaylistViewModel view model for adding a playlist
     * @param playlistCollectionController controller for playlist collection
     */
    public AddPlaylistView(DefaultListModel<String> playlistListModel, AddPlaylistViewModel addPlaylistViewModel,
                           PlaylistCollectionController playlistCollectionController) {

        this.playlistModel = playlistListModel;
        this.addPlaylistViewModel = addPlaylistViewModel;
        this.playlistCollectionController = playlistCollectionController;
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
            }
        }
        );

        cancelButton.addActionListener(evt -> {
            if (evt.getSource().equals(cancelButton)) {
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
            try {
                if (!playlistModel.contains(playlistName)) {
                    // Add playlist to model
                    playlistModel.addElement(playlistName);
                    // Notify controller
                    playlistCollectionController.addPlaylist(playlistName);
                    // Save to database
                    dbPlaylistDataAccessObject.addPlaylist(new User(username, password), playlistName);

                    this.addPlaylistController.switchToPlaylistCollectionView();
                }
                else {
                    JOptionPane.showMessageDialog(this, "Playlist already exist!",
                            "Error Playlist Exists", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (DataAccessException evt) {
                JOptionPane.showMessageDialog(this, evt.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please enter a name.", "Error",
                JOptionPane.ERROR_MESSAGE);
        }
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

    public String getViewName() {
        return viewName;
    }
}
