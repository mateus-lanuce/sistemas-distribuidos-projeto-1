package Interfaces;

import Model.NodeServer;

import java.util.ArrayList;

public interface ClientInterface {

    /**
     * method to get the selected option menu from the user
     */
    int getOptionMenu();

    /**
     * method to show the menu to the user
     * the options are: 1. Send a Unicast message, 2. Send a Broadcast message, 3, view log of messages, 4. Exit
     */
    MessageInterface Menu(ArrayList<String> sendMessagesLog, ArrayList<String> recivedMessagesLog);

    void showReceivedMessage(MessageInterface message);

    void showSentMessage(MessageInterface message);

    void showLog(ArrayList<String> log);

    void showError(String text);

    MessageInterface getUniCastMessage();

    MessageInterface getBroadCastMessage();
}
