package com.websocket.server;

/**
 * Created by fccc on 2017/12/25.
 */

public class MessageContent {
    private String userAvatar;
    private String userId;
    private String userName;
    private int chopHandTimes;
    private int senderMask;
    private int messageType;
    private int personCount;
    private String sendAvatar;
    private String sendId;
    private String sendName;
    private int versionCode;
    private String textContent;
    private String giftName;
    private String imageUrl;
    private int giftType;
    private boolean expensive;
    private int likeNUm;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public boolean isExpensive() {
        return expensive;
    }

    public void setExpensive(boolean expensive) {
        this.expensive = expensive;
    }

    public int getLikeNUm() {
        return likeNUm;
    }

    public void setLikeNUm(int likeNUm) {
        this.likeNUm = likeNUm;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getChopHandTimes() {
        return chopHandTimes;
    }

    public void setChopHandTimes(int chopHandTimes) {
        this.chopHandTimes = chopHandTimes;
    }

    public int getSenderMask() {
        return senderMask;
    }

    public void setSenderMask(int senderMask) {
        this.senderMask = senderMask;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
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

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
