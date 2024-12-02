package use_case.chat;

import java.util.List;

/**
 * Interface the Presenter implements for chat uses cases.
 */
public interface ChatOutputBoundary {

    /**
     * Prepares the success view for chat use cases.
     * @param messages the output data of messages
     */
    void prepareSuccessView(List<String> messages);

    /**
     * Prepares the failure view for the chat related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to friend view when back button is pressed.
     */
    void switchToFriendView();
}
