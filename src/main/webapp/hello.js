// a phantomjs example
var page = require('webpage').create();
var url='http://www.cnblogs.com/front-Thinking/';
phantom.outputEncoding="gbk";
//viewportSize being the actual size of the headless browser
page.viewportSize = { width: 1920, height: 1080 };
//the clipRect is the portion of the page you are taking a screenshot of
// page.clipRect = { top: 0, left: 0, width: 1920, height: 1080 };
page.open(url, function(status) {
    if ( status === "success" ) {
        console.log(page.title);
        //saved ad pdf
        page.paperSize = { format: 'A3',
            orientation: 'portrait',
            border: '1cm' };
        page.render("front-Thinking.pdf");
        //saved as img
        page.render("front-Thinking.jpg");
       // jQuery库函数
       //  page.includeJs("http://code.jquery.com/jquery-1.10.1.min.js",
       //      function() {
       //          page.evaluate(function() {
       //              $('#Header1_HeaderTitle').html('My PhantomJS');
       //          });
       //          page.render("after.png");
       //      });
    } else {
        console.log("Page failed to load.");
    }
    phantom.exit(0);
});

/*
var filePath = '/workspace/file1.js';//文件路径

//判断文件是否存在，是文件还是文件夹
if( fs.exists(filePath) && fs.isFile(filePath) ) {
    var ins = fs.open(filePath, 'r');//打开文件
    while(!ins.atEnd()) {//循环读取文件内容
        var buffer = ins.readLine();//一行行的读取
        console.log(buffer);
    }
}*/
