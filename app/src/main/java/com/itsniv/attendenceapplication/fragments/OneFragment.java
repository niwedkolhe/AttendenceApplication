package com.itsniv.attendenceapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itsniv.attendenceapplication.Adapter.CustomAdapter;
import com.itsniv.attendenceapplication.R;
import com.itsniv.attendenceapplication.model.UserModel;

import java.util.ArrayList;

/**
 * Created by it's niv on 05-12-2016.
 */
public class OneFragment extends Fragment {

    private ListView vlistView;
    private ArrayList<UserModel> vmodeluserModels;

    String[] vtopic=new String[]{"Introduction","Android Architecture","Android Life cycle"};
    String[] vdate=new  String[]{"01/12/2016","02/12/2016","03/12/2016"};
    String[] vtime=new String[]{"8:00","8:05","8:06"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.onefragment,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        vlistView=(ListView)view.findViewById(R.id.list_summary);
        setData();

        CustomAdapter customAdapter =new CustomAdapter(getActivity(),vmodeluserModels);
        vlistView.setAdapter(customAdapter);
    }

    private void setData() {
        vmodeluserModels=new ArrayList<>();

        for (int i = 0;i<vdate.length; i++) {
            UserModel vModel=new UserModel();
            vModel.setVtopic(vtopic[i]);
            vModel.setVdate(vdate[i]);
            vModel.setVtime(vtime[i]);

            vmodeluserModels.add(vModel);
        }

    }
}
