package com.example.for_ksh_from_jhy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import me.relex.circleindicator.CircleIndicator3;

public class Fragment_Main extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 indicator;
    private TextView text;
    private FragmentStateAdapter pagerAdapter;
    //명시적 초기값으로 줌
    private int numberPage = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //ViewPager2
        viewPager2 = findViewById(R.id.viewPager2);
        //Adapter
        //1. 프래그먼트 어댑터를 만들고 보여줄 개수를 설정한다
        //2. 프래그먼트 어댑터를 viewPager2 에 연결시켜준다
        //프래그먼트를 어디서 보여줄건지, 개수는 몇개인지
        pagerAdapter = new FragmentAdapter(this, numberPage);
        viewPager2.setAdapter(pagerAdapter);
        //Indicator
        indicator = findViewById(R.id.indicator);
        //2. circleIndicator 에 viewpager2를 연결해주면 자동으로 개수를 체크해서 실행해준다
        //전체 개수에서 현재 보여줄 위치를 지정한다
        indicator.setViewPager(viewPager2);
        indicator.createIndicators(numberPage, 0);

        //3. viewPager2 에 방향 설정 좌우, 상하
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        //ViewPager2 Item을 200개 만들었으니 현재 위치를 100으로setCurrentItem(100) 하여 좌우로 슬라이딩 가능하도록 하였습니다.
        //4.viewPager2 에서 슬라이딩 이 몇개까지 가능한지
        viewPager2.setCurrentItem(100);
        viewPager2.setOffscreenPageLimit(3);

        //5. viewPager2 event처리
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    viewPager2.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                indicator.animatePageSelected(position % numberPage);
            }

        });


        final float pageMargin =
                getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset =
                getResources().getDimensionPixelOffset(R.dimen.offset);

        //setPageTransformer를 통해 프래그먼트간 애니메이션 맞춤설정도 가능합니다.
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (viewPager2.getOrientation() ==
                        ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager2) ==
                            ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });
    }
}
