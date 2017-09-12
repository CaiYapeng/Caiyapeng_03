package jiyun.com.zy_01_listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

/**
 * Created by lenovo on 2017/9/4.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Bean> data;

    public MyAdapter(MainActivity mainActivity, List<Bean> data) {
        this.context = mainActivity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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

        switch (data.get(i).getType()) {
            case 0:
                ViewHolder holder = null;
                if (view == null) {
                    view = View.inflate(context, R.layout.list_item, null);
                    holder = new ViewHolder(view);
                    view.setTag(holder);
                } else {
                    holder = (ViewHolder) view.getTag();
                }
                Glide.with(context).load(data.get(i).getImgs().get(0)).into(holder.item_img1);
                Glide.with(context).load(data.get(i).getImgs().get(1)).into(holder.item_img2);
                Glide.with(context).load(data.get(i).getImgs().get(2)).into(holder.item_img3);
                break;
            case 1:
                ViewHolder2 holder2 = null;
                if (view == null) {
                    view = View.inflate(context, R.layout.list_item2, null);
                    holder2 = new ViewHolder2(view);
                    view.setTag(holder2);
                } else {
                    holder2 = (ViewHolder2) view.getTag();
                }
                holder2.item2_title.setText(data.get(i).getTitle());
                Glide.with(context).load(data.get(i).getImgs().get(new Random().nextInt(3))).into(holder2.item2_img1);
                break;
        }
        return view;
    }


    public static class ViewHolder {
        public View rootView;
        public ImageView item_img1;
        public ImageView item_img2;
        public ImageView item_img3;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_img1 = (ImageView) rootView.findViewById(R.id.item_img1);
            this.item_img2 = (ImageView) rootView.findViewById(R.id.item_img2);
            this.item_img3 = (ImageView) rootView.findViewById(R.id.item_img3);
        }

    }

    public static class ViewHolder2 {
        public View rootView;
        public TextView item2_title;
        public ImageView item2_img1;

        public ViewHolder2(View rootView) {
            this.rootView = rootView;
            this.item2_title = (TextView) rootView.findViewById(R.id.item2_title);
            this.item2_img1 = (ImageView) rootView.findViewById(R.id.item2_img1);
        }

    }
}
