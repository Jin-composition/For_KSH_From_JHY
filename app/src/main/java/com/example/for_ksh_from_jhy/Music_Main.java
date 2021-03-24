package com.example.for_ksh_from_jhy;

import android.Manifest;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Music_Main extends MainActivity{
    private RecyclerView recyclerView;
    private Button btnPlay,btnStop,btnPause;
    private TextView tvMP3, tvTime;
    private SeekBar pbMP3;

    private ArrayList<MusicData> sdCardList = new ArrayList<>();
    private MediaPlayer mPlayer;

    private int selectPosition;

    private MusicAdapter musicAdapter;

    private boolean flag = false;
    private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        findViewByIdFunc();

        findContentProviderMP3ToArrayList();

        musicAdapter = new MusicAdapter(getApplicationContext(),sdCardList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(musicAdapter);

        //앞에서 구현한 인터페이스를 여기서 재정의 함
        musicAdapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //이미 곡이 나오고 있는데 다른거 누르면 두 곡이 겹쳐서 나옴
                if(btnPlay.isEnabled() == true){
                    selectPosition = position;
                    btnPlay.setEnabled(true);
                    btnStop.setEnabled(false);
                    btnPause.setEnabled(false);
                    btnPause.setEnabled(false);

                    tvMP3.setText("준비 중인 음악" + sdCardList.get(position).getTitle());
                }else{
                    Toast.makeText(Music_Main.this, "다른 노래를 듣고 싶으시면 중지 버튼을 누르고 다시 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        selectPosition = 0;
        btnPlay.setEnabled(true);
        btnStop.setEnabled(false);
        btnPause.setEnabled(false);



        btnPlay.setOnClickListener(v -> {
            mPlayer = new MediaPlayer();
            MusicData musicData = sdCardList.get(selectPosition);
            Uri musicUri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, musicData.getId());
            try {
                mPlayer.setDataSource(Music_Main.this, musicUri);
                mPlayer.prepare();
                mPlayer.start();

                btnPlay.setEnabled(false);
                btnStop.setEnabled(true);
                btnPause.setEnabled(true);
                tvMP3.setText("실행중인 음악"+musicData.getTitle());


                //시크바도 같이 움직여 줘야함
                Thread thread = new Thread(){
                    //ctrl + o
                    @Override
                    public void run() {
                        if(mPlayer == null) return; //노래가 없으면 스레드를 종료하겠다.
                        runOnUiThread(() -> {
                            //MP3 한 곡의 총 걸리는 시간을 체크함 -> 프로그레스바에 전체 시간을 가져와서 세팅함
                            pbMP3.setMax(mPlayer.getDuration());
                        });

                        //노래가 진행되는 그 시간을 표시함 -> 위젯이 아님
                        while(mPlayer.isPlaying()){
                            runOnUiThread(() -> {
                                pbMP3.setProgress(mPlayer.getCurrentPosition());
                                //위에 정한 mm분ss초 로 나타내고 싶다
                                tvTime.setText("진행시간: "+sdf.format(mPlayer.getCurrentPosition()));
                            });
                            SystemClock.sleep(1000);
                        }
                    }
                };thread.start();

            } catch (IOException e) {
                Log.d("MainActivity", "외부파일 접근 에러");
            }
        });

        btnStop.setOnClickListener(v -> {
            mPlayer.stop();
            mPlayer.reset();
            btnPlay.setEnabled(true);
            btnStop.setEnabled(false);
            btnPause.setEnabled(false);
            tvMP3.setText("음악이 중지 됨" );
            tvTime.setText("진행시간 : ");
            pbMP3.setProgress(0);

            Thread thread = new Thread(){
                //ctrl + o
                @Override
                public void run() {
                    //노래가 진행되는 그 시간을 표시함 -> 위젯이 아님
                    while(mPlayer.isPlaying()){
                        runOnUiThread(() -> {
                            pbMP3.setProgress(mPlayer.getCurrentPosition());
                            //위에 정한 mm분ss초 로 나타내고 싶다
                            tvTime.setText("진행시간: "+sdf.format(mPlayer.getCurrentPosition()));
                        });
                        SystemClock.sleep(1000);
                    }
                }
            };thread.start();

        });

        btnPause.setOnClickListener(v -> {
            if(flag == false){
                mPlayer.pause();
                btnPause.setText("이어듣기");
                flag = true;
            }else{
                mPlayer.start();
                btnPause.setText("일시정지");

                //시크바도 같이 움직여 줘야함
                Thread thread = new Thread(){
                    //ctrl + o
                    @Override
                    public void run() {
                        //노래가 진행되는 그 시간을 표시함 -> 위젯이 아님
                        while(mPlayer.isPlaying()){
                            runOnUiThread(() -> {
                                pbMP3.setProgress(mPlayer.getCurrentPosition());
                                //위에 정한 mm분ss초 로 나타내고 싶다
                                tvTime.setText("진행시간: "+sdf.format(mPlayer.getCurrentPosition()));
                            });
                            SystemClock.sleep(1000);
                        }
                    }
                };thread.start();
                flag = false;
            }
        });

        pbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean myTouchStart) {
                //if (myTouchStart == true) 안에 안 넣어주면 노래는 계속 진행되는데 seekBar 를 계속 찾고 있어서 렉 걸린 것 처럼 들림
                if(myTouchStart == true){
                    mPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }   // end of onCreate

    private void findContentProviderMP3ToArrayList() {
        // 컨텐트 프로바이더에서는 핸드폰에서 다운로드했던 음악파일은 모두 관리되고 있다.
        String[] data = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION};

        // resolver에 요청한다 query문으로...
        Cursor cursor = getApplicationContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                data,null,null,data[2] + " ASC");    // Cursor타입이 반환됨, 원하는 정보를 찾아줌
        // data가 내가 보고 싶은 항목, 그리고 TITLE 항목으로 오름차순으로 가져와라
        if(cursor != null){
            while(cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(data[0]));
                String artist = cursor.getString(cursor.getColumnIndex(data[1]));
                String title = cursor.getString(cursor.getColumnIndex(data[2]));
                String albumArt = cursor.getString(cursor.getColumnIndex(data[3]));
                String duration = cursor.getString(cursor.getColumnIndex(data[4]));

//                try{
//                    Integer.parseInt(albumArt);
//                } catch (Exception e){
//                    Toast.makeText(this, albumArt, Toast.LENGTH_SHORT).show();
//                    Log.d("MainActivity",""+albumArt);
//                    albumArt = "0";
//                }


                MusicData musicData = new MusicData(id,artist,title,albumArt,duration);
                sdCardList.add(musicData);
            }   // end of while
        }
    }   // end of findContentProviderMP3ToArrayList

    private void findViewByIdFunc() {
        recyclerView = findViewById(R.id.recyclerView);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnPause = findViewById(R.id.btnPause);
        pbMP3 = findViewById(R.id.pbMP3);
        tvMP3 = findViewById(R.id.tvMP3);
        tvTime = findViewById(R.id.tvTime);
    }   // end of findViewByIdFunc
}

