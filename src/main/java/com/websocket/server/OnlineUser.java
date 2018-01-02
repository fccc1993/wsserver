package com.websocket.server;

/**
 * Created by fccc on 2017/12/25.
 */

public class OnlineUser {
//    private int favorCount;
    private int onlineUserCount;
    private String time;

    @Override
    public String toString() {
        return "{" +
//                "favorCount=" + favorCount +
                "onlineUserCount=" + onlineUserCount +
                ", time='" + time + '\'' +
                '}';
    }

//    public int getFavorCount() {
//        return favorCount;
//    }
//
//    public void setFavorCount(int favorCount) {
//        this.favorCount = favorCount;
//    }

    public int getOnlineUserCount() {
        return onlineUserCount;
    }

    public void setOnlineUserCount(int onlineUserCount) {
        this.onlineUserCount = onlineUserCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
