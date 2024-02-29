package Interfaces;

import Model.Node;

public interface MessageInterface {
    String getMessage();
    Node getSender();
    Node getReceiver();
    String getDateTime();
    void setMessage(String message);
    void setSender(Node sender);
    void setReceiver(Node receiver);
    void setDateTime(String dateTime);
}