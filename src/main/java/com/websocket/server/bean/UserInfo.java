package com.websocket.server.bean;

/**
 * Created by fccc on 2017/12/27.
 */

public class UserInfo {
    private String uid;
    private String uname;
    private String roomId;
    private String intro;
    private int cFans;
    private int clikes;
    private String avatar;
    private String bg;

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", roomId='" + roomId + '\'' +
                ", intro='" + intro + '\'' +
                ", cFans=" + cFans +
                ", clikes=" + clikes +
                ", avatar='" + avatar + '\'' +
                ", bg='" + bg + '\'' +
                '}';
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getcFans() {
        return cFans;
    }

    public void setcFans(int cFans) {
        this.cFans = cFans;
    }

    public int getClikes() {
        return clikes;
    }

    public void setClikes(int clikes) {
        this.clikes = clikes;
    }
}
