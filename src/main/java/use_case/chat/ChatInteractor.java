package use_case.chat;

import use_case.DataAccessException;

import java.util.List;

/**
 * Interactor for the chat usecases.
 */
public class ChatInteractor implements ChatInputBoundary {

    private final ChatDataAccessInterface chatDataAccessInterface;
    private final ChatOutputBoundary chatOutputBoundary;

    public ChatInteractor(ChatDataAccessInterface chatDataAccessInterface,
                          ChatOutputBoundary chatOutputBoundary) {
        this.chatDataAccessInterface = chatDataAccessInterface;
        this.chatOutputBoundary = chatOutputBoundary;
    }

    @Override
    public void executeSaveMessage(String message, String sender, String reciever) {
        try {
            final List<String> messages = chatDataAccessInterface
                    .saveChatBetweenUsers(message, sender, reciever);
            chatOutputBoundary.prepareSuccessView(messages);
        }
        catch (DataAccessException ex) {
            chatOutputBoundary.prepareFailView(ex.getMessage());
        }
    }

    @Override
    public void executeLoadMessages(String sender, String reciever) {
        try {
            final List<String> messages = chatDataAccessInterface
                    .loadChatBetweenUsers(sender, reciever);
            chatOutputBoundary.prepareSuccessView(messages);
        }
        catch (DataAccessException ex) {
            chatOutputBoundary.prepareFailView(ex.getMessage());
        }
    }
}
