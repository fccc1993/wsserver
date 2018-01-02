package com.websocket.server.controller;

import com.websocket.server.result.ResultBean;
import com.websocket.server.service.LiveInfoCountService;
import com.websocket.server.util.JSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by fccc on 2018/1/2.
 */
@RestController
@CrossOrigin
@RequestMapping("/phantomjs")
public class PhantomController {
    @Autowired
    private JSUtil jsUtil;
    @Autowired
    private LiveInfoCountService liveInfoCountService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void start(String uid) throws IOException {
        ResultBean resultBean = jsUtil.liveInfoSpider(uid);
        System.out.println(resultBean.getCode()+"-"+resultBean.getMessage()+"-"+resultBean.getData());

//        ResultBean resultBean2 = jsUtil.userInfoSpider(uid);
//        System.out.println(resultBean2.getCode()+"-"+resultBean2.getMessage()+"-"+resultBean2.getData());
    }
}
