
system = require('system');
address = system.args[1];//获得命令行第二个参数 接下来会用到

var mwp_h5_token;
var mwp_h5_token_enc;
var mgjuuid;
var actorLiveInfoServiceUrl;

var webpage = require('webpage').create();

webpage.onResourceRequested = function(requestData, networkRequest) {
    // console.log("requestData.url:"+requestData.url);
    ///////////////////////////====拦截这个接口  取出url 方便后面模拟请求====//////////////////////////////
    if(requestData.url.indexOf("http://api.mogujie.com/h5/mwp.mogulive.actorLiveInfoService/3") > -1){
        actorLiveInfoServiceUrl =  requestData.url;
    }
};
window.setTimeout(function () {
    var content3 = webpage.evaluate(function () {
        var element = document.getElementsByClassName("J_livePlay video-play")[0].click();
    });
    var cookies = webpage.cookies;
    for(var i in cookies) {
        if(cookies[i].name == '__mgjuuid'){
            mgjuuid = cookies[i].value;
        }
        if(cookies[i].name == '_mwp_h5_token_enc'){
            mwp_h5_token_enc = cookies[i].value;
        }
        if(cookies[i].name == '_mwp_h5_token'){
            mwp_h5_token = cookies[i].value;
        }
    }
    console.log("actorLiveInfoServiceUrl:"+actorLiveInfoServiceUrl);
    ///////////////////////////  以下逻辑 不必用js 写  可以 得到cookies 和 url 后用你java或者node 后台模拟请求    ////////////////////////////////////////////////

    // 一共三个接口 来获取 消息

    // ===============================
    // 还有个接口 获取在线人数和商品列表
    // http://api.mogujie.com/h5/mwp.mogulive.h5HeartBeatService/1/?mw-appkey=100028&mw-t=1512122366102&mw-ttid=NMMain%40mgj_h5_1.0&mw-sign=7f976141e884c93d048ad09988ebdc26&data=%7B%22liveId%22%3A%221c9vlc%22%2C%22favCount%22%3A0%2C%22appPlat%22%3A%22h5%22%7D&callback=mwpCb7&_=1512122366103

    // headers里加
    // Referer: http://h5.mogujie.com/mgj-live/share.html?actorId=13ecqc2
    // Cookie: _mwp_h5_token_enc=8e8d2b0e9f5e6b81544af1041b628665; _mwp_h5_token=f64b834f70476c7ccac98698cdd6e02e_1511949851541; __mgjuuid=4079077a-9fae-407f-8969-81fed4923413
    // 每10秒请求一次
    // "onlineUserCount":23413
    // ===============================


    //////////////==【接口一】==///////////////=====获取房间信息====取出GroupId用于下个接口==/////////////////////////////////
    var xhr = new XMLHttpRequest();
    xhr.webSecutiry =false;
    xhr.localToRemoteUrlAccessEnabled =true;
    xhr.open("GET", actorLiveInfoServiceUrl, true);
    xhr.setRequestHeader("Cookie", "_mwp_h5_token_enc="+mwp_h5_token_enc+";_mwp_h5_token="+mwp_h5_token+";__mgjuuid="+mgjuuid+";");
    xhr.setRequestHeader("Referer","http://h5.mogujie.com/mgj-live/share.html?actorId=13ecqc2");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var jsonstring=xhr.responseText.substring(7,xhr.responseText.length-1) ;
                var json = JSON.parse(jsonstring);
                //  console.log('json:'+jsonstring);
                if(json.ret == "SUCCESS"){
                    if(json.data.liveStatus == 1){
                        console.log('[top]直播中');
                        var GroupId = json.data.groupId;
                        ////////==【接口二】==/////////////用于获取key 获取消息时用/////////////////////////////

                        var xhr2 = new XMLHttpRequest();
                        xhr2.webSecutiry =false;
                        xhr2.localToRemoteUrlAccessEnabled =true;
                        xhr2.open("POST", "https://webim.tim.qq.com/v4/group_open_http_svc/apply_join_group?websdkappid=537048168&v=1.7.0&tinyid=144115209675763445&a2=702d49a3dcd98a4639ada755065b26a194e0cc2be87801b7c4177d9b84a0fb3668fef4ef296b2ef6b0d3dc77887038e9281ee76bbdd182f802efdc2b87fa4b623214476a7bf2ebf7&contenttype=json&sdkappid=1400006909&accounttype=3352&apn=1&reqtime=1511947449", true);

                        xhr2.onreadystatechange = function () {
                            if (xhr2.readyState == 4) {
                                if (xhr2.status == 200) {
                                    // console.log('json:'+xhr2.responseText);
                                    var json = JSON.parse(xhr2.responseText);
                                    if(json.ActionStatus == 'OK'){
                                        var LongPollingKey = json.LongPollingKey;

                                        //////==【接口三】==///////////////获取聊天消息///////消息类型 详见 蘑菇街资料文件//////////////////////
                                        var StartSeq = 1;  //根据接口返回的值 替换
                                        //////每秒请求一次  蘑菇街他们也是这个搞的////////
                                        window.setInterval(function () {

                                            var xhr3 = new XMLHttpRequest();
                                            xhr3.webSecutiry =false;
                                            xhr3.localToRemoteUrlAccessEnabled =true;
                                            xhr3.open("POST", "https://webim.tim.qq.com/v1/group_open_long_polling_http_noauth_svc/get_msg?websdkappid=537048168&v=1.7.0&tinyid=144115209675763445&a2=82ad95a5d6d7b4855c48ac69a660f04fb34d5ccfbcd7c263eaa389a7084101f7287a06d40b4dbc15ad806a29d1cd8db9349a2d42816dc9e63a1c6e32331772aacf7a434f09eb4777&contenttype=json&sdkappid=1400006909&accounttype=3352&apn=1&reqtime=1511778022", true);

                                            xhr3.onreadystatechange = function () {
                                                if (xhr3.readyState == 4) {
                                                    if (xhr3.status == 200) {
                                                        // console.log('json:'+xhr3.responseText);
                                                        var json = JSON.parse(xhr3.responseText);
                                                        if(json.ActionStatus == 'OK'){
                                                            var NextSeq = json.NextSeq;
                                                            StartSeq = NextSeq;
                                                            //////////////////////////////////////////////////

                                                            // console.log('[top]'+xhr3.responseText);
                                                            var RspMsgList = json.RspMsgList;
                                                            for(var i=0;i<RspMsgList.length;i++){
                                                                var list = RspMsgList[i];
                                                                if(list.Event == '3'){
                                                                    var MsgBody = list.MsgBody;
                                                                    for(var j=0;j<MsgBody.length;j++){
                                                                        var list2 = MsgBody[j];
                                                                        var MsgContent = list2.MsgContent;
                                                                        var Data = MsgContent.Data;
                                                                        console.log('[top]'+Data+"\n");
                                                                        return '[top]'+Data+"\n";
                                                                    }


                                                                }
                                                            }
                                                            //////////////////////////////////////////////////


                                                        }else{
                                                            console.log('[top]'+xhr3.responseText);
                                                            console.log('[top]消息接口异常重试');
                                                            phantom.exit();
                                                        }
                                                    }
                                                }
                                            };
                                            var  postbody = '{"StartSeq": '+ StartSeq+',"HoldTime": 90,"Key": "'+ LongPollingKey+'"}';
                                            // console.log('[top]postbody:'+postbody);
                                            xhr3.send(postbody);





                                        },1000);


                                        //////////////////////////////////////////////////




                                    }
                                }
                            }
                        };

                        xhr2.send('{"GroupId": "'+GroupId+'"}');



                        //////////////////////////////////////////////////
                    }else{
                        console.log('[top]直播结束');
                        phantom.exit();
                    }
                }else{
                    console.log('[top]直播结束');
                    phantom.exit();
                }

            } else {
                console.log('[top]异常重试');
                phantom.exit();
            }
        }
    };

    xhr.send(null);
}, 5000);

webpage.open("http://h5.mogujie.com/mgj-live/share.html?actorId="+address, function (status) {
    console.log('status:' + status);
    if(status == 'success'){

    }
});
