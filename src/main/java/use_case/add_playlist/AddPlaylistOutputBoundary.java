package use_case.add_playlist;

import java.util.List;

public interface AddPlaylistOutputBoundary {

    /**
     * Prepares success view for taking a list of playlists.
     * @param updatePlaylist output data
     */
    void prepareSuccessView(List<String> updatePlaylist);

    /**
     * Prepares failure view.
     * @param errorMessage error
     */
    void prepareFailureView(String errorMessage);
}
