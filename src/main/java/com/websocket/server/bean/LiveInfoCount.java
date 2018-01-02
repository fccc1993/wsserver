package com.websocket.server.bean;

import com.websocket.server.bean.OnlineUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fccc on 2017/12/26.
 */
@Document(collection = "liveInfoCount")
public class LiveInfoCount {
    @Id
    private String id;
    private int pageviewCount = 0;
    private int activeUserCount = 0;
    private int fansAwakeCount = 0;
    private int fansIncreaseCount = 0;
    private int addCartCount = 0;
    private int addCartPeopleCount = 0;
    private int buyGoodsCount = 0;
    private int buyGoodPeopleCount = 0;
    private int danmakuCount = 0;
    private int giftCount = 0;
    private int likeCount = 0;
    private int MaxOnlineUserCount = 0;
    private int favorCount = 0;
    @Indexed
    private String uid;
    @Indexed
    private String uname;
    @Indexed
    private String liveId;
    private String intro;
    private int startTime;
    private int endTime;
    private int duration;
    private List<OnlineUser> onlineUserCountList = new ArrayList<OnlineUser>();

    @Override
    public String toString() {
        return "LiveInfoCount{" +
                "pageviewCount=" + pageviewCount +
                ", activeUserCount=" + activeUserCount +
                ", fansAwakeCount=" + fansAwakeCount +
                ", fansIncreaseCount=" + fansIncreaseCount +
                ", addCartCount=" + addCartCount +
                ", addCartPeopleCount=" + addCartPeopleCount +
                ", buyGoodsCount=" + buyGoodsCount +
                ", buyGoodPeopleCount=" + buyGoodPeopleCount +
                ", danmakuCount=" + danmakuCount +
                ", giftCount=" + giftCount +
                ", likeCount=" + likeCount +
                ", MaxOnlineUserCount=" + MaxOnlineUserCount +
                ", favorCount=" + favorCount +
                ", uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", liveId='" + liveId + '\'' +
                ", intro='" + intro + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", onlineUserCountList=" + onlineUserCountList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getPageviewCount() {
        return pageviewCount;
    }

    public void setPageviewCount(int pageviewCount) {
        this.pageviewCount = pageviewCount;
    }

    public int getActiveUserCount() {
        return activeUserCount;
    }

    public void setActiveUserCount(int activeUserCount) {
        this.activeUserCount = activeUserCount;
    }

    public int getFansAwakeCount() {
        return fansAwakeCount;
    }

    public void setFansAwakeCount(int fansAwakeCount) {
        this.fansAwakeCount = fansAwakeCount;
    }

    public int getFansIncreaseCount() {
        return fansIncreaseCount;
    }

    public void setFansIncreaseCount(int fansIncreaseCount) {
        this.fansIncreaseCount = fansIncreaseCount;
    }

    public int getAddCartCount() {
        return addCartCount;
    }

    public void setAddCartCount(int addCartCount) {
        this.addCartCount = addCartCount;
    }

    public int getAddCartPeopleCount() {
        return addCartPeopleCount;
    }

    public void setAddCartPeopleCount(int addCartPeopleCount) {
        this.addCartPeopleCount = addCartPeopleCount;
    }

    public int getBuyGoodsCount() {
        return buyGoodsCount;
    }

    public void setBuyGoodsCount(int buyGoodsCount) {
        this.buyGoodsCount = buyGoodsCount;
    }

    public int getBuyGoodPeopleCount() {
        return buyGoodPeopleCount;
    }

    public void setBuyGoodPeopleCount(int buyGoodPeopleCount) {
        this.buyGoodPeopleCount = buyGoodPeopleCount;
    }

    public int getDanmakuCount() {
        return danmakuCount;
    }

    public void setDanmakuCount(int danmakuCount) {
        this.danmakuCount = danmakuCount;
    }

    public int getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(int giftCount) {
        this.giftCount = giftCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getMaxOnlineUserCount() {
        return MaxOnlineUserCount;
    }

    public void setMaxOnlineUserCount(int maxOnlineUserCount) {
        MaxOnlineUserCount = maxOnlineUserCount;
    }

    public int getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(int favorCount) {
        this.favorCount = favorCount;
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

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public List<OnlineUser> getOnlineUserCountList() {
        return onlineUserCountList;
    }

    public void setOnlineUserCountList(List<OnlineUser> onlineUserCountList) {
        this.onlineUserCountList = onlineUserCountList;
    }
}
