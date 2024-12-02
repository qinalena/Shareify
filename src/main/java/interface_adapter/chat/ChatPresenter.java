package interface_adapter.chat;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileViewModel;
import use_case.chat.ChatOutputBoundary;

import java.util.List;

/**
 * Presenter for viewing messages.
 */
public class ChatPresenter implements ChatOutputBoundary {

    private final ChatViewModel chatViewModel;
    private final ViewManagerModel viewManagerModel;
    private final FriendProfileViewModel friendProfileViewModel;

    public ChatPresenter(ChatViewModel chatViewModel, ViewManagerModel viewManagerModel,
                         FriendProfileViewModel friendProfileViewModel) {
        this.chatViewModel = chatViewModel;
        this.viewManagerModel = viewManagerModel;
        this.friendProfileViewModel = friendProfileViewModel;
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

    @Override
    public void switchToFriendView() {
        viewManagerModel.setState(friendProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
