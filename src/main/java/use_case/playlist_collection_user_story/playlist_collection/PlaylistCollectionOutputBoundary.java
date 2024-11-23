package use_case.playlist_collection_user_story.playlist_collection;

/**
 * The output boundary for Playlist Collection.
 */
public interface PlaylistCollectionOutputBoundary {

    /**
     * Prepares success view for playlist collection use cases.
     * @param playlistName ouput data
     */
    void preparePlaylistAddedView(String playlistName);

    /**
     * Prepare remove playlist case.
     * @param playlistName output data
     */
    void preparePlaylistRemovedView(String playlistName);

    /**
     * Prepares failure view for playlist collection use cases.
     * @param error message indicating issue
     */
    void prepareFailView(String error);
}
