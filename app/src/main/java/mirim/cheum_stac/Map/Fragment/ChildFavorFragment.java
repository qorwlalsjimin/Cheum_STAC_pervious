package mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import mirim.cheum_stac.MainActivity;
import mirim.cheum_stac.Map.ListView.ListViewAdapter;
import mirim.cheum_stac.Map.ListView.ListViewItem;
import mirim.cheum_stac.Map.StoreDatas;
import mirim.cheum_stac.R;

public class ChildFavorFragment extends Fragment {
    public static ChildFavorFragment newInstance(){
        return new ChildFavorFragment();
    }

    ListView listData; //즐겨찾기 보이는 리스트뷰
    int storeId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_favor, container, false);

        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;

        listData = (ListView) v.findViewById(R.id.list_favorite);
        listData.setAdapter(adapter);

        //리스트뷰에 데이터 추가
        StoreDatas storeDatas = new StoreDatas(); //추후 파이어베이스 DB로 교체
        for(int i = 0; i<storeDatas.dataCnt; i++)
            adapter.addItem(storeDatas.storeText[i][1], storeDatas.storeText[i][2], i);

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem obj = (ListViewItem) parent.getAdapter().getItem(position);
                storeId = obj.getId();

                ((MainActivity)getActivity()).replaceFragment(ChildMapFragment.newInstance());
            }
        });


        return v;
    }
}