package com.websocket.server.service;

import com.websocket.server.bean.LiveInfoCount;

import java.util.List;

/**
 * Created by fccc on 2018/1/2.
 */
public interface LiveInfoCountService {
    List<LiveInfoCount> findList(int skip, int limit);
    List<LiveInfoCount> findListByUname(String uname);
    LiveInfoCount findOne(String id);
    void insert(LiveInfoCount entity);
}
