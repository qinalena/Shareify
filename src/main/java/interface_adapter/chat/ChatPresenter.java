package interface_adapter.chat;

import use_case.chat.ChatOutputBoundary;

import java.util.List;

/**
 * Presenter for viewing messages.
 */
public class ChatPresenter implements ChatOutputBoundary {

    private final ChatViewModel chatViewModel;

    public ChatPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void prepareSuccessView(List<String> messages) {
        chatViewModel.getState().setChatMessages(messages);
        chatViewModel.getState().setError(null);
        chatViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        chatViewModel.getState().setError(errorMessage);
        chatViewModel.firePropertyChanged();
    }
}
