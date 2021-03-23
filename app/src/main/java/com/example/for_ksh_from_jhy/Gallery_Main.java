package com.example.for_ksh_from_jhy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class Gallery_Main extends MainActivity {
    private Gallery gallery;
    private ImageView ivPicture;
    private ArrayList<Integer> dataList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();


    //2. 데이터를 준비한다.
    public static final Integer [] pictureID = new Integer[]{R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
             R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10, R.drawable.pic11, R.drawable.pic12, R.drawable.pic13,
            R.drawable.pic14, R.drawable.pic15, R.drawable.pic16, R.drawable.pic17, R.drawable.pic18, R.drawable.pic19, R.drawable.pic20 };

    private String [] pictureName = new String[]{"거만한 표정1", "거만한 표정2", "빙구", "원숭이", "갑자기 쎈 척", "노래방에서 기분 좋음","카페에서 쎈 척", "카페에서 웃어보기", "피곤에 찌듬1", "피곤에 찌듬2",
    "무슨 표정인지 모름", "그냥 흰 티 입은 애", "거만한 표정3", "무슨 표정인지 모름2", "빕스 시절", "단기알바 시절", "공부하는 척", "공부하다가", "거만한 표정4", "멍청해보임"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //1. 리스트 뷰, 그리드 뷰, 갤러리
        gallery = findViewById(R.id.gallery);
        ivPicture = findViewById(R.id.ivPicture);


        //2. 데이터 제공
        dataList = new ArrayList<Integer>(Arrays.asList(pictureID));
        ivPicture.setImageResource(dataList.get(0));
        Toast.makeText(this, pictureName[0], Toast.LENGTH_SHORT).show();



        //3. 어댑터
        MyGalleryAdapter myGalleryAdapter = new MyGalleryAdapter(this, dataList, pictureID, pictureName);
        gallery.setAdapter(myGalleryAdapter);

        gallery.setOnItemClickListener((adapterView, view, position, l) -> {
            ivPicture.setImageResource(dataList.get(position));
            ivPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i=0; i<pictureName.length; i++){
                        list.add(new String(pictureName[i]));
                    }
                    Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        });



        gallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Gallery_Main.this, list.get(position)+" 사진이 삭제됨", Toast.LENGTH_SHORT).show();
                dataList.remove(position);
                ivPicture.setImageResource(dataList.get(0));
                myGalleryAdapter.setDataList(dataList);
                myGalleryAdapter.notifyDataSetChanged();

                return true;
            }
        });

    }
}