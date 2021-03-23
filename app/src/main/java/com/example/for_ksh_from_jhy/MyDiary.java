package com.example.for_ksh_from_jhy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MyDiary extends MainActivity {

    private DatePicker datePicker;
    private EditText edtDiary;
    private Button btnMode;
    private String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        setTitle("끄적 끄적");

        findViewByIdFunc();

        eventHandlerFunc();

    }

    private void eventHandlerFunc() {
        //1. 달력을 터치했을 때 처리해야 할 이벤트 (날짜.txt 파일을 만든다), 찾아서 읽어온다. edtText 에서 내용을 처리한다. 버튼을 수정한다
        //2. 버튼모드를 조정한다
        Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                fileName = String.valueOf(year) + "_" + String.valueOf(month+1) + "_" + String.valueOf(day) + ".txt";
                FileInputStream fis = null;
                try {
                    fis = openFileInput(fileName);
                    byte [] byteArray = new byte[1000];
                    fis.read(byteArray);
                    //trim -> 빈 공간을 잘라준다
                    String diaryData = (new String (byteArray)).trim();
                    edtDiary.setText(diaryData);
                    btnMode.setEnabled(true);
                    btnMode.setText("수정하기");
                    //fis.close(); 를 finally 에 넣으면 없는 파일인데 닫으라고 하니까 오류 발생
                    //파일이 없다면  fis = openFileInput(fileName); 이 뒤에 문장들은 아예 실행을 안함
                    //그래서 이 try 안에 써줘야함
                    fis.close();
                } catch (FileNotFoundException e) {
                    Log.d("diaryApp", "읽기모드에서 파일을 찾을 수 없음");
                    edtDiary.setHint("존재하지 않는 파일입니다");
                    btnMode.setEnabled(true);
                    btnMode.setText("새로 저장");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("diaryApp", "읽기모드에서 파일을 읽을 수 없음");
                    e.printStackTrace();
                } finally {
                    Log.d("diaryApp", "읽기모드 finally 오류");
                }
            }
        });


        //버튼이 눌러졌다는 것은 enable 이 true 가 됐다는 뜻
        btnMode.setOnClickListener(v -> {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                String diaryData = edtDiary.getText().toString();
                fos.write(diaryData.getBytes());
                toastMessage(btnMode.getText() + "완료 했습니다");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                toastMessage(btnMode.getText() + "실패 했습니다");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Log.d("diaryApp", "읽기모드 finally 오류");
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                edtDiary.setHint("새로운 일기를 써주세요");
                btnMode.setEnabled(false);
                btnMode.setText("일기저장모드");
            }

        });
    }


    private void toastMessage(String s) {
        Toast.makeText(getApplicationContext(), s , Toast.LENGTH_SHORT).show();
    }



    private void findViewByIdFunc() {
        datePicker = findViewById(R.id.datePicker);
        edtDiary = findViewById(R.id.edtDiary);
        btnMode = findViewById(R.id.btnMode);
    }
}
