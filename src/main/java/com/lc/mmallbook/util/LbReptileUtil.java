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


}
