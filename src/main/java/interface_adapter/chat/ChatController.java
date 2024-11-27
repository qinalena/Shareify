package interface_adapter.chat;

import use_case.chat.ChatInputBoundary;

/**
 * Controller for chat use cases.
 */
public class ChatController {

    private final ChatInputBoundary chatUseCaseInteractor;

    public ChatController(ChatInputBoundary chatUseCaseInteractor) {
        this.chatUseCaseInteractor = chatUseCaseInteractor;
    }

    /**
     * Tells chat interactor to execute chat use cases.
     * @param message we would like to send (may be null for loading in)
     * @param sender of message
     * @param reciever of message
     */
    public void execute(String message, String sender, String reciever) {
        if (message != null) {
            chatUseCaseInteractor.executeSaveMessage(message, sender, reciever);
        }
        else {
            chatUseCaseInteractor.executeLoadMessages(sender, reciever);
        }
    }
}
