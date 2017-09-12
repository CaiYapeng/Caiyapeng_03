package jiyun.com.cs_scroll;

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

public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private ListView mList;
    private SwipeRefreshLayout swipe;
    private List<Bean.ResultBean.ListBean> data = new ArrayList<>();
    private MyAdapter adapter;
    private int pageNum = 1;
    private String url = "http://v.juhe.cn/weixin/query?key=a332c6b34264527ac142764eaed9364d&pno=" + (pageNum++);
    private int lastitem = -1;
    private boolean is = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.e("TAG", "进入");
        initView();
        Log.e("TAG", "进入");
        initData();
    }

    private void initData() {
        VolleyData.getInstance(HomeActivity.this).sendGet("http://v.juhe.cn/weixin/query?key=a332c6b34264527ac142764eaed9364d&pno=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "进入");
                Gson gson = new Gson();
                Type type = new TypeToken<Bean>() {
                }.getType();
                Bean o = gson.fromJson(response, type);
                Log.e("TAG", o.toString());
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
        mList = (ListView) findViewById(R.id.mList);
        adapter = new MyAdapter(this, data);
        mList.setAdapter(adapter);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mList.setOnScrollListener(HomeActivity.this);
        swipe.setOnRefreshListener(HomeActivity.this);
    }

    @Override
    public void onRefresh() {
        data.clear();

        if (swipe.isRefreshing()) {
            swipe.setRefreshing(false);
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (lastitem == adapter.getCount() - 1 && i == SCROLL_STATE_IDLE && !is) {
            initData();
        }
    }


    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        lastitem = i + i1 - 1;
    }
}
