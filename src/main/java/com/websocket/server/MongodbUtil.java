package com.websocket.server;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.UUID;

/**
 * Created by fccc on 2017/12/29.
 */

public class MongodbUtil {

    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/application-mongodb.xml");
    LiveInfoCountRepository repository = context.getBean(LiveInfoCountRepository.class);

    public String insert(LiveInfoCount entity) {
        repository.test();
        repository.createCollection();
        repository.insert(entity);
        System.out.println(repository.findList(0, 5));
        findByUname("fccc");
        return "success";
    }

    public String findByUname(String uname){
        List<LiveInfoCount> list = repository.findListByUname(uname);
        System.out.println(list.size());
        return "success";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/application-mongodb.xml");
        LiveInfoCountRepository repository = context.getBean(LiveInfoCountRepository.class);
        LiveInfoCount entity = new LiveInfoCount();
        entity.setUid("34324543543");
        entity.setUname("fccc");
        entity.setLiveId("324er");
        repository.insert(entity);
    }

}
