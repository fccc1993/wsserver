package com.websocket.server.producer;


import javax.jms.Destination;

/**
 * Created by fccc on 2018/1/3.
 */

public interface ProducerService {
    /**
     * 向指定队列发送消息
     */
    void sendMessage(Destination destination, final String msg);
    /**
     * 向默认队列发送消息
     */
    void sendMessage(final String msg);
}
