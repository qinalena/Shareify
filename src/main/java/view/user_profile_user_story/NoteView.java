package view.user_profile_user_story;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import entity.User;
import interface_adapter.user_profile_user_story.note.NoteController;
import interface_adapter.user_profile_user_story.note.NoteState;
import interface_adapter.user_profile_user_story.note.NoteViewModel;

/**
 * The View for when the user is viewing a note in the program.
 */
public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "note";
    private final NoteViewModel noteViewModel;

    // Example user
    private final User user = new User("newUserName3", "password123");

    private final JLabel noteName = new JLabel("Shareify - " + user.getName());
    private final JTextArea noteInputField = new JTextArea();

    private final JButton backButton = new JButton("Back");
    private final JButton saveButton = new JButton("Save");
    private final JButton refreshButton = new JButton("Refresh");
    private NoteController noteController;

    public NoteView(NoteViewModel noteViewModel) {

        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.noteViewModel = noteViewModel;
        this.noteViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(saveButton);
        buttons.add(refreshButton);

        saveButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(saveButton)) {
                        noteController.execute(noteInputField.getText());

                    }
                }
        );

        refreshButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(refreshButton)) {
                        noteController.execute(null);

                    }
                }
        );

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        noteController.switchToUserProfileView();

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(noteName);
        this.add(noteInputField);
        this.add(buttons);
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
        final NoteState state = (NoteState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(NoteState state) {
        noteInputField.setText(state.getNote());
    }

    public void setNoteController(NoteController controller) {
        this.noteController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}

