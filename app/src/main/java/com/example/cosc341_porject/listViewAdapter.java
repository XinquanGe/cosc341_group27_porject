package com.example.cosc341_porject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class listViewAdapter  extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Restaurant> restaurants;


    listViewAdapter(Context context,ArrayList<Restaurant> restaurants){
        this.context=context;
        this.restaurants=restaurants;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {

        return restaurants.size();
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
        public TextView textView1;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        viewHolder holder = null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.layout_list_view,null);
            holder = new viewHolder();
            holder.imageView = view.findViewById(R.id.iv_list);
            holder.textView1 = view.findViewById(R.id.tv_list1);

            view.setTag(holder);
        }else {
            holder = (viewHolder) view.getTag();
        }

            holder.textView1.setText(restaurants.get(i).getName());
            holder.imageView.setImageResource(R.drawable.testpic);

        return view;
    }

}
