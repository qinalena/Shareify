package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import data_access.DBNoteDataAccessObject;
import use_case.note.DataAccessException;

public class AddFriendView extends JFrame {
    private JTextField friendNameField;
    private JButton saveButton;
    private DBNoteDataAccessObject dbNoteDataAccessObject; // DAO for checking username

    public AddFriendView(DefaultListModel<String> friendsListModel, DBNoteDataAccessObject dbNoteDataAccessObject) {
        setTitle("Add Friend");
        setSize(300, 150);
        setLayout(new FlowLayout());

        // Assign DAO to class variable
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;

        // Initialize components
        friendNameField = new JTextField(20);
        saveButton = new JButton("Save");

        // Add components to the frame
        add(new JLabel("Friend's Name:"));
        add(friendNameField);
        add(saveButton);

        // Action listener to add friend to the list if they exist
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendName = friendNameField.getText();

                if (!friendName.isEmpty()) {
                    try {
                        // Check if the user exists
                        String foundUsername = dbNoteDataAccessObject.getUserByUsername(friendName);

                        if (foundUsername != null) { // User exists
                            friendsListModel.addElement(friendName); // Add friend to list
                            dispose();
                        } else { // User does not exist
                            JOptionPane.showMessageDialog(AddFriendView.this, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (DataAccessException ex) {
                        JOptionPane.showMessageDialog(AddFriendView.this, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AddFriendView.this, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
