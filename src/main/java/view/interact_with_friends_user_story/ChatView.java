package view.interact_with_friends_user_story;

import interface_adapter.chat.ChatController;
import interface_adapter.chat.ChatState;
import interface_adapter.chat.ChatViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatView extends JPanel implements PropertyChangeListener, ActionListener {

    private final String viewName = "chat";
    private final ChatViewModel chatViewModel;

    private final JLabel chatSectionTitle = new JLabel("Chat");
    private final JTextArea chatInputField = new JTextArea();

    private final JButton messageButton = new JButton("Send Message");
    private final JButton refreshMessagesButton = new JButton("Refresh Messages");
    private final JButton backButton = new JButton("Back");

    private final JList<String> messagesList = new JList<>();
    private final JScrollPane scrollPane = new JScrollPane(messagesList);

    private ChatController chatController;

    public ChatView(ChatViewModel chatViewModel) {

        chatSectionTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.chatViewModel = chatViewModel;
        this.chatViewModel.addPropertyChangeListener(this);

        // Don't let user change the displayed messages
        messagesList.setEnabled(false);

        final JPanel buttons = new JPanel();
        buttons.add(messageButton);
        buttons.add(refreshMessagesButton);
        buttons.add(backButton);

        messageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(messageButton)) {
                        final ChatState chatState = chatViewModel.getState();
                        if (!chatInputField.getText().isEmpty()) {
                            chatController.execute(chatInputField
                                            .getText() + " - [" + chatState.getUsername() + "]",
                                    chatState.getUsername(), chatState.getFriendUsername());
                        }
                    }
                }
        );

        refreshMessagesButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(refreshMessagesButton)) {
                        final ChatState chatState = chatViewModel.getState();
                        chatController.execute(null, chatState.getUsername(), chatState.getFriendUsername());

                    }
                }
        );

        backButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        chatController.switchToFriendView();

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(chatSectionTitle);
        this.add(scrollPane);
        this.add(chatInputField);
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
        final ChatState state = (ChatState) evt.getNewValue();

        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(ChatState state) {
        if (state.getChatMessages() == null) {
            final String[] chatMessages = {};
            messagesList.setListData(chatMessages);
        }
        else {
            messagesList.setListData(state.getChatMessages().toArray(new String[0]));
        }
        chatSectionTitle.setText("Chat with " + state.getFriendUsername());
    }

    public String getViewName() {
        return viewName;
    }

    public void setChatController(ChatController controller) {
        this.chatController = controller;
    }
}
