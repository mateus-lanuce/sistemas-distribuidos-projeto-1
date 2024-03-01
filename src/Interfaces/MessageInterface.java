package Interfaces;

import Model.NodeServer;

public interface MessageInterface {
    String getMessage();
    NodeServer getSender();
    NodeServer getReceiver();
    String getDateTime();
    String getType();
    void setMessage(String message);
    void setSender(NodeServer sender);
    void setReceiver(NodeServer receiver);
    void setDateTime(String dateTime);
    void setType(String type);
}