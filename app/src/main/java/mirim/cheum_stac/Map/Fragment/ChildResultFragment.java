package mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_result, container, false);
    }
}