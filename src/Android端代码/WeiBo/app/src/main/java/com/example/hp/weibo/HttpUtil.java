package com.example.hp.weibo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.RequestBody;


/**
 * 安卓于服务器连接
 */
public class HttpUtil {
    /**
     *
     * @param address 网址
     * @param requestBody 要传递的数据
     * @param callback 回调函数
     */
    public static void getByOkHttp(final String address, final RequestBody requestBody,
                                   final okhttp3.Callback callback) {
        //10.13.188.222
        //172.20.10.6
        //192.168.43.82
        ///服务器IP
        final String WebIP = "http://172.20.10.8:8080/weibo/";
        ///开辟线程，进行网络连接
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /// 声明客户端
                    OkHttpClient client = new OkHttpClient();

                    /// 声明请求
                    Request request = new Request.Builder()
                            .url(WebIP + address)
                            .post(requestBody)
                            .build();
                    /// 客户端发起请求
                    client.newCall(request).enqueue(callback);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获取位图
     * @param url 网址
     * @return Bitmap位图
     */
    public static Bitmap getURLimage(final String url) {
        final String WebIP = "http://172.20.10.8:8080/weibo/";
        Bitmap bmp = null;
        try {
            URL myurl = new URL(WebIP + url);
            /// 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            ///获得图片的数据流
            InputStream is = conn.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
