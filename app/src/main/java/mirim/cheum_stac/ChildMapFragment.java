package mirim.cheum_stac;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import mirim.cheum_stac.Map.Fragment.ChildResultFragment;

public class ChildMapFragment extends Fragment {

    LinearLayout linearInfo;

    public static ChildMapFragment newInstance() {
        return new ChildMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_child_map, container, false);


        linearInfo = v.findViewById(R.id.linear_store_info);
        linearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(ChildResultFragment.newInstance());
            }
        });
        return v;
    }
}