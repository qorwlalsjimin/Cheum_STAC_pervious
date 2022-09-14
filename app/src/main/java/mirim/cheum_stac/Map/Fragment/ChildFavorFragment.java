package mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import mirim.cheum_stac.R;

public class ChildFavorFragment extends Fragment {
    public static ChildFavorFragment newInstance(){
        return new ChildFavorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_favor, container, false);

        ListView list;
        list = (ListView) v.findViewById(R.id.list_favorite);
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);

        data.add("즐");
        data.add("겨");
        data.add("찾");
        data.add("기");
        adapter.notifyDataSetChanged();

        return v;
    }
}