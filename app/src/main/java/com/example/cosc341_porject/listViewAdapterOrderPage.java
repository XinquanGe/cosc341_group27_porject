package com.example.cosc341_porject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class listViewAdapterOrderPage extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    ArrayList<order> orders = new ArrayList<order>();
    listViewAdapterOrderPage(Context context, ArrayList<order> orders){
        this.context=context;
        this.orders=orders;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return orders.size();
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
        public TextView textView1, textView2, textView3, textView4;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        viewHolder holder = null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.layout_orderlist_view,null);
            holder = new viewHolder();

            holder.textView1 = view.findViewById(R.id.tv_list1);
            holder.textView2 = view.findViewById(R.id.tv_list2);
            holder.textView3 = view.findViewById(R.id.tv_list3);
            holder.textView4 = view.findViewById(R.id.tv_list4);

            view.setTag(holder);
        }else {
            holder = (viewHolder) view.getTag();
        }
        holder.textView1.setText("Order "+(i+1));
        StringBuilder orderThings= new StringBuilder();
        for (int j=0;j<orders.get(i).getAn_order().size();j++){
            orderThings.append(orders.get(i).getAn_order().get(j).getName());
            orderThings.append("  Quantity: "+orders.get(i).getAn_order().get(j).getNum()+"\n");
        }
        holder.textView2.setText(orders.get(i).getRestaurant().getName()+"\n"+orderThings);
        holder.textView3.setText( "Price $: "+orders.get(i).getTprice());
        if(orders.get(i).isPaid()==false)
            holder.textView4.setText("Unpaid");
        else{
            holder.textView4.setText("paid");
            holder.textView4.setTextColor(Color.parseColor("#66ccff"));
        }


        return view;
    }

}
