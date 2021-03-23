package com.example.for_ksh_from_jhy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyGraphicView extends View {
    private Context context;
    private float scaleX = 0.5f;
    private float scaleY = 0.5f;
    private float angle = 0.0f;
    private Integer selectImageID;


    //생성자를 2개 만들기
    public MyGraphicView(Context context) {
        super(context);
        this.context = context;
    }

    public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 캔버스 중앙 좌표를 구한다.
        int cenX = this.getWidth() / 2;
        int cenY = this.getHeight() / 2;

        //캔버스 크기를 결정한다.
        canvas.scale(scaleX, scaleY, cenX, cenY);

        //캔버스에 회전을 진행
        canvas.rotate(angle, cenX, cenY);

        //그림을 가져와서 캔버스에 그림을 그린다.
        if (selectImageID == null) {
            selectImageID = R.drawable.pic1;
        }
        Bitmap picture = BitmapFactory.decodeResource(getResources(), selectImageID);

        //사진 좌표를 가져온다.
        int picX = (this.getWidth() - picture.getWidth()) / 2;
        int picY = (this.getHeight() - picture.getHeight()) / 2;

        //캔버스에다 그림좌표를 그린다.
        canvas.drawBitmap(picture, picX, picY, null);

        //비트맵 리소스를 해제한다.
        picture.recycle();
    }

    @Override
    public float getScaleX() {

        return scaleX;
    }

    @Override
    public void setScaleX(float scaleX) {

        this.scaleX = scaleX;
    }

    @Override
    public float getScaleY() {

        return scaleY;
    }

    @Override
    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getAngle() {

        return angle;
    }

    public void setAngle(float angle) {

        this.angle = angle;
    }


    public void setSelectImageID(Integer selectImageID) {

        this.selectImageID = selectImageID;
    }

}
