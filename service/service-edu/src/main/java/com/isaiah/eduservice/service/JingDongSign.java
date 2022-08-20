package com.isaiah.eduservice.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: IsaiahLu
 * @date: 2022/8/20 16:27
 */
public interface JingDongSign {
    JSONObject getSign(JSONArray content);
}
