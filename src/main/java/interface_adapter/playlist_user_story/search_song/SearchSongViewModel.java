package interface_adapter.playlist_user_story.search_song;

import interface_adapter.ViewModel;

/**
 * The ViewModel for Search Track.
 */
public class SearchSongViewModel extends ViewModel<SearchSongState> {
    public SearchSongViewModel() {
        super("search track");
        setState(new SearchSongState());
    }
}
