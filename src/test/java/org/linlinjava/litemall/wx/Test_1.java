package org.linlinjava.litemall.wx;

import com.google.gson.Gson;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.io.File;
import java.util.HashMap;

public class Test_1 {
    public static void main(String[] args) {
        String secret = "442590526bc23c6e3e184c1185afc18a";
        String appid = "wx92af6526fb742ee6";
        String param = "grant_type=client_credential&appid="+ appid + "&secret=" + secret;
        HttpRequest req = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?" + param);
        HttpResponse response = req.send();
        Gson gson = new Gson();
        HashMap map = gson.fromJson(response.body(), HashMap.class);
        String access_token = map.get("access_token").toString();
        System.out.println(access_token);

        HttpRequest post = HttpRequest.post("https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + access_token);
        post.contentType("multipart/form-data");
        post.charset("utf-8");
        post.form("media",new File("D:/1.jpg"));
        HttpResponse response2 = post.send();
        HashMap hashMap = gson.fromJson(response2.body(), HashMap.class);
        String errmsg = hashMap.get("errmsg").toString();
        System.out.println(errmsg);


    }
}
