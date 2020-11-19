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
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhm
 * @date 2020/7/9 22:17
 */
public class LbReptileUtil {
    public static final String KEY_ALGORITHM = "RSA";
    private static  final String URL="http://10.52.48.28/deepexi.dd.domain.transaction/admin-api/v1/domain/transaction/saleOrderInfo/oLpage?page=1&size=10&total=1&status=9&orgId=987&notTickTypeStr=0&availablePickNumNotZero=true&likeCode=GL20201028101137&productId=570&userId=10776&tenantId=gree&appId=623";

    private static  final String URL="https://ec.snssdk.com/product/lubanajaxstaticitem?id=3438007932145553597&token=ad14e5631f60a76eaf895adaaf4c1a84&page_id=&scope_type=5&b_type_new=0";

    public static String Getllcphone() throws IOException {
        HttpParamers paramers=new HttpParamers(HttpMethod.GET);
        HttpHeader header=new HttpHeader();
        header.addParam("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mbyI6eyJhY2NvdW50SWQiOiIyODg0IiwiZW50ZXJwcmlzZUNvZGUiOiJncmVlIiwidGVuYW50SWQiOiJncmVlIiwidXNlcklkIjoiMTA3NzYiLCJ1c2VybmFtZSI6Im9keCJ9LCJpYW1UeXBlIjoiY2xpZW50IiwidXNlcl9uYW1lIjoib2R4Iiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTYwMzkyMzM4MiwianRpIjoiYmZjMzM5ZTEtNGM5NC00MzNmLWI2MzUtNWJjZTY1Y2UxYzI2IiwiY2xpZW50X2lkIjoiZGVlcGV4aSIsIm1lbWJlcklkIjpudWxsfQ.VbkwS5MoNXOXk0f6s85WZa3nNz-JlVIeq_XEFMgf4io");

        String response= HttpClient.doGet(URL,paramers,header,1000,1000);
        return response;
        }



    /**
     * RSA加密（公钥）
     *
     * @param value 需要加密的值
     * @param publicKey 公钥
     * @return 已加密的值
     */
    public static String rsaEncrypt(String value, String publicKey)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptData = cipher.doFinal(value.getBytes());

        return Base64.encodeBase64String(encryptData);
    }


//
//    public static void main(String[] args) throws Exception {
//
//        String loginPublicKey= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWqmlnUmee2iILSz0HR7Z3dfvk1YqLR+sOb7+ZKsd4fXmh+ruwSFQjGLmySv2ZEs8gLNDqml84+AkH9ZLlAEoMCWT/MPiOwoQxr9uVDYcQUkxh1AsC1fEVD89EZpsZHGmEhw3/sRCeA3ZDhxVElpqi1RzWAKMT066BH/F0/9qZrQIDAQAB";
//        String password =rsaEncrypt("abcd4321",loginPublicKey);
//        System.out.println(password);
//
////
////        String response=  LbReptileUtil.Getllcphone();
////        JSONObject jsonObject = JSONObject.parseObject(response);
////        System.out.println(response);
////        JSONObject payload = JSONObject.parseObject(jsonObject.getString("payload"));
////        JSONArray content = JSON.parseArray(payload.getString("content"));
////        JSONObject contentob = JSON.parseObject(content.getString(0));
////        JSONArray items = JSON.parseArray(contentob.getString("items"));
////        JSONObject itemsob = JSON.parseObject(items.getString(0));
////        System.out.println(itemsob.getString("id"));
//
//
//    }


}
