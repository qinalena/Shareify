package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for when the user is creating a playlist and naming it in the program.
 */

public class AddPlaylistView extends JFrame {
    private JTextField playlistNameField;
    private JButton saveButton;

    public AddPlaylistView(DefaultListModel<String> playlistListModel) {
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String playlistName = playlistNameField.getText();
                if (!playlistName.isEmpty()) {
                    playlistListModel.addElement(playlistName);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(AddPlaylistView.this, "Please enter a name.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
