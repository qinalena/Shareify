package interface_adapter.playlist_collection_user_story.playlist_collection;

import interface_adapter.ViewModel;

/**
 * The View Model for the Playlist Collection view.
 */
public class PlaylistCollectionViewModel extends ViewModel<PlaylistCollectionState> {
    public PlaylistCollectionViewModel() {
        super("playlist collection");
        setState(new PlaylistCollectionState());
    }
}