package interface_adapter.chat;

import interface_adapter.ViewModel;

/**
 * Viewmodel for chat.
 */
public class ChatViewModel extends ViewModel<ChatState> {

    public ChatViewModel() {
        super("chat");
        setState(new ChatState());
    }
}
