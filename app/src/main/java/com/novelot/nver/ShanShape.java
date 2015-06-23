package com.novelot.nver;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by V on 2015/6/23.
 */
public class ShanShape {
    private float strokeWidth;
    private static int[] COLORS = new int[]{
            Color.parseColor("#e60033"),
            Color.parseColor("#ee7800"),
            Color.parseColor("#ffd900"),
            Color.parseColor("#3eb370"),
            Color.parseColor("#0095d9"),
            Color.parseColor("#165e83"),
            Color.parseColor("#884898"),
            Color.WHITE};
    private float mRadius;
    private float mAngle;
    /*旋转过的角度*/
    private float rotateAngle;

    public void setRotateAngle(float rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    //画笔
    private final Paint mPaints;
    //
    RectF[] oval = new RectF[COLORS.length];

    public ShanShape(float radius, float angle) {
        this.mRadius = radius;
        this.mAngle = angle;
        //
        strokeWidth = radius / 2 / COLORS.length;
        //
        mPaints = new Paint();
        mPaints.setAntiAlias(true);
        mPaints.setStyle(Paint.Style.FILL);
        mPaints.setStrokeWidth(0);
//
        for (int i = 0; i < COLORS.length; i++) {
            oval[i] = new RectF(
                    0 + strokeWidth * i,
                    0 + strokeWidth * i,
                    mRadius * 2 - strokeWidth * i,
                    mRadius * 2 - strokeWidth * i);
//            oval[i] = new RectF(strokeWidth * i - mRadius, strokeWidth * i - mRadius, mRadius - strokeWidth * i, mRadius - strokeWidth * i);
        }
    }

    /*package*/void draw(Canvas canvas) {
//        canvas.translate(mRadius, mRadius);
        //1.先让画布反方向旋转rotateAngle;
//        canvas.rotate(-rotateAngle, 0, 0);
        //2.画外部的扇形；
        for (int i = 0; i < COLORS.length; i++) {
            int tmp = 1 << 6 - i;
            if ((tmp & wei) == tmp) {
                mPaints.setColor(COLORS[i]);
            } else {
                mPaints.setColor(COLORS[COLORS.length - 1]);
            }
            canvas.drawArc(oval[i], rotateAngle, mAngle, true, mPaints);
        }
        //截取
//        canvas.clipRect(0, -mRadius, mRadius, 0);
        //最后，让画布正反向旋转ratateAngle;
//        canvas.rotate(rotateAngle, 0, 0);
        //
//        canvas.translate(mRadius, mRadius);
    }

    private int wei;

    void setWei(int wei) {
        this.wei = wei;
    }
}
