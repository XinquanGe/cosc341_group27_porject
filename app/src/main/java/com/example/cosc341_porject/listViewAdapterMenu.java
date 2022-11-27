package com.example.cosc341_porject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listViewAdapterMenu extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Dish> menu;


    private PricesListener pricesListener;
    listViewAdapterMenu(Context context,ArrayList<Dish> menu){
        this.context=context;
        this.menu=menu;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int i) {
        return menu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder holder = null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.layout_menu_view,null);
            holder = new viewHolder();
            holder.imageView = view.findViewById(R.id.iv_list);
            holder.textView1 = view.findViewById(R.id.tv_dishName);
            holder.textView2 = view.findViewById(R.id.tv_describe);
            holder.textView3 = view.findViewById(R.id.tv_dishNum);
            holder.textView4 = view.findViewById(R.id.tv_price);
            holder.menuButton1 = view.findViewById(R.id.bt_menu1);
            holder.menuButton2 = view.findViewById(R.id.bt_menu2);

            holder.menuButton1.setOnClickListener(holder.minusClickListener);
            holder.menuButton2.setOnClickListener(holder.addClickListener);

            view.setTag(holder);
        }else {
            holder = (viewHolder) view.getTag();
        }
            Dish d = menu.get(i);
            holder.dish=d;
            holder.textView4.setText("Price: $"+d.getPrice());

            holder.textView1.setText(d.getName());
            holder.textView2.setText("des:");
            holder.textView3.setText(String.valueOf(String.valueOf(d.getNum())));

            holder.imageView.setImageResource(R.drawable.testpic);

        return view;
    }

    class viewHolder{
        public ImageView imageView;
        public TextView textView1, textView2, textView3,textView4;
        public Button menuButton1, menuButton2;
        MinusClickListener minusClickListener;
        AddClickListener addClickListener;
        Dish dish;

        public viewHolder(){
            addClickListener = new AddClickListener();
            minusClickListener = new MinusClickListener();
        }
        class AddClickListener implements View.OnClickListener{

            @Override
            public void onClick(View view) {
                if(dish==null)
                    return;
                dish.setNum(dish.getNum()+1);
                Log.d("disnum","disnum: "+dish.getNum());
                pricesListener.totalPrices();
                notifyDataSetChanged();
            }
        }

        class MinusClickListener implements View.OnClickListener{

            @Override
            public void onClick(View view) {
                if(dish==null)
                    return;

                int number=dish.getNum();
                if(number>0) {
                    dish.setNum(number - 1);
                    Log.d("disnum","disnum: "+dish.getNum());
                    pricesListener.totalPrices();
                }else
                    return ;
                notifyDataSetChanged();
            }
        }
    }

    public void setPricesListener(PricesListener pricesListener){
        this.pricesListener=pricesListener;
    }
    public interface PricesListener{
        void totalPrices();
    }
}