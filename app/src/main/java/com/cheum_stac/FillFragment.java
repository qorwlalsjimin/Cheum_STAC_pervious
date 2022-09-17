package com.cheum_stac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cheum_stac.R;

;

public class FillFragment extends Fragment {


    public FillFragment() {
        // Required empty public constructor
    }

    public static FillFragment newInstance() {
        FillFragment fragment = new FillFragment();

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
        return inflater.inflate(R.layout.fragment_fill, container, false);
    }
}