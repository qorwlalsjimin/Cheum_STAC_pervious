package mirim.cheum_stac;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mirim.cheum_stac.util.FirebaseUtils;
import mirim.cheum_stac.util.UserUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        List<Button> buttonList = new ArrayList<>();
        buttonList.add(view.findViewById(R.id.store_btn_1));
        buttonList.add(view.findViewById(R.id.store_btn_2));

        /**
         *  root/{user의 hash값}/~ 을 바라보는 디비 주소값을 받아와서 그 밑에 store을 바라봄
         *  root/{user의 hash값}/store/
         * **/
        DatabaseReference reference = FirebaseUtils.getUserReference();

        //위에서 갖고온 store 주소값의 데이터를 읽어서 버튼 상태값 바꿔주기
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (Button button : buttonList) {
                    String path = UserUtils.getHash() + "/store/" + button.getTag().toString();
                    Boolean favorite = false;
                    if (dataSnapshot.child(path).exists()){
                        favorite = dataSnapshot.child(path).getValue(Boolean.class);
                        button.setTextColor(getColor(favorite));
                    }
                    reference.child(path).setValue(favorite);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Boolean favorite = !getColorFavorite(button.getCurrentTextColor());
                            button.setTextColor(getColor(favorite));
                            reference.child(path).setValue(favorite);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //에러 처리
            }
        });
        return view;
    }

    public Boolean getColorFavorite(int color) {
        return color == Color.RED;
    }

    public int getColor(Boolean favorite) {
        return favorite ? Color.RED : Color.BLUE;
    }
}