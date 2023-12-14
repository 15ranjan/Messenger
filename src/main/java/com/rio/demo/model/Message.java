package com.rio.demo.model;

import java.sql.Timestamp;

public class Message {
    private String from;
    private String to;
    private String groupId;
    private String text;
    private String filelink;
    private Timestamp sentAt;
    private Timestamp deliveredAt;
    private Timestamp deletedAt;
    private boolean isDeleted;
}
