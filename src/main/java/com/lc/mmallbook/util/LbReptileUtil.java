package com.lc.mmallbook.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lc.mmallbook.util.http.HttpClient;
import com.lc.mmallbook.util.http.HttpHeader;
import com.lc.mmallbook.util.http.HttpParamers;
import org.springframework.http.HttpMethod;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.util.Map;

/**
 * @author lhm
 * @date 2020/7/9 22:17
 */
public class LbReptileUtil {

    private static  final String URL="https://ec.snssdk.com/product/lubanajaxstaticitem?id=3438007932145553597&token=ad14e5631f60a76eaf895adaaf4c1a84&page_id=&scope_type=5&b_type_new=0";

    public static String Getllcphone() throws IOException {
        HttpParamers paramers=new HttpParamers(HttpMethod.GET);
        HttpHeader header=new HttpHeader();

        String response= HttpClient.doGet(URL,paramers,header,1000,1000);
        return response;
        }



    public static void main(String[] args) throws IOException {


        String response=  LbReptileUtil.Getllcphone();
        JSONObject jsonObject = JSONObject.parseObject(response);
        System.out.println(response);
        JSONObject data = JSONObject.parseObject(jsonObject.getString("data"));
        JSONObject freight = JSONObject.parseObject(data.getString("freight"));
       // JSONObject columns = JSONObject.parseObject(freight.getString("columns"));
        JSONArray columns = JSON.parseArray(freight.getString("columns"));
        System.out.println(columns);


    }


}
