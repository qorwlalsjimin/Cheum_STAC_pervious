package com.cheum_stac.Map.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.cheum_stac.util.FirebaseUtils;
import com.cheum_stac.util.UserUtils;
import com.cheum_stac.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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
    ImageButton imgbtnStar;
    ViewGroup mapViewContainer;
    static int storeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_child_result, container, false);
        Log.d("storeId를 추적합니다. 쭈고 -_-", "onCreateView 실행되자마자 storeId: "+storeId);

        imgbtnStar = v.findViewById(R.id.imgbtn_star);
        Log.d("파이어베이스를 추적하자 -_-", "가게 아이디"+Integer.toString(storeId));

        //파이어베이스 실시간 DB 연동
        Log.d("파이어베이스를 추적하자 -_-", "데이터베이스레퍼런스 연결 직전!");
        DatabaseReference reference = FirebaseUtils.getUserReference(); //reference는 user 속성을 받음
        Log.d("파이어베이스를 추적하자 -_-", "reference에 user 속성 받기");

        //위에서 갖고온 store 주소값의 데이터를 읽어서 버튼 상태값 바꿔주기
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//dataSnapshot : user

                Log.d("storeId를 추적합니다. 쭈고 -_-", "reference에 user 연동 storeId: "+storeId);
                Log.d("파이어베이스를 추적하자 -_-", "onDataChange 실행");
                String path = UserUtils.getHash() + "/favorite/" + Integer.toString(storeId);
                Boolean favorite = false;
                Log.d("파이어베이스를 추적하자 -_-", "경로 지정하고 favorite: "+favorite);
                if (dataSnapshot.child(path).exists()){
                    favorite = dataSnapshot.child(path).getValue(Boolean.class);
                    Log.d("파이어베이스를 추적하자 -_-", "favorite값을 디비에서 가져왓어요! favorite: "+favorite);
                    imgbtnStar.setBackgroundResource(getBGR(favorite));
                    Log.d("파이어베이스를 추적하자 -_-", "favorite값에 따라 달라지는 이미지 파일 getBGR(favorite): "+getBGR(favorite)+" favorite: "+favorite);
                    Log.d("storeId를 추적합니다. 쭈고 -_-", "reference에 user 연동 storeId: "+storeId);
                }
                reference.child(path).setValue(favorite);

                if(favorite) imgbtnStar.setTag("star");
                else imgbtnStar.setTag("star_empty");

                Log.d("파이어베이스를 추적하자 -_-", "DB에 true/false값 set 1 favorite: "+favorite);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("파이어베이스를 추적하자 -_-", "어머낫!!! 오류.");
                //에러 처리
            }
        });


        String path = UserUtils.getHash() + "/favorite/" + Integer.toString(storeId);
        imgbtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("파이어베이스를 추적하자 -_-", "이미지 버튼 온클릭******************************************");
                Boolean favorite = !getBGRFavorite(imgbtnStar.getTag().toString());
                reference.child(path).setValue(favorite);
                Log.d("파이어베이스를 추적하자 -_-", "DB에 true/flase값 set 2 favorite: "+favorite);
            }
        });
        
        //지도 화면에 보이게 함
//        MapView mapView = new MapView(getActivity());
//        mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
//        mapViewContainer.addView(mapView);

        //상세정보 내리는 이미지 버튼
//        imgbtnDown = v.findViewById(R.id.imgbtn_down);
//        imgbtnDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Fragment창 닫는 코드로 추후 수정(FragmentManager)
//                ((MainActivity)getActivity()).replaceFragment(ChildMapFragment.newInstance());
//                mapViewContainer.removeAllViews();
//            }
//        });


        Log.d("storeId를 추적합니다. 쭈고 -_-", "reference에 user 연동 storeId: "+storeId);

        return v;
    }

    public Boolean getBGRFavorite(String d) {
        return d.equals("star") ? true : false;
    }

    public int getBGR(Boolean favorite) {
        return favorite ? R.drawable.star : R.drawable.star_empty;
    }

    @Override
    public void onPause() {
        super.onPause();
        //카카오맵 지도 2~3개 이상 열리지 않게 조치
        if(mapViewContainer != null) mapViewContainer.removeAllViews();
    }

    //즐겨찾기 화면, 검색된 화면에서 값 받아오기
    public void displayMessage(String data){
        storeId = Integer.parseInt(data);
        Log.d("값 옮기기를 추적하자 -_-", "가게 아이디를 정상적으로 받았나요? storeId: "+storeId);
    }

}