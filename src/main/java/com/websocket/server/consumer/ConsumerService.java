package com.websocket.server.consumer;


import javax.jms.Destination;

/**
 * Created by fccc on 2018/1/3.
 */

public interface ConsumerService {
    /**
     * 接受消息
     */
    void receive(Destination destination);
}
