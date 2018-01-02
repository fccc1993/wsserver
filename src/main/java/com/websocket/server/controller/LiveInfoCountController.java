package com.websocket.server.controller;

import com.websocket.server.redis.RedisNamespace;
import com.websocket.server.bean.LiveInfoCount;
import com.websocket.server.service.LiveInfoCountService;
import com.websocket.server.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fccc on 2017/12/29.
 */
@RestController
@CrossOrigin
@RequestMapping("/liveinfo")
public class LiveInfoCountController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(LiveInfoCountController.class);

    @Autowired
    private LiveInfoCountService liveInfoCountService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        System.out.println("--------------------------------start----------------------");
        LiveInfoCount entity = new LiveInfoCount();
        entity.setUid("qwerty123456");
        entity.setUname("fccc");
        entity.setLiveId("asdfgh");
        System.out.println("----------------------------------redis--------------------------");
        redisUtil.set(RedisNamespace.MOGUJIE_ANCHOR_CRAWLED.getKey(), "qwerty123456", 120L);
        System.out.println("-------------------------------mongodb-------------------------");
        liveInfoCountService.insert(entity);
        List<LiveInfoCount> list = liveInfoCountService.findListByUname("fccc");
        System.out.println(list.get(0).toString());
        System.out.println("------------------"+list.size());
    }


    @RequestMapping(value = "/haha", method = RequestMethod.GET)
    public String haha(){
        redisUtil.leftPushList(RedisNamespace.MOGUJIE_ANCHOR_CRAWLED.getKey(), "lkjiop", false);
        System.out.println("-----------------------------haha---------------");
        return "haha";
    }
}
