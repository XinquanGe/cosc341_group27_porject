package com.example.cosc341_porject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class gridViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    gridViewAdapter(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    static class viewHolder{
        public ImageView imageView;
        public TextView textView1, textView2, textView3;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        gridViewAdapter.viewHolder holder = null;
            if(view==null){
                view=layoutInflater.inflate(R.layout.layout_grid_view,null);
                holder = new gridViewAdapter.viewHolder();
                holder.imageView = view.findViewById(R.id.iv_grid);
                holder.textView1 = view.findViewById(R.id.tv_grid1);
                view.setTag(holder);
            }else {
                holder = (gridViewAdapter.viewHolder) view.getTag();
            }

                holder.textView1.setText("name");
                holder.imageView.setImageResource(R.drawable.testpic);

            return view;
    }
}
