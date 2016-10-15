package edu.ustc.jikecollege;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<HashMap<String, Object>> list_data;
    //测试数据
    private String[] name = {"aa","bb","cc"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv_list);
        initData();

    }


    /**
     * 初始化数据
     */
    private void initData(){
        list_data = new ArrayList<HashMap<String, Object>>();
        for (int i =1; i <name.length; i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name[i]);
            map.put("state", "点单");
            list_data.add(map);
        }


        Adapter adapter =new Adapter(this, list_data);
        listView.setAdapter(adapter);

    }
}


/**
 * 自定义Adapter
 */
class Adapter extends BaseAdapter{
    private Context mContext;
    private static boolean LOGIN=false;
    private int currentItem = -1;
    private ArrayList<HashMap<String, Object>> list;

    public Adapter(Context mContext, ArrayList<HashMap<String, Object>> data) {
        this.mContext = mContext;
        this.list = data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView ==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            holder.setTv_name((TextView) convertView.findViewById(R.id.tv_name));
            holder.setTv_button((TextView) convertView.findViewById(R.id.tv_button));
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getTv_button().setTag(position);

        holder.getTv_name().setText(list.get(position).get("name").toString());
        holder.getTv_name().setText(list.get(position).get("state").toString());

        if (currentItem == position){
            holder.getTv_button().setBackgroundColor(000000);
        }else {
        }


        holder.getTv_button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                if(tag == currentItem){
                    currentItem = -1;
                }else {
                    currentItem = tag;
                }
                notifyDataSetChanged();
            }
        });

        return convertView;

    }
}


class ViewHolder{
    private TextView tv_name;
    private TextView tv_button;

    public ViewHolder() {
    }

    public ViewHolder(TextView tv_name, TextView tv_button) {
        this.tv_name = tv_name;
        this.tv_button = tv_button;
    }

    public TextView getTv_button() {
        return tv_button;
    }

    public void setTv_button(TextView tv_button) {
        this.tv_button = tv_button;
    }

    public TextView getTv_name() {
        return tv_name;
    }

    public void setTv_name(TextView tv_name) {
        this.tv_name = tv_name;
    }


}