package com.rio.demo.model;

import java.sql.Timestamp;

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

    public Message() {
        this.messageId = String.valueOf(getUniqueId());
    }

    private long getUniqueId() {
        return id++;
    }

}
