package com.example.administrator.huhu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.huhu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyselfFragment extends Fragment {


    public MyselfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return LayoutInflater.from(getActivity()).inflate(R.layout.layout_me_frg,container,false);
    }


}
