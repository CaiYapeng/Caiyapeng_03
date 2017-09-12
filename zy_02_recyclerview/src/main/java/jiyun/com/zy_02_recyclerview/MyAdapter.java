package jiyun.com.zy_02_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lenovo on 2017/9/4.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Bean> data;

    public MyAdapter(MainActivity mainActivity, ArrayList<Bean> data) {
        this.context = mainActivity;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType() == 0) {
            return 0;
        } else if (data.get(position).getType() == 1) {
            return 1;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View inflate = View.inflate(context, R.layout.list_item, null);
                holder = new ViewHolder1(inflate);
                break;
            case 1:
                View inflate1 = View.inflate(context, R.layout.list_item2, null);
                holder = new ViewHolder2(inflate1);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                Glide.with(context).load(data.get(position).getImgs().get(0)).into(
                        ((ViewHolder1) holder).item_img1);
                Glide.with(context).load(data.get(position).getImgs().get(1)).into(
                        ((ViewHolder1) holder).item_img2);
                Glide.with(context).load(data.get(position).getImgs().get(2)).into(
                        ((ViewHolder1) holder).item_img3);
                break;
            case 1:
                Glide.with(context).load(data.get(position).getImgs().get(new Random().nextInt(3))).into(
                        ((ViewHolder2) holder).item2_img1);
                ((ViewHolder2) holder).item2_title.setText(data.get(position).getContent());
                break;
        }


    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public ImageView item_img1;
        public ImageView item_img2;
        public ImageView item_img3;

        public ViewHolder1(View itemView) {
            super(itemView);
            item_img1 = (ImageView) itemView.findViewById(R.id.item_img1);
            item_img2 = (ImageView) itemView.findViewById(R.id.item_img2);
            item_img3 = (ImageView) itemView.findViewById(R.id.item_img3);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView item2_title;
        public ImageView item2_img1;


        public ViewHolder2(View itemView) {
            super(itemView);
            this.item2_title = (TextView) itemView.findViewById(R.id.item2_title);
            this.item2_img1 = (ImageView) itemView.findViewById(R.id.item2_img1);
        }
    }
}
