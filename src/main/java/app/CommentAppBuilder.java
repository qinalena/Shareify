//package app;
//
//import javax.swing.*;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.note.NoteController;
//import interface_adapter.note.NotePresenter;
//import interface_adapter.note.NoteViewModel;
//import use_case.note.NoteDataAccessInterface;
//import use_case.note.NoteInteractor;
//import use_case.note.NoteOutputBoundary;
//import view.NoteView;
//import view.ViewManager;
//import interface_adapter.comment.CommentViewModel;
//import view.CommentView;
//import interface_adapter.comment.CommentViewModel;
//
//import java.awt.*;
//
///**
// * Builder for the Note Application.
// */
//public class CommentAppBuilder {
//    public static final int HEIGHT = 300;
//    public static final int WIDTH = 400;
//    private NoteDataAccessInterface noteDAO;
//    private NoteViewModel noteViewModel = new NoteViewModel();
//    private CommentViewModel commentViewModel = new CommentViewModel();
//    private CommentView commentView;
//    private NoteView noteView;
//    private NoteInteractor noteInteractor;
//
//    private final JPanel cardPanel = new JPanel();
//    private final CardLayout cardLayout = new CardLayout();
//
//    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
//    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
//
//    /**
//     * Sets the NoteDAO to be used in this application.
//     * @param noteDataAccess the DAO to use
//     * @return this builder
//     */
//    public CommentAppBuilder addNoteDAO(NoteDataAccessInterface noteDataAccess) {
//        noteDAO = noteDataAccess;
//        return this;
//    }
//
//    /**
//     * Creates the objects for the Note Use Case and connects the NoteView to its
//     * controller.
//     * <p>This method must be called after addNoteView!</p>
//     * @return this builder
//     * @throws RuntimeException if this method is called before addNoteView
//     */
//    public CommentAppBuilder addNoteUseCase() {
//        final NoteOutputBoundary noteOutputBoundary = new NotePresenter(noteViewModel, viewManagerModel);
//        noteInteractor = new NoteInteractor(
//                noteDAO, noteOutputBoundary);
//
//        final NoteController controller = new NoteController(noteInteractor);
//        if (noteView == null) {
//            throw new RuntimeException("addNoteView must be called before addNoteUseCase");
//        }
//        noteView.setNoteController(controller);
//        return this;
//    }
//
//    /**
//     * Creates the NoteView and underlying NoteViewModel.
//     * @return this builder
//     */
//    public CommentAppBuilder addNoteView() {
//        noteViewModel = new NoteViewModel();
//        noteView = new NoteView(noteViewModel);
//        return this;
//    }
//
//    public CommentAppBuilder addCommentView() {
//        commentViewModel = new CommentViewModel();
//        commentView = new CommentView(commentViewModel);
//        return this;
//    }
//
//    /**
//     * Builds the application.
//     * @return the JFrame for the application
//     */
//    public JFrame build() {
//        final JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setTitle("Shareify");
//        frame.setSize(WIDTH, HEIGHT);
//
//        frame.add(commentView);
//
//        // refresh so that the note will be visible when we start the program
//
//        return frame;
//
//    }
//}