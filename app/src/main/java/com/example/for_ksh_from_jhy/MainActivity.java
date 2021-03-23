package com.example.for_ksh_from_jhy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout, linearBack, linearSearch, linearCall, linearCamera, linearGallery, linearPlace, linearDiary;
    private Button btnOpenMenu,  btnGraphicView;
    private ImageView ivCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIdFunc();

        eventHandlerFunc();
    }

    private void eventHandlerFunc() {
        btnOpenMenu.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        linearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.naver.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        linearCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });


        linearGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Gallery_Main.class);
                startActivity(intent);
            }
        });

        linearPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Spinner_Main.class);
                startActivity(intent);
            }
        });

        linearDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyDiary.class);
                startActivity(intent);
            }
        });


    }

    private void findViewByIdFunc() {
        drawerLayout = findViewById(R.id.drawerLayout);
        linearLayout = findViewById(R.id.LinearLayout);
        btnOpenMenu = findViewById(R.id.btnOpenMenu);
        linearBack = findViewById(R.id.linearBack);
        linearSearch = findViewById(R.id.linearSearch);
        linearCamera = findViewById(R.id.linearCamera);
        linearCall = findViewById(R.id.linearCall);
        linearGallery = findViewById(R.id.linearGallery);
        linearPlace = findViewById(R.id.linearPlace);
        linearDiary = findViewById(R.id.linearDiary);
//        btnCloseMenu = findViewById(R.id.btnCloseMenu);
//        btnGraphicView = findViewById(R.id.btnGraphicView);
    }
}
