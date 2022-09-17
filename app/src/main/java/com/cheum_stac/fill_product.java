package com.cheum_stac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cheum_stac.R;

public class fill_product extends Fragment {

    public fill_product() {
        // Required empty public constructor
    }

    public static fill_product newInstance(String param1, String param2) {
        fill_product fragment = new fill_product();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fill_product, container, false);
    }
}