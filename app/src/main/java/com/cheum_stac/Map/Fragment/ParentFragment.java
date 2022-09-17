package com.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cheum_stac.FragmentListener;
import com.cheum_stac.R;

public class ParentFragment extends Fragment implements View.OnClickListener {

    EditText editSearch;
    Toolbar toolSearch;
    ImageButton imgSearch;
    FragmentListener fragmentListener;

    public static ParentFragment newInstance() {
        return new ParentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_parent, container, false);

        //즐겨찾기로 이동
        Fragment fg;
        fg = ChildFavorFragment.newInstance();
        setChildFragment(fg);

        editSearch = (EditText) v.findViewById(R.id.editTextFilter);
        toolSearch = v.findViewById(R.id.toolbar_search);
        imgSearch = v.findViewById(R.id.img_search_icon);

        editSearch.setOnClickListener(oneListener);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //입력 전
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Fragment fg;
                fg = ChildSearchFragment.newInstance();
                setChildFragment(fg);
            }
            @Override
            public void afterTextChanged(Editable s) {
                //입력했다가 다시 다 지웠을때 즐겨찾기 Fragment로
                if(editSearch.getText().length() == 0){
                    Fragment fg = ChildFavorFragment.newInstance();
                    setChildFragment(fg);
                }
                else{
                    //데이터 보내기
                    fragmentListener.onCommand(0, editSearch.getText().toString());
                    ChildSearchFragment childSearchFragment = new ChildSearchFragment();
                    childSearchFragment.isSearch(true);
                }
            }
        });

        //검색어 입력 후 완료 버튼 눌렀을때
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    imgSearch.setImageResource(R.drawable.x);
                    //키보드 내리는 기능 추가하기
                    return true;
                }
                return false;
            }
        });
        imgSearch.setOnClickListener(twoListener);

        return v;
    }



    //자식 Fragment로 이동(즐겨찾기 리스트뷰)
    View.OnClickListener oneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("Fragment 전환", "첫번째 자식 전");
            Fragment fg;
            fg = ChildFavorFragment.newInstance();
            setChildFragment(fg);
            Log.d("Fragment 전환", "첫번째 자식 후");
        }
    };

    //자식 Fragment로 이동(검색 리스트뷰)
    View.OnClickListener twoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fg;
            fg = ChildSearchFragment.newInstance();
            setChildFragment(fg);
        }
    };



    @Override
    public void onClick(View view) {}

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

    public void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.child_fragment_container, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }

}