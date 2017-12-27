package com.websocket.server;

/**
 * Created by fccc on 2017/12/25.
 */

public class Message {
    private int messageType;
    private String messageId;
    private String messageContent;
    private String sendAvatar;
    private String sendId;
    private String sendName;
    private String groupId;
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSendAvatar() {
        return sendAvatar;
    }

    public void setSendAvatar(String sendAvatar) {
        this.sendAvatar = sendAvatar;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
