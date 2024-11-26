package view.playlist_user_story;

import entity.Song;
import interface_adapter.playlist_user_story.search_song.SearchSongController;
import interface_adapter.playlist_user_story.search_song.SearchSongState;
import interface_adapter.playlist_user_story.search_song.SearchSongViewModel;
import view.login_user_story.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
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

    private JTextField searchInputField = new JTextField(15);

    private final JButton backButton = new JButton("Back");
    private final JButton searchButton = new JButton("Search");
    private final JButton addSongButton = new JButton("Add Song");

    private JList<String> songs = new JList<>(new DefaultListModel<>());

    public SearchSongView(SearchSongViewModel searchSongViewModel) {
        this.searchSongViewModel = searchSongViewModel;
        this.searchSongViewModel.addPropertyChangeListener(this);

        final LabelTextPanel search = new LabelTextPanel(
                new JLabel("Search"), searchInputField);
        searchInputField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchInputField.setMargin(new Insets(5, 5, 5, 5));
        searchInputField.setToolTipText("Enter your username");

        final JPanel buttons = new JPanel();
        buttons.add(backButton);
        buttons.add(searchButton);
        buttons.add(addSongButton);

        backButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
               searchTrackController.switchToPlaylistView();
           }
        }
        );

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchTrackController.searchSong(searchInputField.getText());
            }
        }
        );

        addSongButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchTrackController.addSong(songs.getSelectedValue());
            }
        }
        );

        songs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songs.setLayoutOrientation(JList.VERTICAL);
        final JScrollPane scrollPane = new JScrollPane(songs);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(search);
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
        final SearchSongState state = (SearchSongState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(SearchSongState state) {
        final DefaultListModel<String> listModel = (DefaultListModel<String>) songs.getModel();
        listModel.clear();

        for (Song song : state.getSearchResults()) {
            listModel.addElement(song.getName() + " - " + song.artistsToString());
        }
    }

    public void setSearchTrackController(SearchSongController searchTrackController) {
        this.searchTrackController = searchTrackController;
    }

    public String getViewName() {
        return viewName;
    }
}
