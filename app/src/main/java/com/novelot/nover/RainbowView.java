package com.novelot.nover;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * TODO: document your custom view class.
 */
public class RainbowView extends View {


    private float mRotateAngle;
    /**
     * 半径
     */
    private float mRadius;
    //    private ShanShape[] shapes;
    protected RainbowAdapter mAdapter;

    public RainbowView(Context context) {
        super(context);
        init(null, 0);
    }

    public RainbowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RainbowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @TargetApi(21)
    public RainbowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RainbowView, defStyle, 0);
        mRadius = a.getDimension(R.styleable.RainbowView_radiuo, 0);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if(mAdapter!=null)
//            mRadius = mAdapter.getShape(0).getRadius();
        setMeasuredDimension((int) (2 * mRadius), (int) mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.clipRect(0, 0, mRadius * 2, mRadius);
//        for (int i = 0; i < 15; i++) {
//            shapes[i].setWei(WEI[i]);
//            shapes[i].setRotateAngle(180 + 180 / 15 * i);
//            shapes[i].draw(canvas);
//        }
        if (mAdapter != null) {
            int count = mAdapter.getCount();
            for (int i = 0; i < count; i++) {
                ShanShape shape = mAdapter.getShape(i);
                if (shape != null) {
                    shape.setRotateAngle(180 + 180 / count * i + mRotateAngle);
                    shape.draw(canvas);
                }
            }
        }
    }

    /**
     * 设置adapter
     */
    public void setAdapter(RainbowAdapter a) {
        mAdapter = a;
    }

    public float getRadius() {
        return mRadius;
    }

    float x = 0f;
    float y = 0f;
//    double startAngle;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                Log.v("rainbow", mRadius + ":down x=" + x + ",y=" + y);
                //
//                startAngle = getAngle(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                float x2 = event.getX();
                float y2 = event.getY();
                //
                double a1 = Math.atan((x - mRadius) / (y - mRadius));
                double a2 = Math.atan((x2 - mRadius) / (y2 - mRadius));
                double da = a2 - a1;
                //
                Log.v("rainbow", "da=" + da);
//                if (da < 0) {
//                    turnLeft();
//                } else {
//                    turnRight();
//                }
                mRotateAngle -= da * 15;
                invalidate();
                x = event.getX();
                y = event.getY();
                //
//                double currAngle = getAngle(event.getX(), event.getY());
//                mRotateAngle -= currAngle - startAngle;
//                invalidate();
//                startAngle = getAngle(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }

        return false;

    }


    private double getAngle(double xTouch, double yTouch) {
        double x = xTouch - (mRadius / 2d);
        double y = mRadius - yTouch - (mRadius / 2d);

        switch (getQuadrant(x, y)) {
            case 1:
                return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;

            case 2:
            case 3:
                return 180 - (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);

            case 4:
                return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;

            default:
                // ignore, does not happen
                return 0;
        }
    }

    /**
     * @return The selected quadrant.
     */
    private static int getQuadrant(double x, double y) {
        if (x >= 0) {
            return y >= 0 ? 1 : 4;
        } else {
            return y >= 0 ? 2 : 3;
        }
    }

}
