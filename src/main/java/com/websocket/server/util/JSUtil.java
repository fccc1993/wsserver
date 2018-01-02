package com.websocket.server.util;

import com.websocket.server.result.ResultBean;
import com.websocket.server.bean.*;
import com.websocket.server.service.LiveInfoCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fccc on 2017/12/22.
 */

@Component
public class JSUtil {
    @Autowired
    private LiveInfoCountService liveInfoCountService;

    public ResultBean liveInfoSpider(String uid) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        //phantomjs 和codes.js的路径之间有个空格 本代码只是测试用的绝对路径
        Process liveProcess = runtime.exec("D:\\devSoft\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe --web-security=no D:\\devWorkspace\\IdeaProjects\\wsserver\\src\\main\\webapp\\infoq.js " + uid);
        InputStream liveis = liveProcess.getInputStream();
        BufferedReader livebr = new BufferedReader(new InputStreamReader(liveis));
        StringBuffer livebuffer = new StringBuffer();

        LiveInfoCount liveInfoCount = new LiveInfoCount();
        //各项参数统计初始化
        int pageviewCount = liveInfoCount.getPageviewCount();
        int addCartCount = liveInfoCount.getAddCartCount();
        int buyGoodsCount = liveInfoCount.getBuyGoodsCount();
        int danmakuCount = liveInfoCount.getDanmakuCount();
        int giftCount = liveInfoCount.getGiftCount();
        int likeCount = liveInfoCount.getLikeCount();
        //统计活跃人数，唤醒粉丝数，加购人数，下单人数
        Set<String> activeUserCountSet = new HashSet<String>();
        Set<String> fansAwakeCountCountSet = new HashSet<String>();
        Set<String> fansIncreaseCountSet = new HashSet<String>();
        Set<String> addCartCountSet = new HashSet<String>();
        Set<String> buyGoodsCountSet = new HashSet<String>();

        String livetmp = "";
        while ((livetmp = livebr.readLine()) != null) {
            livebuffer.append(livetmp);
            String livetmpstr = livebuffer.toString();
//            System.out.println(livetmpstr);

            if (livetmpstr.startsWith("liveStatus") && !"liveStatus == 1".equals(livetmpstr)) {
                return ResultBean.successOf("[" + uid + "]" + "该主播尚未开播");
            }
            if (livetmpstr.startsWith("[top]{\"ActionStatus\":\"FAIL\",")) {
                return ResultBean.failureOf("[" + uid + "]" + "该主播消息接口异常");
            }

            //直播间消息处理
            if (livetmpstr.startsWith("message")) {
                Message message = JsonUtil.jsonToBean(livetmpstr.substring(8, livetmpstr.length()), Message.class);
                MessageContent messageContent = JsonUtil.jsonToBean(message.getMessageContent(), MessageContent.class);
                System.out.println("messageType:" + message.getMessageType() + ",sendId:" + message.getSendId() + ",senderMask:" + messageContent.getSenderMask() + ",sendName:" + message.getSendName());

                int messageType = message.getMessageType();
                //统计活跃人数
                if (message.getMessageType() != 31) {
                    activeUserCountSet.add(message.getSendId());
                    liveInfoCount.setActiveUserCount(activeUserCountSet.size());
                }
                switch (messageType) {

                    //进入直播间，统计唤醒粉丝数
                    case 31:
                        liveInfoCount.setPageviewCount(++pageviewCount);
                        // 1-助理 4-粉丝 5-粉丝+助理 0-游客
                        int senderMask = messageContent.getSenderMask();
                        if (senderMask == 4 || senderMask == 5 || senderMask == 6) {
                            fansAwakeCountCountSet.add(message.getSendId());
                            liveInfoCount.setFansAwakeCount(fansAwakeCountCountSet.size());
                        }
                        if (senderMask == 6) {
                            System.out.println(livetmpstr);
                        }
                        break;

                    //新增粉丝数
                    case 52:
                        //1-截图 2-关注 3-分享
                        if (messageContent.getType() == 2) {
                            fansIncreaseCountSet.add(message.getSendId());
                            liveInfoCount.setFansIncreaseCount(fansIncreaseCountSet.size());
                        }
                        System.out.println(livetmpstr);
                        break;

                    //添加购物车，统计添加购物车人数
                    case 54:
                        liveInfoCount.setAddCartCount(++addCartCount);
                        addCartCountSet.add(message.getSendId());
                        liveInfoCount.setAddCartPeopleCount(addCartCountSet.size());
                        System.out.println(livetmpstr);
                        break;

                        //购买商品，统计购买商品人数
                    case 55:
                        liveInfoCount.setBuyGoodsCount(++buyGoodsCount);
                        buyGoodsCountSet.add(message.getSendId());
                        liveInfoCount.setBuyGoodPeopleCount(buyGoodsCountSet.size());
                        System.out.println(livetmpstr);
                        break;

                        //礼物
                    case 12:
                        liveInfoCount.setGiftCount(++giftCount);
                        System.out.println(livetmpstr);
                        break;

                        //弹幕
                    case 1:
                        liveInfoCount.setDanmakuCount(++danmakuCount);
                        break;

                        //点赞
                    case 21:
                        liveInfoCount.setLikeCount(++likeCount);
                        break;
                }
            }

//                //统计活跃人数
//                if (message.getMessageType() != 31) {
//                    activeUserCountSet.add(message.getSendId());
//                    liveInfoCount.setActiveUserCount(activeUserCountSet.size());
//                }
//
//                //进入直播间，统计唤醒粉丝数
//                if (message.getMessageType() == 31) {
//                    liveInfoCount.setPageviewCount(++pageviewCount);
//                    // 1-助理 4-粉丝 5-粉丝+助理 0-游客
//                    int senderMask = messageContent.getSenderMask();
//                    if (senderMask == 4 || senderMask == 5) {
//                        fansAwakeCountCountSet.add(message.getSendId());
//                        liveInfoCount.setFansAwakeCount(fansAwakeCountCountSet.size());
//                    }
//                    if (senderMask == 6) {
//                        System.out.println(livetmpstr);
//                    }
//
//                }
//                //新增粉丝数
//                if (message.getMessageType() == 52) {
//                    //1-截图 2-关注 3-分享
//                    if (messageContent.getType() == 2) {
//                        fansIncreaseCountSet.add(message.getSendId());
//                        liveInfoCount.setFansIncreaseCount(fansIncreaseCountSet.size());
//                    }
//                    System.out.println(livetmpstr);
//                }
//                //添加购物车，统计添加购物车人数
//                if (message.getMessageType() == 54) {
//                    liveInfoCount.setAddCartCount(++addCartCount);
//                    addCartCountSet.add(message.getSendId());
//                    liveInfoCount.setAddCartPeopleCount(addCartCountSet.size());
//                }
//                //购买商品，统计购买商品人数
//                if (message.getMessageType() == 55) {
//                    liveInfoCount.setBuyGoodsCount(++buyGoodsCount);
//                    buyGoodsCountSet.add(message.getSendId());
//                    liveInfoCount.setBuyGoodPeopleCount(buyGoodsCountSet.size());
//                }
//                //弹幕
//                if (message.getMessageType() == 1) {
//                    liveInfoCount.setDanmakuCount(++danmakuCount);
//                }
//                //礼物
//                if (message.getMessageType() == 12) {
//                    liveInfoCount.setGiftCount(++giftCount);
//                    System.out.println(livetmpstr);
//                }
//                //点赞
//                if (message.getMessageType() == 21) {
//                    liveInfoCount.setLikeCount(++likeCount);
//                }
//                if (message.getMessageType() == 56 || message.getMessageType() == 57 || message.getMessageType() == 54) {
//                    System.out.println(livetmpstr);
//                }


            //在线人数记录
            if (livetmpstr.startsWith("onlineUser")) {
                OnlineUser onlineUser = JsonUtil.jsonToBean(livetmpstr.substring(11, livetmpstr.length()), OnlineUser.class);
                if (onlineUser.getOnlineUserCount() > liveInfoCount.getMaxOnlineUserCount()) {
                    liveInfoCount.setMaxOnlineUserCount(onlineUser.getOnlineUserCount());
                }
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                onlineUser.setTime(sdf.format(date));
                liveInfoCount.getOnlineUserCountList().add(onlineUser);
                System.out.println(liveInfoCount.toString());
//                liveInfoCount.setFavorCount(onlineUser.getFavorCount());
                liveInfoCountService.insert(liveInfoCount);
            }

            //直播结束数据统计
            if (livetmpstr.startsWith("playback")) {
                PlaybackInfo playbackInfo = JsonUtil.jsonToBean(livetmpstr.substring(9, livetmpstr.length()), PlaybackInfo.class);
                System.out.println(playbackInfo);
            }

            //主播信息和直播id
            if (livetmpstr.startsWith("actorInfo")) {
                LiveInfoCount actorLiveInfo = JsonUtil.jsonToBean(livetmpstr.substring(10, livetmpstr.length()), LiveInfoCount.class);
                liveInfoCount.setUid(actorLiveInfo.getUid());
                liveInfoCount.setUname(actorLiveInfo.getUname());
                liveInfoCount.setLiveId(actorLiveInfo.getLiveId());
                liveInfoCount.setIntro(actorLiveInfo.getIntro());
            }
            livebuffer.delete(0, livebuffer.toString().length());
        }
        System.out.println(liveInfoCount.toString());
        return ResultBean.successOf("[" + uid + "]" + "该主播直播结束", liveInfoCount);
    }


    public ResultBean userInfoSpider(String uid) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process endProcess = runtime.exec("D:\\devSoft\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe --web-security=no D:\\devWorkspace\\IdeaProjects\\wsserver\\src\\main\\webapp\\info.js " + uid);
        InputStream endis = endProcess.getInputStream();
        BufferedReader endbr = new BufferedReader(new InputStreamReader(endis));
        StringBuffer endbuffer = new StringBuffer();
        String endtmp = "";

        UserInfo userInfo = new UserInfo();
        while ((endtmp = endbr.readLine()) != null) {
            endbuffer.append(endtmp);
            String endtmpstr = endbuffer.toString();
            System.out.println(endtmpstr);

            //获取主播主页信息
            if (endtmpstr.startsWith("homeInfo")) {
                userInfo = JsonUtil.jsonToBean(endtmpstr.substring(9, endtmpstr.length()), UserInfo.class);
                //将64x64头像缩略图转化为400x400原图
                String avatar = userInfo.getAvatar().replace("_64x64.jpg", "");
                userInfo.setAvatar(avatar);

                System.out.println(userInfo.toString());
            }
            endbuffer.delete(0, endbuffer.toString().length());
        }
        return ResultBean.successOf("[" + uid + "]" + "该主播信息抓取成功", userInfo);
    }
}
