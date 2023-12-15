package com.rio.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private static int id = 0;

    private String groupId;
    private String groupName;
    private List<String> usernames;
    private List<Message> messages;

    public Group(String groupName) {
        this.groupName = groupName;
        this.groupId = String.valueOf(getUniqueId());
        this.usernames = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public List<Message> getMessages() {
        return messages;
    }

    private int getUniqueId() {
        return id++;
    }

}
