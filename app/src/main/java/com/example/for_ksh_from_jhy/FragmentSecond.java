package com.example.for_ksh_from_jhy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSecond extends Fragment {
    private ImageView imgBanner2;
    private TextView tvName2;
    private int fragNumber;



    public static FragmentSecond newInstance(int fragNumber){
       FragmentSecond fragment = new FragmentSecond();
       Bundle bundle = new Bundle();
       bundle.putInt("fragNumber", fragNumber);
       fragment.setArguments(bundle);
       return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragNumber = getArguments().getInt("fragNumber", 0);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container, false);

        return view;
    }

    // 이안에서 객체를 찾고 하고 싶은 event 처리를 다 하면 됨
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgBanner2 = view.findViewById(R.id.imgBanner2);
        tvName2 = view.findViewById(R.id.tvName2);
        tvName2.setText("Page" + fragNumber);

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
