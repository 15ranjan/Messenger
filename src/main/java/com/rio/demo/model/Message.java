package com.rio.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private static long id = 0;
    private String messageId;
    private String from;
    private String to;
    private String groupId;
    private String text;
    private String filelink;
    private Timestamp sentAt;
    private Timestamp deliveredAt;
    private Timestamp deletedAt;
    private boolean isDeleted;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public Timestamp getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Timestamp deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Message() {
        this.messageId = String.valueOf(getUniqueId());
    }

    private Message(Message message){
        this.messageId = message.getMessageId();
        this.from = message.getFrom();
        this.to = message.getTo();
        this.text = message.getText();
        this.sentAt = message.getSentAt();
        this.deliveredAt = message.getDeliveredAt();
    }

    private long getUniqueId() {
        return id++;
    }

    public Message clone(){
        Message message = new Message(this);
        return message;
    }
}
