package jiyun.com.zy_02_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<Bean> data = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

        OKHttp.getInstance().sendGet("http://192.168.0.66:8080/URL/GaoJi.json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String s = response.body().string();
                Log.e("TAG", s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<Bean>>() {
                        }.getType();
                        ArrayList<Bean> o = gson.fromJson(s, type);
                        data.addAll(o);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        adapter = new MyAdapter(this, data);
        recycler.setAdapter(adapter);
    }
}
