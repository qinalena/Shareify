package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriendView extends JFrame {
    private JTextField friendNameField;
    private JButton saveButton;

    public AddFriendView(DefaultListModel<String> friendsListModel) {
        setTitle("Add Friend");
        setSize(300, 150);
        setLayout(new FlowLayout());

        // Initialize components
        friendNameField = new JTextField(20);
        saveButton = new JButton("Save");

        // Add components to the frame
        add(new JLabel("Friend's Name:"));
        add(friendNameField);
        add(saveButton);

        // Action listener to add friend to the list
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendName = friendNameField.getText();
                if (!friendName.isEmpty()) {
                    friendsListModel.addElement(friendName);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddFriendView.this, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
