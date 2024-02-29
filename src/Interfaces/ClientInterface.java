package Interfaces;

public interface ClientInterface {

    /**
     * method to get the selected option menu from the user
     */
    int getOptionMenu();

    /**
     * method to show the menu to the user
     * the options are: 1. Send a Unicast message, 2. Send a Broadcast message, 3, view log of messages, 4. Exit
     */
    void showMenu();

    void showReceivedMessage(MessageInterface messageInterface);

    void showSentMessage(String message);

    void showLog(MessageInterface[] log);

    void showError(String text);

    MessageInterface getUniCastMessage();

    MessageInterface getBroadCastMessage();
}
