package com.novelot.nver;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class RainbowView extends View {

    static final int[] WEI = new int[]{0x40, 0x40, 0x40,
            0x60, 0x60, 0x60,
            0x70, 0x70, 0x70,
            0x30, 0x30, 0x30,
            0x10, 0x10, 0x10};
    /**
     * 半径
     */
    private float mRadius;
    private ShanShape[] shapes;

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

        shapes = new ShanShape[15];
        for (int i = 0; i < 15; i++) {
            ShanShape shape = new ShanShape(mRadius, 180 / 15);
            shapes[i] = shape;
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) (2 * mRadius), (int) mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.clipRect(0, 0, mRadius * 2, mRadius);
        for (int i = 0; i < 15; i++) {
            shapes[i].setWei(WEI[i]);
            shapes[i].setRotateAngle(180 + 180 / 15 * i);
            shapes[i].draw(canvas);
        }
    }

    //    @TargetApi(11)
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int childCount = getChildCount();
//        int sumRotationAngle = 0;
//        for (int i = 0; i < childCount; i++) {
//            View childAt = getChildAt(i);
//            childAt.layout(mRadius - childAt.getWidth(),
//                    mRadius - childAt.getHeight(),
//                    mRadius,
//                    mRadius);
//            int[] point = rotationChildView(childAt, l + mRadius, t + mRadius, sumRotationAngle);
//            childAt.layout(point[0], point[1], point[0] + childAt.getWidth(), point[1] + childAt.getHeight());

//            childAt.layout(mRadius - childAt.getWidth(), mRadius - childAt.getHeight(), mRadius, mRadius);
//            childAt.setRotation(sumRotationAngle);
//
//            sumRotationAngle += 180 / childCount;
//        }
//    }

//    private int[] rotationChildView(View view, int centX, int centY, float angle) {
//
//        int width = 300;//view.getWidth();
//        int height = 60;//view.getHeight();
//        double radius = Math.sqrt(width * width + height * height);
//        double newAngle = Math.atan(width / height) + angle;
//        double x = Math.cos(newAngle) * radius;
//        double y = Math.sin(newAngle) * radius;
//
//        return new int[]{(int) x, (int) y};
//    }


    /**
     * 扇形
     */
    static class FanShape extends View {


        public int getRadius() {
            return mRadius;
        }

        public int getAngle() {
            return mAngle;
        }

        /**
         * 半径
         */
        private int mRadius;
        /**
         * 角度，扇面扫过的角度
         */
        private int mAngle;
        private Paint mPaint;
        private RectF mArcRect;
        private RectF mClipRect;

        public FanShape(Context context, int radius, int angle) {
            super(context);
            this.mRadius = radius;
            this.mAngle = angle;
            init();
        }

//        public FanShape(Context context, AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//            init();
//        }
//
//        @TargetApi(21)
//        public FanShape(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//            super(context, attrs, defStyleAttr, defStyleRes);
//            init();
//        }

        private void init() {

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            int mWidth = mRadius;
            int mHeight = (int) (mRadius * Math.sin(mAngle));
            mArcRect = new RectF(0, mHeight - mWidth, mWidth * 2, mHeight + mWidth);
            mClipRect = new RectF(0, 0, mWidth, mHeight);

        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.clipRect(mClipRect);
            mPaint.setColor(Color.RED);
            canvas.drawArc(mArcRect, 180, mAngle, true, mPaint);
        }
    }


}
