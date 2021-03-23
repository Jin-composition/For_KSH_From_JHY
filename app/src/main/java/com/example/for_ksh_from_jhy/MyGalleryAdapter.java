package com.example.for_ksh_from_jhy;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyGalleryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> dataList;
    private Integer[] pictureID;
    private String [] pictureName;

    public MyGalleryAdapter(Context context, ArrayList<Integer> dataList, Integer[] pictureID, String[] pictureName) {
        this.context = context;
        this.dataList = dataList;
        this.pictureID = pictureID;
        this.pictureName = pictureName;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDataList(ArrayList<Integer> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return (Object) dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            //새로운 뷰를 만든다.
            ImageView iv = new ImageView(context);
            iv.setLayoutParams(new Gallery.LayoutParams(200, 300));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(5,5,5,5);
            iv.setImageResource(dataList.get(position));

            view = iv;
        }else{
            ((ImageView)view).setImageResource(dataList.get(position));
        }

        //이미 만들어진 뷰에 데이터만 입력해서 리턴해주면 된다.

        return view;
    }
}