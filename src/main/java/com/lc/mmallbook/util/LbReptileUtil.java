package com.lc.mmallbook.util;

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

    private static  final String URL="https://ec.snssdk.com/product/lubanajaxstaticitem?id=3414589641746189084&token=254ddfbb9d367ea6de2c4e5d53b66a2c&page_id=&scope_type=5&b_type_new=0";

    public static String Getllcphone() throws IOException {
        HttpParamers paramers=new HttpParamers(HttpMethod.GET);
        HttpHeader header=new HttpHeader();

        String response= HttpClient.doGet(URL,paramers,header,1000,1000);
        return response;
        }



    public static void main(String[] args) throws IOException {

        String response=  LbReptileUtil.Getllcphone();
        JSONObject jsonObject = JSONObject.parseObject(response);
        System.out.println(jsonObject.getString("st"));

    }


}
