package view;

import data_access.DBUserDataAccessObject;
import entity.UserFactoryInter;
import interface_adapter.add_playlist.AddPlaylistController;
import interface_adapter.add_playlist.AddPlaylistViewModel;
import interface_adapter.add_playlist.AddPlaylistState;
import use_case.note.DataAccessException;

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
    private final DBUserDataAccessObject dbUserDataAccessObject = new DBUserDataAccessObject(userFactory);
    private String UserFactoryInter;

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
        saveButton.addActionListener(evt -> {
            try {
                addPlaylist();
            }
            catch (DataAccessException playlistEvent) {
                throw new RuntimeException(playlistEvent);
            }
        });

        // Add as listener
        addPlaylistViewModel.addPropertyChangeListener(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addPlaylist() throws DataAccessException {
        // Sets name of playlist as inputted name by user
        final String playlistName = playlistNameField.getText();
        try {
            if (!playlistModel.contains(playlistName)) {
                playlistModel.addElement(playlistName);
                addPlaylistViewModel.setNewPlaylist(playlistName);
                // Should save to database under that user - there's a bug that username is null
                dbUserDataAccessObject.addPlaylistToUser(
                        dbUserDataAccessObject.get(UserFactoryInter), playlistName);
                dispose();
            }
            else if (playlistModel.contains(playlistName)) {
                JOptionPane.showMessageDialog(this, "Playlist name already exists!",
                        "NameError", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this, "Please enter a name.", "Empty Name Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (DataAccessException evt) {
            JOptionPane.showMessageDialog(null, evt.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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