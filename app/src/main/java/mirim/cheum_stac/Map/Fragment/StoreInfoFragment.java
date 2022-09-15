package mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mirim.cheum_stac.R;

public class StoreInfoFragment extends Fragment {


    public StoreInfoFragment() {}

    public static StoreInfoFragment newInstance() {
        return new StoreInfoFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_info, container, false);
    }
}