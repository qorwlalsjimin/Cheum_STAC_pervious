package com.cheum_stac;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cheum_stac.Map.Fragment.ChildResultFragment;
import com.cheum_stac.Map.Fragment.ChildSearchFragment;
import com.cheum_stac.Map.Fragment.ParentFragment;
import com.cheum_stac.util.UserUtils;
import com.cheum_stac.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment homeFragment = new HomeFragment();
    private MapFragment mapFragment = new MapFragment();
    private FillFragment fillFragment = new FillFragment();
    private MypageFragment mypageFragment = new MypageFragment();

    //지도 검색 Fragment들
    private ParentFragment parentFragment;
    private ChildSearchFragment childSearchFragment;
    private ChildResultFragment childResultFragment;

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,homeFragment).commit(); //초기화면 HomeFragment로 지정

        BottomNavigationView bottomNavigationView = findViewById(R.id.btn_navi_menu);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.tab_home:
                        transaction.replace(R.id.frameLayout,homeFragment).commit();
                        break;
                    case R.id.tab_map:
                        transaction.replace(R.id.frameLayout,mapFragment).commit();
                        break;
                    case R.id.tab_fill:
                        transaction.replace(R.id.frameLayout,fillFragment).commit();
                        break;
                    case R.id.tab_myPage:
                        transaction.replace(R.id.frameLayout,mypageFragment).commit();
                        break;

                }
                return false;
            }
        });
        //지도 검색 Fragment들 정의
        parentFragment = new ParentFragment();
        childSearchFragment = new ChildSearchFragment();
        childResultFragment = new ChildResultFragment();

        gethash(); //키해시 값 구하는 메서드

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commit();
    }

    //FragmentListener 추상메서드 구현
    @Override
    public void onCommand(int index, String data) {
        switch (index){
            case 0: //ParentFragment =>
                childSearchFragment.displayMessage(data);
                break;
            case 1: //ChildFavorFragment, ChildSearchFragment =>
                childResultFragment.displayMessage(data);
                Log.d("값 옮기기를 추적하자 -_-", "Favor에서 받은 값으로 displayMessage를 호출하셧나요? data: "+data);
                break;
        }
    }

    //카카오맵 api를 사용하기 위한 키해시 값 구하는 메서드
    private void gethash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
                UserUtils.setHash(something.replaceAll("\n",""));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
}