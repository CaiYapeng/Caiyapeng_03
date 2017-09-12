package jiyun.com.zy_01_listview;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lenovo on 2017/9/4.
 */
public class OKHttp {
    private static OKHttp http;
    private static OkHttpClient client;

    private OKHttp() {
        client = new OkHttpClient();
    }

    public static synchronized OKHttp getInstance() {
        if (http == null) {
            http = new OKHttp();
        }
        return http;
    }

    public static void sendGet(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
