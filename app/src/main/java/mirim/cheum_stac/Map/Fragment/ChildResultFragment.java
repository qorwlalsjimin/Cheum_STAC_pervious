package mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import net.daum.mf.map.api.MapView;

import mirim.cheum_stac.MainActivity;
import mirim.cheum_stac.R;

public class ChildResultFragment extends Fragment {


    public ChildResultFragment() {}

    public static ChildResultFragment newInstance() {
        return new ChildResultFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ImageButton imgbtnDown;
    ViewGroup mapViewContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_child_result, container, false);

        MapView mapView = new MapView(getActivity());
        mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        imgbtnDown = v.findViewById(R.id.imgbtn_down);
        imgbtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment창 닫는 코드로 추후 수정(FragmentManager)
                ((MainActivity)getActivity()).replaceFragment(ChildMapFragment.newInstance());
                mapViewContainer.removeAllViews();
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewContainer.removeAllViews();

    }
}