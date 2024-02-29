package Model;

import Interfaces.MessageInterface;

import java.io.Serializable;

/**
 * Represents a message with its sender, receiver, and date/time information.
 */
public class Message implements MessageInterface, Serializable {
    
    private static final long serialVersionUID = 1L;
    private String message;
    private Node sender;
    private Node receiver;
    private String dateTime;

    /**
     * Constructs a new Message object with the specified message, sender, receiver, and date/time.
     *
     * @param message  the content of the message
     * @param sender   the sender of the message
     * @param receiver the receiver of the message
     * @param dateTime the date/time when the message was sent
     */
    public Message(String message, Node sender, Node receiver, String dateTime) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = dateTime;
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
    public Node getSender() {
        return this.sender;
    }

    /**
     * Returns the receiver of the message.
     *
     * @return the receiver of the message
     */
    @Override
    public Node getReceiver() {
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
    public void setSender(Node sender) {
        this.sender = sender;
    }

    /**
     * Sets the receiver of the message.
     *
     * @param receiver the new receiver of the message
     */
    @Override
    public void setReceiver(Node receiver) {
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
}
