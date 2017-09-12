package jiyun.com.cs_scroll;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by lenovo on 2017/9/4.
 */
public class VolleyData {

    private static VolleyData volley;
    public static RequestQueue queue;
    private VolleyData(Context context) {
            queue= Volley.newRequestQueue(context);
    }

    public static synchronized VolleyData getInstance(Context context) {
        if (volley == null) {
            volley = new VolleyData(context);
        }
        return volley;
    }

    public static  void sendGet(String url, Response.Listener<String> listen, Response.ErrorListener errorlisten){
        StringRequest request=new StringRequest(url,listen,errorlisten);
        queue.add(request);
    }
}
