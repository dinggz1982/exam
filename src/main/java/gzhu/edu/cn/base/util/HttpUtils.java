package gzhu.edu.cn.base.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-09-30 00:07
 */
public class HttpUtils {

    //get请求网络数据
    public static String httpGet(String http) {
        try {
            //设置URL
            URL url = new URL(http);
            //获取HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置为get请求
            connection.setRequestMethod("GET");
            //设置连接主机超时时间
            connection.setConnectTimeout(5000);
            //设置从主机读取数据超时
            connection.setReadTimeout(5000);

            int code = connection.getResponseCode();
            if (code == 200) {
                //得到数据
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                //拼接数据
                StringBuilder builder = new StringBuilder();
                String s = "";
                while ((s = reader.readLine()) != null) {
                    builder.append(s);
                }
                //返回数据
                return builder.toString();
            }
            //关闭连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}