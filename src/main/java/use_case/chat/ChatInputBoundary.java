package use_case.chat;

/**
 * Input boundary for chat use cases.
 */
public interface ChatInputBoundary {

    /**
     * Execute the save message use case.
     * @param message message we want to send
     * @param sender of message
     * @param reciever of message
     */
    void executeSaveMessage(String message, String sender, String reciever);

    /**
     * Execute the load messages use case.
     * @param sender current sender in chat
     * @param reciever current reciever in chat
     */
    void executeLoadMessages(String sender, String reciever);
}
