package view;

import interface_adapter.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is viewing the playlist collection page in the program.
 */

public class PlaylistCollectionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "playlist collection";

    private final PlaylistCollectionViewModel playlistCollectionViewModel;

    private final JLabel playlistCollectionName = new JLabel("Shareify - Playlist Collection");

    // Initialize components
    private JButton createPlaylistButton = new JButton("Create Playlist");
    private JButton deletePlaylistButton = new JButton("Delete Playlist");
    private PlaylistCollectionController playlistCollectionController;

    // JList to show the names of the playlists
    private JList<String> playlistCollectionList;

    public PlaylistCollectionView(PlaylistCollectionViewModel playlistCollectionViewModel) {
        // Setting label properties
        playlistCollectionName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing JList
        playlistCollectionList = new JList<>(new DefaultListModel<>());

        // Add a sample friend to the list for testing
        ((DefaultListModel<String>) playlistCollectionList.getModel()).addElement("Sample Playlist");

        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.playlistCollectionViewModel.addPropertyChangeListener(this);

        // Add buttons to frame
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(createPlaylistButton);
        buttons.add(deletePlaylistButton);
        add(buttons, BorderLayout.SOUTH);

        // Button to open Add Playlist screen
        createPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPlaylistView((DefaultListModel<String>) playlistCollectionList.getModel()).setVisible(true);
            }
        });

        // Button to delete the selected playlist
        deletePlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected index from the JList
                int selectedIndex = playlistCollectionList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Remove the selected playlist from the list
                    ((DefaultListModel<String>) playlistCollectionList.getModel()).remove(selectedIndex);
                }
                else {
                    JOptionPane.showMessageDialog(PlaylistCollectionView.this,
                            "No playlist selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding components to the frame
        this.add(playlistCollectionName);
        this.add(buttons);
        // Add scroll panel to frame for list of playlists created
        this.add(new JScrollPane(playlistCollectionList), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        final Object newValue = e.getNewValue();
        if (newValue instanceof PlaylistCollectionState) {
            final PlaylistCollectionState state = (PlaylistCollectionState) newValue;
            setFields(state);

            if (state.getPlaylistError() != null) {
                JOptionPane.showMessageDialog(this, state.getPlaylistError(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            // Handle unexpected object type (log or throw an exception)
            System.err.println("Unexpected value in property change: " + newValue.getClass().getName());
        }
    }

    private void setFields(PlaylistCollectionState playlistCollectionState) {
    }

    public void setPlaylistCollectionController(PlaylistCollectionController playlistCollectionController) {
        this.playlistCollectionController = playlistCollectionController;
    }

    public String getViewName() {
        return viewName;
    }
}
