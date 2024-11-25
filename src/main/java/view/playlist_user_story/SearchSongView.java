package view.playlist_user_story;

import interface_adapter.playlist_user_story.search_song.SearchSongController;
import interface_adapter.playlist_user_story.search_song.SearchSongViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View when Search Track is clicked on in Playlist View.
 */
public class SearchSongView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search track";

    private final SearchSongViewModel searchSongViewModel;

    private SearchSongController searchTrackController;

    private JLabel instructions = new JLabel("Search track by title");

    private JTextField searchText = new JTextField();

    private final JButton backButton = new JButton("Back");
    private final JButton searchButton = new JButton("Search");
    private final JButton addTrackButton = new JButton("Add Track");

    private JList<String> tracks = new JList<>(new DefaultListModel<>());

    public SearchSongView(SearchSongViewModel searchSongViewModel) {
        this.searchSongViewModel = searchSongViewModel;
        this.searchSongViewModel.addPropertyChangeListener(this);

        instructions.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(searchButton);
        buttons.add(addTrackButton);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchTrackController.searchSong(searchText.getText());
            }
        }
        );

        tracks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tracks.setLayoutOrientation(JList.VERTICAL);
        final JScrollPane scrollPane = new JScrollPane(tracks);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(instructions);
        this.add(searchText);
        this.add(scrollPane);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click" + evt.getActionCommand());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setSearchTrackController(SearchSongController searchTrackController) {
        this.searchTrackController = searchTrackController;
    }

    public String getViewName() {
        return viewName;
    }
}
