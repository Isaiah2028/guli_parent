package com.isaiah.eduservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.isaiah.eduservice.service.JingDongSign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: IsaiahLu
 * @date: 2022/8/20 16:29
 */
@Service
@Slf4j
public class JingDongSignImpl implements JingDongSign {


    @Value("${jingdong.user.secret}")
    private String secret;
    @Value("${jingdong.user.host}")
    private String host;
    @Value("${jingdong.user.LOP-DN}")
    private String LOP_DN;
    @Value("${jingdong.user.HMac-username}")
    private String HMac_username;
    @Autowired
    private RestTemplate restTemplate;

    public JingDongSignImpl() {
    }

    @Override
    public JSONObject getSign(com.alibaba.fastjson.JSONArray content) {
        Map<String, String> map = this.getSignCode(content);
        com.alibaba.fastjson.JSONObject jsonObject = this.getWholeProcessByOrder(content, map);
        return jsonObject;
    }

    public com.alibaba.fastjson.JSONObject getWholeProcessByOrder(com.alibaba.fastjson.JSONArray msg, Map<String, String> map) {
        String Authorization = "hmac username=\"" + this.HMac_username + "\",algorithm=\"hmac-sha1\",headers=\"X-Date content-md5\",signature=\"" + (String)map.get("signature") + "\"";
        System.out.println("-----------------------map-------------------");
        System.out.println(map);
        System.out.println("-----------------------map-------------------");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Authorization", Authorization);
        httpHeaders.set("content-md5", (String)map.get("content_md5"));
        httpHeaders.set("X-Date", (String)map.get("X_Date"));
        httpHeaders.set("LOP-DN", this.LOP_DN);
        log.info("----------------JD请求参数---------------------{}", msg);
        HttpEntity httpEntity = new HttpEntity(msg, httpHeaders);
        ResponseEntity<String> exchange = this.restTemplate.exchange(this.host + "/chint/getWholeProcessByOrder", HttpMethod.POST, httpEntity, String.class, new Object[0]);
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject((String)exchange.getBody());
        log.info("-----------------JD返回物流信息------------------{}", jsonObject);
        Integer code = (Integer)jsonObject.get("code");
        return 200 == code ? jsonObject : null;
    }

    public Map<String, String> getSignCode(com.alibaba.fastjson.JSONArray msg) {
        String X_Date = getServerTime();
        String content = msg.toString();
        String content_md5 = MD5("[" + content + "]");
        String txt = "X-Date: " + X_Date + "\ncontent-md5: " + content_md5;
        byte[] digest = new byte[0];

        try {
            digest = HmacSHA1Encrypt(txt, this.secret);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        String signature = Base64.getEncoder().encodeToString(digest);
        Map<String, String> map = new HashMap();
        map.put("signature", signature);
        map.put("content_md5", content_md5);
        map.put("X_Date", X_Date);
        return map;
    }

    private static String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }

    private static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes("utf-8");
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("utf-8");
        byte[] digest = mac.doFinal(text);
        return digest;
    }

    private static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString(array[i] & 255 | 256).substring(1, 3));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException var5) {
            return null;
        }
    }



}