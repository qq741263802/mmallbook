package com.lc.mmallbook.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ReadSwaggerUtil {


    @Test
    public static void createfile() throws IOException {

        final HashMap<String, String> getdata = getdata();

        File f = new File("");
        String path = f.getCanonicalPath();
        String packgepathname = getdata.get("title").replaceAll("-", "") + "Test";
        String packagename = "com.souche.qa.volvo.http";
        String newpackagename = "com.souche.qa.volvo.http." + packgepathname;

        File httpdatepackage = new File(path + "/src/test/java/com/souche/qa/volvo/http/" + packgepathname);
        if (!httpdatepackage.exists()) {
            httpdatepackage.mkdir();
        }
        String packgepath = httpdatepackage.getPath();

//        File mainTestDatePackage = new File(path + "/src/test/resources/testdata/" + packgepathname);
//        if (!mainTestDatePackage.exists()) {
//            mainTestDatePackage.mkdir();
//        }

        for (Map.Entry<String, String> entrySet : getdata.entrySet()) {

            String apikey = entrySet.getKey();
            String paramvalue = entrySet.getValue();

            System.out.println("paramvalue："+paramvalue);

            String demovalue = "String caseId";

            String demoParamPut = "String moduleParam;";

            String[] valueString = paramvalue.split(",");

            String param = "";

            for(int i=1; i<valueString.length;i++){
                String[] valueArray = valueString[i].split(" ");
                if (valueArray.length >1 ){
                    String paramString = valueArray[1];
                    String mapPut = "param.put(" + "\"" + paramString + "\"," + paramString + ");"+"\r\n\t\t";
                    param += mapPut;

                }}


            if (apikey != "title") {
                String[] arraysplit = apikey.split("/|\\.");
                System.out.println(arraysplit);
                String a = arraysplit[arraysplit.length - 3];
                String b = arraysplit[arraysplit.length - 2];
                String A = a.substring(0, 1).toUpperCase() + a.substring(1);
                String B = b.substring(0, 1).toUpperCase() + b.substring(1);
                String uperfilename = A.concat(B).concat("Test");
                String lowerfilename = a.concat(B).concat("Test");


                String[] arraysplit1 = apikey.split("/");
                String method = arraysplit1[0];
                String finalapi = "";
                for (int i = 1; i < arraysplit1.length; i++) {
                    finalapi += "/" + arraysplit1[i];}


                File javafile = new File(packgepath + "/" + uperfilename + ".java");
                if (!javafile.exists()) {
                    javafile.createNewFile();
                    String filepath = javafile.getPath();

                    InputStreamReader is = new InputStreamReader(new FileInputStream(path + "/src/test/java/com/souche/qa/volvo/http/ModuleTest.java"), "utf-8");
                    OutputStreamWriter ot = new OutputStreamWriter(new FileOutputStream(filepath));
                    BufferedReader br = new BufferedReader(is);
                    BufferedWriter bw = new BufferedWriter(ot);
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.contains("ModuleTest") || line.contains("moduleTest") || line.contains(demovalue) || line.contains("apipath") || line.contains(packagename) || line.contains("doget")|| line.contains(demoParamPut) ) {
                            line = line.replaceAll("ModuleTest", uperfilename);
                            line = line.replaceAll("moduleTest", lowerfilename);
                            line = line.replaceAll(demovalue, paramvalue);
                            line = line.replaceAll("apipath", finalapi);
                            line = line.replaceAll(packagename, newpackagename);
                            line = line.replaceAll("doget", "do" + method);
                            line = line.replaceAll(demoParamPut, param);

                        }

                        bw.write(line);
                        bw.newLine();

                    }

                    br.close();
                    bw.close();
                }


            }


        }


    }

    /**
     * 将swagger文档返回的api和api参数，组装成map对象
     *
     * @return
     * @throws IOException
     */


    public static HashMap<String, String> getdata() throws IOException {

        String url = "http://10.52.48.28/deepexi.dd.domain.common/v2/api-docs";
        JSONObject responseResult = doGet(url);

        JSONArray apiarray = responseResult.getJSONArray("apis");
        String title = responseResult.getJSONObject("info").getString("title");

        HashMap<String, String> finalresult = new HashMap<>();
        finalresult.put("title", title);

        for (Object oneapiarray : apiarray) {
            JSONObject apiobject = (JSONObject) JSON.toJSON(oneapiarray);
            String api = apiobject.getString("path");//获取到最外层api
//            System.out.println("oneapiarray:" + oneapiarray);
//            System.out.println("path:" + api);
            String apiurl = url + api;
//            String apiurl = url + "/api/management/order/batchDelivery.json";
            JSONObject json = doGet(apiurl);
            JSONArray apisarray = json.getJSONArray("apis");

            HashMap<String, String> result = new HashMap<>();

            for (Object oneapisarray : apisarray) {
                JSONObject apisobject = (JSONObject) JSON.toJSON(oneapisarray);
                String apis = apisobject.getString("path"); //获取每个外层api中的api

//                System.out.println(apis);

                JSONArray valuearray = apisobject.getJSONArray("operations").getJSONObject(0).getJSONArray("parameters");
//              //获取接口方法
                String method = apisobject.getJSONArray("operations").getJSONObject(0).getString("method");
                apis = method + apis;

                StringJoiner finalname = new StringJoiner(",");
//                finalname.add(method);
                finalname.add("String caseId");
                for (Object onevaluearray : valuearray) {
                    JSONObject valueobject = (JSONObject) JSON.toJSON(onevaluearray);
                    String name = valueobject.getString("name");//获取里层每个api的入参
                    String type = valueobject.getString("type");//获取里层每个api的入参的类型

                    if (type.equals("string")) {
                        type = "String";
                    } else if (type.equals("integer")) {
                        type = "int";
                    } else if (type.equals("array")) {
                        String newtype = valueobject.getJSONObject("items").getString("type");
                        if (newtype.equals("string")) {
                            newtype = "String";
                        } else if (newtype.equals("integer")) {
                            newtype = "int";
                        }
                        type = newtype + "[]";

                    }

                    if (!("body".equals(name))) {
                        String typrname = type + " " + name;
                        finalname.add(typrname);   //将一个接口中的参数合并
//                    System.out.println(name);
                    }


                }
//                System.out.println(finalname.toString());
                result.put(apis, finalname.toString());//将外层接口下的每个接口和参数以map输出

            }
            finalresult.putAll(result);


        }
        System.out.println(finalresult);
        return finalresult;


    }

    /**
     * 生成http请求
     *
     * @param apiurl
     * @return
     * @throws IOException
     */

    public static JSONObject doGet(String apiurl) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiurl);
       // httpGet.setHeader("_security_token_inc", TokenUtil.LoginToken());
        HttpResponse response = httpclient.execute(httpGet);
        String getentity = EntityUtils.toString(response.getEntity());
        JSONObject responseResult = JSONObject.parseObject(getentity);
        return responseResult;

    }

    public static void main(String[] args) throws IOException {
      //  String url = "http://10.52.48.28/deepexi.dd.domain.common/v2/api-docs";
       System.out.println(getdata());
    }

}
