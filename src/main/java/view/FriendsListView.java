package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendsListView extends JFrame {
    private JList<String> friendsList;
    private JButton addFriendButton;
    private JButton deleteFriendButton;

    public FriendsListView() {
        setTitle("Friends List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        friendsList = new JList<>(new DefaultListModel<>());
        addFriendButton = new JButton("Add Friend");
        deleteFriendButton = new JButton("Delete Friend");

        // Add a sample friend to the list for testing
        ((DefaultListModel<String>) friendsList.getModel()).addElement("Sample Friend");

        // Add components to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addFriendButton);
        buttonPanel.add(deleteFriendButton);
        add(new JScrollPane(friendsList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button to open Add Friend screen
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFriendView((DefaultListModel<String>) friendsList.getModel()).setVisible(true);
            }
        });

        // Button to delete the selected friend
        deleteFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected index from the JList
                int selectedIndex = friendsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Remove the selected friend from the list
                    ((DefaultListModel<String>) friendsList.getModel()).remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(FriendsListView.this, "No friend selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to add a friend to the list (could be called by a ViewModel later)
    public void addFriendToList(String friendName) {
        ((DefaultListModel<String>) friendsList.getModel()).addElement(friendName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FriendsListView view = new FriendsListView();
            view.setVisible(true);
        });
    }
}
