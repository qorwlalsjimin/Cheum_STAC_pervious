package com.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cheum_stac.FragmentListener;
import com.cheum_stac.MainActivity;
import com.cheum_stac.Map.ListView.ListViewAdapter;
import com.cheum_stac.Map.ListView.ListViewItem;
import com.cheum_stac.Map.StoreDatas;
import com.cheum_stac.R;

public class ChildFavorFragment extends Fragment {
    public static ChildFavorFragment newInstance(){
        return new ChildFavorFragment();
    }

    ListView listData; //즐겨찾기 보이는 리스트뷰
    int storeId=7; //ChildResultFragment로 값이 안 넘어가서 넣은 임의의 초기값
    FragmentListener fragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_favor, container, false);

        //리스트뷰
        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;
        listData = (ListView) v.findViewById(R.id.list_favorite);
        listData.setAdapter(adapter);

        //리스트뷰에 데이터 추가
        StoreDatas storeDatas = new StoreDatas(); //추후 파이어베이스 DB로 교체
        for(int i = 0; i<storeDatas.dataCnt; i++)
            adapter.addItem(storeDatas.storeText[i][1], storeDatas.storeText[i][2], i);

        //즐겨찾기 가게 클릭시
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem obj = (ListViewItem) parent.getAdapter().getItem(position);
                storeId = obj.getId();

                fragmentListener.onCommand(1, Integer.toString(storeId));
                Log.d("값 옮기기를 추적하자 -_-", "onCommand로 값을 보냇어요! storeId: "+storeId);

                ((MainActivity)getActivity()).replaceFragment(ChildMapFragment.newInstance());
            }
        });


        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FragmentListener) fragmentListener = (FragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(fragmentListener != null) fragmentListener = null;
    }
}