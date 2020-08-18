package com.lc.mmallbook.util;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
/**
 * @author lhm
 * @date 2020/8/7 22:17
 */
public class ReadPropertyUtil {

    public static Properties loadProps(String filePath) {
        Properties pros = new Properties();
        InputStreamReader in = new InputStreamReader(ReadPropertyUtil.class.getClassLoader().getResourceAsStream(filePath));
        try {
            pros.load(in);
        } catch (IOException e) {
            System.out.println("配置文件加载失败！" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pros;


    }
}