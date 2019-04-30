package com.dayuan.atm.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

    //发送请求、响应
    public static String get(String url) {
        try {
            //创建HttpClient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。
            HttpGet httpGet = new HttpGet(url);
            //调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse
            CloseableHttpResponse response = httpclient.execute(httpGet);

            //System.out.println(response.getStatusLine());

            //调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容
            HttpEntity entity = response.getEntity();
            //转换成String
            return EntityUtils.toString(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
