package interface_adapter.user_profile;

import use_case.user_profile.UserProfileOutputBoundary;

/**
 * The presenter for our Note viewing and editing program.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final UserProfileViewModel userProfileViewModel;

    public UserProfilePresenter(UserProfileViewModel userProfileViewModel) {
        this.userProfileViewModel = userProfileViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param note the output data
     */
    @Override
    public void prepareSuccessView(String note) {
        userProfileViewModel.getState().setError(null);
        userProfileViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        userProfileViewModel.getState().setError(errorMessage);
        userProfileViewModel.firePropertyChanged();
    }
}
