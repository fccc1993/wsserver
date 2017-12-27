system = require('system');
address = system.args[1];//获得命令行第二个参数 接下来会用到

var mwp_h5_token;
var mwp_h5_token_enc;
var mgjuuid;
var userinfoUrl;

var webpage = require('webpage').create();

webpage.onResourceRequested = function (requestData, networkRequest) {
    // console.log("requestData.url:"+requestData.url);
    ///////////////////////////====拦截这个接口  取出url 方便后面模拟请求====//////////////////////////////
    if (requestData.url.indexOf("http://newpreapi.mogujie.com/h5/mwp.member.userinfo/1.5/") > -1) {
        userinfoUrl = requestData.url;
    }

};
window.setTimeout(function () {
    var cookies = webpage.cookies;
    for (var i in cookies) {
        if (cookies[i].name == '__mgjuuid') {
            mgjuuid = cookies[i].value;
        }
        if (cookies[i].name == '_mwp_h5_token_enc') {
            mwp_h5_token_enc = cookies[i].value;
        }
        if (cookies[i].name == '_mwp_h5_token') {
            mwp_h5_token = cookies[i].value;
        }
    }

    var xhr = new XMLHttpRequest();
    xhr.webSecutiry = false;
    xhr.localToRemoteUrlAccessEnabled = true;
    xhr.open("GET", userinfoUrl, true);
    xhr.setRequestHeader("Cookie", "_mwp_h5_token_enc=" + mwp_h5_token_enc + ";_mwp_h5_token=" + mwp_h5_token + ";__mgjuuid=" + mgjuuid + ";");
    xhr.setRequestHeader("Referer", "http://h5.mogujie.com/profile/index.html?uid=12rtjt4");

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var jsonstring = xhr.responseText.substring(7, xhr.responseText.length - 1);
                var json = JSON.parse(jsonstring);

                if (json.ret == "SUCCESS") {
                    var introTrim = json.data.intro.trim().replace(/\n/g," ");
                    // console.log("fanslike:{\"cFans\":" + json.data.cFans + ",\"clikes\":" + json.data.clikes + ",\"uid\":" + json.data.uid + ",\"uname\":" + json.data.uname + "}\n");
                    console.log("homeInfo:{\"uid\":" + json.data.uid + ",\"uname\":" + json.data.uname +
                        ",\"avatar\":" + "\"" + json.data.avatar + "\"" +
                        ",\"bg\":" + "\"" + json.data.bg + "\"" +
                        ",\"cFans\":" + json.data.cFans +
                        ",\"intro\":" +"\""+ introTrim +"\""+
                        ",\"clikes\":" + json.data.clikes + "}");
                    phantom.exit();
                }
            }
        }

    };

    xhr.send(null);
}, 5000);

webpage.open("http://h5.mogujie.com/profile/index.html?uid=" + address, function (status) {
    console.log('status:' + status);
    if (status !== 'success') {
        console.log('Unable to Post!');
    } else {
        // console.log(webpage.content);
    }
});
