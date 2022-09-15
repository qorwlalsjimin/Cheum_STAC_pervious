package mirim.cheum_stac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.appsearch.StorageInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;

import mirim.cheum_stac.Map.Fragment.ChildSearchFragment;
import mirim.cheum_stac.Map.Fragment.FragmentListener;
import mirim.cheum_stac.Map.Fragment.ParentFragment;
import mirim.cheum_stac.Map.Fragment.StoreInfoFragment;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment homeFragment = new HomeFragment();
    private MapFragment mapFragment = new MapFragment();
    private FillFragment fillFragment = new FillFragment();
    private MypageFragment mypageFragment = new MypageFragment();

    //지도 검색 Fragment들
    private ParentFragment parentFragment;
    private ChildSearchFragment childSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
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

        gethash(); //키해시 값 구하는 메서드

    }

    //parent 프래그먼트로 이동 : 프래그먼트간 이동에 사용
    public void onFragmentChange(String nameFrag){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, ParentFragment.newInstance()).commit();
    }

    //리스트뷰 클릭시 Fragment 이동 시도(현재 X)
    public void onInfoFragmentChange(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, StoreInfoFragment.newInstance()).commit();
    }

    //FragmentListener 추상메서드 구현
    @Override
    public void onCommand(int index, String message) {
        if(index == 0) childSearchFragment.displayMessage(message);
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
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
}