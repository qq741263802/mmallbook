package com.lc.mmallbook.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.lc.mmallbook.util.http.HttpClient;
import com.lc.mmallbook.util.http.HttpHeader;
import com.lc.mmallbook.util.http.HttpParamers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.util.Map;

/**
 * @author lhm
 * @date 2020/7/9 22:17
 */
public class LbReptileUtil {

    private static  final String URL="https://ec.snssdk.com/product/lubanajaxstaticitem?id=3437247909706504805&token=73e787138ee57d43af04d846db95f688&page_id=&scope_type=5&b_type_new=0";

     public static String Getllcphone() throws IOException {
        HttpParamers paramers=new HttpParamers(HttpMethod.GET);
        HttpHeader header=new HttpHeader();

        String response= HttpClient.doGet(URL,paramers,header,1000,1000);
        return response;


        }






}
