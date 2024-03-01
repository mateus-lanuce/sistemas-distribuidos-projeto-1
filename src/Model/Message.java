package Model;

import Interfaces.MessageInterface;

import java.io.Serializable;

/**
 * Represents a message with its sender, receiver, and date/time information.
 */
public class Message implements MessageInterface, Serializable {
    
    private static final long serialVersionUID = 1L;
    private String message;
    private NodeServer sender;
    private NodeServer receiver;
    private String dateTime;
    private String type;
    private int controlBroadcast = 0;

    /**
     * Constructs a new Message object with the specified message, sender, receiver, and date/time.
     *
     * @param message  the content of the message
     * @param sender   the sender of the message
     * @param receiver the receiver of the message
     * @param dateTime the date/time when the message was sent
     */
    public Message(String message, NodeServer sender, NodeServer receiver, String dateTime, String type) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = dateTime;
        this.type = type;
    }

    /**
     * Returns the content of the message.
     *
     * @return the message content
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the sender of the message.
     *
     * @return the sender of the message
     */
    @Override
    public NodeServer getSender() {
        return this.sender;
    }

    /**
     * Returns the receiver of the message.
     *
     * @return the receiver of the message
     */
    @Override
    public NodeServer getReceiver() {
        return this.receiver;
    }

    /**
     * Returns the date/time when the message was sent.
     *
     * @return the date/time of the message
     */
    @Override
    public String getDateTime() {
        return this.dateTime;
    }

    /**
     * Sets the content of the message.
     *
     * @param message the new message content
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets the sender of the message.
     *
     * @param sender the new sender of the message
     */
    @Override
    public void setSender(NodeServer sender) {
        this.sender = sender;
    }

    /**
     * Sets the receiver of the message.
     *
     * @param receiver the new receiver of the message
     */
    @Override
    public void setReceiver(NodeServer receiver) {
        this.receiver = receiver;
    }

    /**
     * Sets the date/time when the message was sent.
     *
     * @param dateTime the new date/time of the message
     */
    @Override
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getControlBroadcast() {
        return controlBroadcast;
    }

    public void setControlBroadcast(int controlBroadcast) {
        this.controlBroadcast = controlBroadcast;
    }

    public void updateControlBroadcast() {
        this.controlBroadcast++;
    }
    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", dateTime='" + dateTime + '\'' +
                ", type='" + type + '\'' +
                ", controlBroadcast=" + controlBroadcast +
                '}';
    }
}
