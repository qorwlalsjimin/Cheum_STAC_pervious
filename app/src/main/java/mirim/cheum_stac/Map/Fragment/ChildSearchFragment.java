package mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import mirim.cheum_stac.Map.ListView.ListViewAdapter;
import mirim.cheum_stac.R;

public class ChildSearchFragment extends Fragment {

    static String strSearch = "초기값"; //검색어 string 타입
    EditText editSearch; //검색어 editText 타입
    ListView listData; //검색 결과 보이는 리스트뷰
    boolean isSearch = true;

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

        //가게 검색 필터링 기능(listview)
        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;

        listData = (ListView) v.findViewById(R.id.list_search);
        listData.setAdapter(adapter);

        //리스트뷰에 데이터 추가
        adapter.addItem("알맹상점 리스테이션 ", "서울 중구 한강대로 405 4층 (봉래동 2가)") ;
        adapter.addItem("시리얼에코리필스테이션 롯데마트 제타플렉스점", "서울 송파구 올림픽로 240 (잠실동)") ;
        adapter.addItem("아로마티카 제로스테이션", "서울 강남구 도산대로1길 62 1층 (신사동)") ;
        adapter.addItem("덕분애제로웨이스트샵", "서울 서초구 서운로26길 11 2층 (서초동)") ;
        adapter.addItem("순환지구", "서울 성북구 동소문로25길 8 1층 (동선동 4가)") ;

        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.fragment_parent, v.findViewById(R.id.editTextFilter));
        editSearch = layout.findViewById(R.id.editTextFilter);
        editSearch.setText(strSearch);
        Log.d("검색 원츄", "리스너 밖");
        if(isSearch) ((ListViewAdapter) listData.getAdapter()).getFilter().filter(strSearch);

        return v;
    }

    //리스트뷰 필터링을 시작할 것인지 boolean값 ParentFragment에서 받아오기
    public void isSearch(boolean b){
        isSearch = b;
    }

    //ParentFragment에서 값 받아오기
    public void displayMessage(String message){
        strSearch = message;
    }

}