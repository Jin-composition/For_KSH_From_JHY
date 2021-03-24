package com.example.for_ksh_from_jhy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout, linearBack, linearSearch, linearCall, linearCamera,
            linearGallery, linearPlace, linearDiary, linearMp3, linearSecret;
    private ImageButton ivOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIdFunc();

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {
        ivOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(linearLayout);
            }
        });

        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(linearLayout);
            }
        });

        linearCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:010-9272-2884");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "언제든지 전화해♥", Toast.LENGTH_SHORT).show();
            }
        });

        linearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.naver.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "나의 마지막 목표는 NAVER...!", Toast.LENGTH_SHORT).show();
            }
        });

        linearCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "우리 사진좀 찍자!!", Toast.LENGTH_SHORT).show();
            }
        });


        linearGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Gallery_Main.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "눈갱 미안 ><", Toast.LENGTH_SHORT).show();
            }
        });

        linearPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Spinner_Main.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "너와 같이 가고 싶은 곳 들을 모아봤어♥", Toast.LENGTH_SHORT).show();
            }
        });

        linearDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyDiary.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "나한테 빡치는게 있으면 여기에 적어줘", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "긍정적으로 검토해볼게ㅋㅋㅋ", Toast.LENGTH_SHORT).show();
            }
        });

        linearMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Music_Main.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "어때? 잘 만들었지? ><", Toast.LENGTH_SHORT).show();
            }
        });

        linearSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Fragment_Main.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "후 방 주 의!!!!!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void findViewByIdFunc() {
        drawerLayout = findViewById(R.id.drawerLayout);
        linearLayout = findViewById(R.id.LinearLayout);
        ivOpen = findViewById(R.id.ivOpen);
        linearBack = findViewById(R.id.linearBack);
        linearSearch = findViewById(R.id.linearSearch);
        linearCamera = findViewById(R.id.linearCamera);
        linearCall = findViewById(R.id.linearCall);
        linearGallery = findViewById(R.id.linearGallery);
        linearPlace = findViewById(R.id.linearPlace);
        linearDiary = findViewById(R.id.linearDiary);
        linearMp3 = findViewById(R.id.linearMp3);
        linearSecret = findViewById(R.id.linearSecret);
    }
}
