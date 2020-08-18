package com.lc.mmallbook.util.http;
import java.util.HashMap;
import java.util.Map;
/**
 * @author lhm
 * @date 2020/7/10 0:00
 */
public class HttpHeader {

    private Map<String, String> params = new HashMap<String, String>();

    public HttpHeader addParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

}
