package com.websocket.server;


import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by fccc on 2017/12/7.
 */

public class GzipEncoder {
//    public static String compress(String str) throws IOException {
//        if (str == null || str.length() == 0) {
//            return str;
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        GZIPOutputStream gzip = new GZIPOutputStream(out);
//        gzip.write(str.getBytes());
//        gzip.close();
//        return out.toString("utf8");
//    }

    // 解压缩
//    public static String uncompress(String str) throws IOException {
//        if (str == null || str.length() == 0) {
//            return str;
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("iso-8859-1"));
//        GZIPInputStream gunzip = new GZIPInputStream(in);
//        byte[] buffer = new byte[256];
//        int n;
//        while ((n = gunzip.read(buffer)) >= 0) {
//            out.write(buffer, 0, n);
//        }
//// toString()使用平台默认编码，也可以显式的指定如toString("GBK")
//        return out.toString();
//    }

    public static void main(String[] args) throws IOException {
//        URL realUrl = new URL("http://www.sojson.com/");
//// 打开和URL之间的连接
//        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
//// 发送POST请求必须设置如下两行
//        conn.setRequestMethod("GET");// 提交模式
//        conn.setDoInput(true);
//        conn.setDoOutput(false);
//        conn.setUseCaches(false);
//        conn.setRequestProperty("Connection", "close");
//        conn.setConnectTimeout(3000);  //设置连接主机超时（单位：毫秒）
//        conn.setReadTimeout(2000);     //设置从主机读取数据超时（单位：毫秒）
//        InputStream stream = new GZIPInputStream(conn.getInputStream());
//        String str = IOUtils.toString(stream,"utf-8");
//        System.out.println(str);
    }

}
