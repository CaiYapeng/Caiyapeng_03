package jiyun.com.cs_scroll;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lenovo on 2017/9/4.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Bean.ResultBean.ListBean> data;

    public MyAdapter(HomeActivity homeActivity, List<Bean.ResultBean.ListBean> data) {
        this.context = homeActivity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size(); //null == data ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(data.get(i).getTitle());
        holder.content.setText(data.get(i).getSource());
        Glide.with(context).load(data.get(i).getFirstImg()).into(holder.img);
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    public static class ViewHolder {
        public View rootView;
        public TextView title;
        public ImageView img;
        public TextView content;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.img = (ImageView) rootView.findViewById(R.id.img);
            this.content = (TextView) rootView.findViewById(R.id.content);
        }

    }
}

