package com.itsniv.attendenceapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itsniv.attendenceapplication.R;
import com.itsniv.attendenceapplication.model.UserModel;

import java.util.ArrayList;

/**
 * Created by it's niv on 02-12-2016.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserModel> modelArrayList;

    public CustomAdapter(Context context, ArrayList<UserModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return this.modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{

        private TextView vtopics;
        private TextView vdate;
        private TextView vtime;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        View rowview=view;

        if (view==null){

            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            viewHolder=new ViewHolder();
            rowview=inflater.inflate(R.layout.summary,parent,false);

            viewHolder.vtopics=(TextView)rowview.findViewById(R.id.textview_topics);
            viewHolder.vdate=(TextView)rowview.findViewById(R.id.textview_date);
            viewHolder.vtime=(TextView)rowview.findViewById(R.id.textview_time);

            rowview.setTag( viewHolder);
        }else {
            viewHolder=(ViewHolder) rowview.getTag();
        }
        viewHolder.vtopics.setText(this.modelArrayList.get(position).getVtopic());
        viewHolder.vdate.setText(this.modelArrayList.get(position).getVdate());
        viewHolder.vtime.setText(this.modelArrayList.get(position).getVtime());

        return rowview;
    }
}
