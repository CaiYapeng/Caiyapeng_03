package jiyun.com.text;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by lenovo on 2017/9/4.
 */
public class volleyData {
    private static volleyData volley;
    public static RequestQueue queue;
    private volleyData(Context context) {
        queue= Volley.newRequestQueue(context);
    }

    public static synchronized volleyData getInstance(Context context) {
        if (volley == null) {
            volley = new volleyData(context);
        }
        return volley;
    }

    public static  void sendPost(String url, Response.Listener<String> listen, Response.ErrorListener errorlisten){
        StringRequest request=new StringRequest(url,listen,errorlisten);
        queue.add(request);
    }
}
