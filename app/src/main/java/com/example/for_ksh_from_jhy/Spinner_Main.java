package com.example.for_ksh_from_jhy;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Spinner_Main extends MainActivity{

    private Spinner spinner1;
    private LinearLayout pictureLayout;
    private MyGraphicView myGraphicView;
    private Integer position;
    private ArrayList<String> list = new ArrayList<>();

    public static final Integer [] pictureID = new Integer[]{ R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8,
            R.drawable.p9, R.drawable.p10, R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15, R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19};

    private String [] pictureName = new String[]{"방이동 골뱅이탕", "화양동 뱃놈", "왕십리 제일곱창", "왕십리 고기를 품다", "왕십리 정은이네 곱창", "종로3가 여진곱", "왕십리 스시도쿠",
    "왕십리 대도식당", "왕십리 유래회관", "왕십리 춘향미엔", "왕십리 엽기꼼닭발", "성수동 곰 식당", "왕십리 한방 닭 한마리", "왕십리 쭈꾸미도사", "한양대 라라옥", "한양닭발신오돌뼈", "상왕십리 인기명", "광장시장 육회 자매집", "방이동 죽변항" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        //spinner 에 들어갈 데이터
        for(int i=0; i<pictureName.length; i++){
            list.add(new String(pictureName[i]));
        }

        //스피너를 가져옴
        spinner1 = findViewById(R.id.spinner1);
        pictureLayout = findViewById(R.id.pictureLayout);


        //스피너와 연결할 어댑터를 가져온다.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        //스피너와 어댑터를 연결한다.
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                myGraphicView.setSelectImageID(pictureID[position]);
                myGraphicView.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 내가 만든 뷰
        myGraphicView = new MyGraphicView(this);

        //내가 만든 뷰를 pictureLayout 에 첨부 하는 거
        pictureLayout.addView(myGraphicView);

        //myGraphicView 를 롱클릭 하면 옵션 메뉴로 등록한다.
        registerForContextMenu(myGraphicView);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1 : myGraphicView.setAngle(myGraphicView.getAngle() + 20);
                break;
            case 2: myGraphicView.setScaleX(myGraphicView.getScaleX() + 0.2f);
                myGraphicView.setScaleY(myGraphicView.getScaleY() + 0.2f);break;
            case 3 : myGraphicView.setScaleX(myGraphicView.getScaleX() - 0.2f);
                myGraphicView.setScaleY(myGraphicView.getScaleY() - 0.2f);break;
            default:
                Toast.makeText(this, "정신차려", Toast.LENGTH_SHORT).show(); break;
        }
        myGraphicView.invalidate();

        return true;
    }


    //옵션 메뉴 만들기

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if( v == myGraphicView){
            menu.add(0,1,0,"회전");
            menu.add(0,2,0,"확대");
            menu.add(0,3,0,"축소");
        }
    }
}
