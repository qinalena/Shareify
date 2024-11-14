package use_case.note.playlist_collection;

/**
 * The Input Boundary for our playlist collection related use cases.
 */

public interface PlaylistCollectionInputBoundary {

    /**
     * Executes the create playlist use case.
     * @param playlist the input data
     */

    void executeCreatePlaylist(List playlist);


}
