package view;

import data_access.DBNoteDataAccessObject;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.add_friend.AddFriendPresenter;
import interface_adapter.add_friend.AddFriendViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.friends_list.FriendsListState;
import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInteractor;
import use_case.add_friend.AddFriendOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class FriendsListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "friendsList";
    private final FriendsListViewModel viewModel;
    private FriendsListController controller;
    private AddFriendOutputBoundary addFriendOutputBoundary;
    private DBNoteDataAccessObject dbNoteDataAccessObject;

    // Moved initialization to the constructor to prevent premature calls with uninitialized dependencies
    private AddFriendInputBoundary addFriendInputBoundary;

    // UI components
    private final JList<String> friendsList = new JList<>(new DefaultListModel<>());
    private final JButton addFriendButton = new JButton("Add Friend");
    private final JButton deleteFriendButton = new JButton("Delete Friend");

    public FriendsListView(FriendsListController controller, FriendsListViewModel viewModel,
                           DBNoteDataAccessObject dbNoteDataAccessObject, AddFriendOutputBoundary addFriendOutputBoundary) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
        this.addFriendOutputBoundary = addFriendOutputBoundary;
        this.viewModel.addPropertyChangeListener(this);

        // Initialize AddFriendInputBoundary here after dependencies are set
        if (this.dbNoteDataAccessObject != null && this.addFriendOutputBoundary != null) {
            this.addFriendInputBoundary = new AddFriendInteractor(
                    dbNoteDataAccessObject,
                    addFriendOutputBoundary,
                    friendsListModelToList()
            );
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Layout for the Friends List view
        friendsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friendsList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scrollPane = new JScrollPane(friendsList);

        // Buttons for adding and deleting friends
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addFriendButton);
        buttonPanel.add(deleteFriendButton);

        // Add components to panel
        add(scrollPane);
        add(buttonPanel);

        // Set up button actions
        addFriendButton.addActionListener(this);
        deleteFriendButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addFriendButton) {
            this.addFriendInputBoundary = new AddFriendInteractor(
                    dbNoteDataAccessObject,
                    addFriendOutputBoundary,
                    friendsListModelToList()
            );

            // Create ViewModel
            AddFriendViewModel addFriendViewModel = new AddFriendViewModel();

            // Create Presenter
            AddFriendPresenter addFriendPresenter = new AddFriendPresenter(addFriendViewModel);

            // Create Controller
            AddFriendController addFriendController = new AddFriendController(addFriendInputBoundary);

            // Create and configure the AddFriendView
            AddFriendView addFriendView = new AddFriendView(
                    (DefaultListModel<String>) friendsList.getModel(),
                    addFriendViewModel
            );
            addFriendView.setAddFriendController(addFriendController); // Inject the Controller
            addFriendView.setVisible(true); // Display the Add Friend window
        }
        else if (evt.getSource() == deleteFriendButton) {
            int[] selectedIndices = friendsList.getSelectedIndices();
            if (selectedIndices.length > 0) {
                DefaultListModel<String> listModel = (DefaultListModel<String>) friendsList.getModel();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    listModel.remove(selectedIndices[i]);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a friend to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<String> friendsListModelToList() {
        DefaultListModel<String> model = (DefaultListModel<String>) friendsList.getModel();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < model.size(); i++) {
            list.add(model.get(i));
        }
        return list;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // React to changes in the view model (e.g., when the friends list or errors change)
        final FriendsListState state = (FriendsListState) evt.getNewValue();
        updateFriendsList(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update the JList with the latest friends data
    private void updateFriendsList(FriendsListState state) {
        DefaultListModel<String> listModel = (DefaultListModel<String>) friendsList.getModel();
        listModel.clear();
        for (String friend : state.getFriends()) {
            listModel.addElement(friend);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setFriendsListController(FriendsListController controller) {
        this.controller = controller;
    }

    public void setDbNoteDataAccessObject(DBNoteDataAccessObject dbNoteDataAccessObject) {
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
        // Reinitialize AddFriendInputBoundary if dependencies change
        if (this.addFriendOutputBoundary != null) {
            this.addFriendInputBoundary = new AddFriendInteractor(
                    dbNoteDataAccessObject,
                    addFriendOutputBoundary,
                    friendsListModelToList()
            );
        }
    }

    public void setAddFriendOutputBoundary(AddFriendOutputBoundary addFriendOutputBoundary) {
        this.addFriendOutputBoundary = addFriendOutputBoundary;
        // Reinitialize AddFriendInputBoundary if dependencies change
        if (this.dbNoteDataAccessObject != null) {
            this.addFriendInputBoundary = new AddFriendInteractor(
                    dbNoteDataAccessObject,
                    addFriendOutputBoundary,
                    friendsListModelToList()
            );
        }
    }
}