package com.websocket.server;

/**
 * Created by fccc on 2017/12/26.
 */

public class PlaybackInfo {
    private int visitorCount;
    private int startTime;
    private int endTime;
    private int duration;
    private String liveId;
    private String roomId;
    private int cFans;
    private int clikes;
    private String uid;
    private String uname;

    @Override
    public String toString() {
        return "PlaybackInfo{" +
                "visitorCount=" + visitorCount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", liveId='" + liveId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", cFans=" + cFans +
                ", clikes=" + clikes +
                ", uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public int getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(int visitorCount) {
        this.visitorCount = visitorCount;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }
}
