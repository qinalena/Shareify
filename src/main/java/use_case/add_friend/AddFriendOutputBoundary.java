package use_case.add_friend;

import java.util.List;

public interface AddFriendOutputBoundary {

    // Method for handling success, taking a list of friends as an argument
    void prepareSuccessView(List<String> updatedFriendsList);

    // Method for handling failure, taking an error message as an argument
    void prepareFailView(String errorMessage);

    void swtichToFriendsListView();
}
