package view.interact_with_friends_user_story;

import interface_adapter.comment.CommentController;
import interface_adapter.comment.CommentState;
import interface_adapter.comment.CommentViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for when a user wants to comment and see comments.
 */
public class CommentView extends JPanel implements PropertyChangeListener, ActionListener {

    private final String viewName = "comment";
    private final CommentViewModel commentViewModel;

    private final JLabel commentSectionTitle = new JLabel("Comment Section");
    private final JTextArea commentInputField = new JTextArea();

    private final JButton commentButton = new JButton("Comment");
    private final JButton refreshCommentButton = new JButton("Refresh");
    private final JButton backButton = new JButton("Back");

    private final JList<String> commentsList = new JList<>();
    private final JScrollPane scrollPane = new JScrollPane(commentsList);

    private CommentController commentController;

    public CommentView(CommentViewModel commentViewModel) {

        commentSectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.commentViewModel = commentViewModel;
        this.commentViewModel.addPropertyChangeListener(this);

        // Don't want user to be able to edit the displayed comments
        commentsList.setEnabled(false);

        final JPanel buttons = new JPanel();
        buttons.add(commentButton);
        buttons.add(backButton);
        buttons.add(refreshCommentButton);

        refreshCommentButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(refreshCommentButton)) {
                        final CommentState currentState = commentViewModel.getState();
                        commentController.execute(null, currentState
                                .getFriendUsername(), currentState.getPlaylistName());

                    }
                }
        );
        commentButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(commentButton)) {
                        final CommentState currentState = commentViewModel.getState();
                        if (!commentInputField.getText().isEmpty()) {
                            commentController.execute(commentInputField.getText() + " - [" + currentState.getUsername()
                                            + "]",
                                    currentState.getFriendUsername(), currentState.getPlaylistName());
                        }
                    }
                }
        );

        backButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        commentController.switchToFriendPlaylistView();

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(commentSectionTitle);
        this.add(scrollPane);
        this.add(commentInputField);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CommentState state = (CommentState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(CommentState state) {

        if (state.getComments() == null) {
            final String[] comments = {};
            commentsList.setListData(comments);
        }
        else {
            commentsList.setListData(state.getComments().toArray(new String[0]));
        }
        commentSectionTitle.setText("Comment Section For Playlist " + state.getPlaylistName());
    }

    public String getViewName() {
        return viewName;
    }

    public void setCommentController(CommentController controller) {
        this.commentController = controller;
    }
}
