package Interfaces;

import java.net.Socket;

public interface ServerInterface extends Runnable{

    /**
     * Method to send a message to the next node.
     * 
     * @param messageInterface the message to be sent
     * @return true if the message was sent successfully, false otherwise
     */
    void sendMessage(MessageInterface messageInterface);

    /**
     * Method to verify if the message is for the current node.
     *
     * @param messageInterface the message to be checked
     * @return true if the message is for the current node, false otherwise
     */
    private boolean isMessageForMe(MessageInterface messageInterface) {
        return false;
    }

    /**
     * Method to receive a message from the previous node.
     *
     * @param messageInterface the message to be received
     * @return the received message, or null if no message is received
     */
    private MessageInterface receiveMessage(MessageInterface messageInterface) {
        return null;
    }

    /**
     * Method to start the server.
     */
    private void startServer() {
    }
}
