package com.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cheum_stac.FragmentListener;
import com.cheum_stac.MainActivity;
import com.cheum_stac.Map.ListView.ListViewAdapter;
import com.cheum_stac.Map.ListView.ListViewItem;
import com.cheum_stac.Map.StoreDatas;
import com.cheum_stac.R;

public class ChildSearchFragment extends Fragment {

    FragmentListener fragmentListener;
    static String strSearch = "초기값"; //검색어 string 타입
    EditText editSearch; //검색어 editText 타입
    ListView listData; //검색 결과 보이는 리스트뷰
    boolean isSearch = true;
    int storeId;

    public static ChildSearchFragment newInstance() {
        return new ChildSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_search, container, false);

        //가게 검색 필터링 기능(listview) 구현 시작
        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;

        listData = (ListView) v.findViewById(R.id.list_search);
        listData.setAdapter(adapter);

        //리스트뷰에 데이터 추가
        StoreDatas storeDatas = new StoreDatas();
        for(int i = 0; i<storeDatas.dataCnt; i++)
            adapter.addItem(storeDatas.storeText[i][1], storeDatas.storeText[i][2], i);

        //ParentFragment에서 검색어값 받아오기
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.fragment_parent, v.findViewById(R.id.editTextFilter));
        editSearch = layout.findViewById(R.id.editTextFilter);
        editSearch.setText(strSearch);

        //ListView 필터링
        if(isSearch) ((ListViewAdapter) listData.getAdapter()).getFilter().filter(strSearch);

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem obj = (ListViewItem) parent.getAdapter().getItem(position);
                storeId = obj.getId();
                Log.d("값 옮기기를 추적하자 -_-", "서치프래그먼트 storeId: "+storeId);

                fragmentListener.onCommand(1, Integer.toString(storeId));
                ((MainActivity)getActivity()).replaceFragment(ChildMapFragment.newInstance());
            }
        });

        return v;
    }

    //리스트뷰 필터링을 시작할 것인지의 boolean값 ParentFragment에서 받아오기
    public void isSearch(boolean b){
        isSearch = b;
    }

    //ParentFragment에서 값 받아오기
    public void displayMessage(String message){
        strSearch = message;
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