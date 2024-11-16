package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistView extends JFrame {
    private JList<String> playlistList;
    private JButton addSongButton;
    private JButton removeSongButton;

    public PlaylistView() {
        setTitle("Playlist");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        playlistList = new JList<>(new DefaultListModel<>());
        addSongButton = new JButton("Add Song");
        removeSongButton = new JButton("Remove Song");

        // Add a sample friend to the list for testing
        ((DefaultListModel<String>) playlistList.getModel()).addElement("Sample Song");

        // Add components to the frame
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(addSongButton);
        buttons.add(removeSongButton);
        add(new JScrollPane(playlistList), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        // Button to add song to be implemented

        // Button to delete the selected song
        removeSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected index from the JList
                int selectedIndex = playlistList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Remove the selected song from the list
                    ((DefaultListModel<String>) playlistList.getModel()).remove(selectedIndex);
                }
                else {
                    JOptionPane.showMessageDialog(PlaylistView.this,
                            "No song selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to add a song to the list
    public void addSongToPlaylist(String friendName) {
        ((DefaultListModel<String>) playlistList.getModel()).addElement(friendName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlaylistView view = new PlaylistView();
            view.setVisible(true);
        });
    }
}
