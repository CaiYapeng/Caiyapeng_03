package jiyun.com.text;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//
public class Main2Activity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private ListView list;
    private SwipeRefreshLayout swipe;
    private List<Bean.ResultBean.ListBean> data = new ArrayList<>();
    private MyAdapter adapter;
    private int position = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        volleyData.getInstance(this).sendPost("http://v.juhe.cn/weixin/query?key=a332c6b34264527ac142764eaed9364d&pno=" + (position++), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<Bean>() {
                }.getType();
                Bean o = gson.fromJson(response, type);
                data.addAll(o.getResult().getList());
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.toString());
            }
        });
    }

    private void initView() {
        list = (ListView) findViewById(R.id.list);
        adapter = new MyAdapter(Main2Activity.this, data);
        list.setAdapter(adapter);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list.setOnScrollListener(this);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipe.isRefreshing()) {
                    position=1;
                    data.clear();
                    initData();
                    swipe.setRefreshing(false);
                }
            }
        });
    }

    private boolean is = false;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (lastitem == adapter.getCount() - 1 & i == SCROLL_STATE_IDLE && !is) {
            initData();
        }
    }

    private int lastitem = -1;

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        lastitem = i1 + i - 1;
    }
}
