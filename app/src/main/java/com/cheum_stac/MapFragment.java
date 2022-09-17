package com.cheum_stac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cheum_stac.Map.Fragment.ParentFragment;
import com.cheum_stac.R;

import net.daum.mf.map.api.MapView;

public class MapFragment extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public MapFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        MapView mapView = new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        EditText editSearch = v.findViewById(R.id.editTextFilter);
        editSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ((MainActivity)getActivity()).replaceFragment(ParentFragment.newInstance());
            }
        });
        editSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(ParentFragment.newInstance());
            }
        });
        return v;
    }
}