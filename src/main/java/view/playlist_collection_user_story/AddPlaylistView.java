package view.playlist_collection_user_story;

import data_access.DBUserDataAccessObject;
import entity.UserFactoryInter;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistController;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistViewModel;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is creating a playlist and naming it in the program.
 */
public class AddPlaylistView extends JFrame implements PropertyChangeListener {
    // This is the list we update
    private DefaultListModel<String> playlistModel;

    private AddPlaylistViewModel addPlaylistViewModel;
    private AddPlaylistController addPlaylistController;
    private JTextField playlistNameField;
    private JButton saveButton;
    private UserFactoryInter userFactory;
    private final DBUserDataAccessObject dbUserDataAccessObject = new DBUserDataAccessObject();

    public AddPlaylistView(DefaultListModel<String> playlistListModel, AddPlaylistViewModel addPlaylistViewModel) {
        this.playlistModel = playlistListModel;
        this.addPlaylistViewModel = addPlaylistViewModel;

        // UI components
        setTitle("Add Playlist");
        setSize(300, 150);
        setLayout(new FlowLayout());

        // Initialize components
        playlistNameField = new JTextField(20);
        saveButton = new JButton("Save");

        // Add components to the frame
        add(new JLabel("Name of Playlist:"));
        add(playlistNameField);
        add(saveButton);

        // Action listener to add friend to the list
        saveButton.addActionListener(e -> addPlaylist());

        // Add as listener
        addPlaylistViewModel.addPropertyChangeListener(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addPlaylist() {
        // Sets name of playlist as inputted name by user
        final String playlistName = playlistNameField.getText();
        if (!playlistModel.contains(playlistName)) {
            playlistModel.addElement(playlistName);
            addPlaylistViewModel.setNewPlaylist(playlistName);
            dispose();
        }
        else if (playlistModel.contains(playlistName)) {
            JOptionPane.showMessageDialog(this, "Playlist name already exists!",
                    "NameError", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please enter a name.", "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddPlaylistState addPlaylistState = (AddPlaylistState) evt.getNewValue();

        if (addPlaylistState.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, addPlaylistState.getErrorMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            playlistModel.clear();

            // Updates the playlist list when the state is changed
            for (String playlist : addPlaylistState.getPlaylists()) {
                playlistModel.addElement(playlist);
            }
            // Close after successful
            dispose();
        }
    }

    public void setAddPlaylistController(AddPlaylistController addPlaylistController) {
        this.addPlaylistController = addPlaylistController;
    }
}
