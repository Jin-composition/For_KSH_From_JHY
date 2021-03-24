package com.example.for_ksh_from_jhy;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    //프래그먼트를 보여줄 개수를 선정 -> 50개 프래그먼트 중에서 4개만 보겠다 하면 4개만 보면 됨
    private int count;

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity, int count) {
        super(fragmentActivity);
        this.count = count;
    }

    //프래그먼트를 만들어서 제공해주는 함수
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = position % count;  //4 % 4 (0 ~ 3)
        switch (index){
            case 0: return FragmentFirst.newInstance(index + 1);
            case 1: return FragmentSecond.newInstance(index + 1);
            case 2: return FragmentThird.newInstance(index + 1);
            case 3: return FragmentFourth.newInstance(index + 1);
            case 4: return FragmentFifth.newInstance(index + 1);
            case 5: return FragmentSixth.newInstance(index + 1);
            case 6: return FragmentSeventh.newInstance(index + 1);
            case 7: return FragmentEighth.newInstance(index + 1);
            case 8: return FragmentNinth.newInstance(index + 1);
            case 9: return FragmentTenth.newInstance(index + 1);
            default: Log.e("FragmentAdaptor: ", "FragmentAdaptor 에러 발생");
        }
        return null;
    }

    //프래그 먼트 몇개 정도 준비하고 있는지 묻는거 -> 대충 200개로 한다
    @Override
    public int getItemCount() {

        return 200;
    }
}
