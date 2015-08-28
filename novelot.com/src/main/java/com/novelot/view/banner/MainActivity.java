package com.novelot.view.banner;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ViewPager pagerBanner;

    private void assignViews() {
        pagerBanner = (ViewPager) findViewById(R.id.pager_banner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        //
        pagerBanner.setOffscreenPageLimit(0);
//        pagerBanner.setOnPageChangeListener(mBannerChangedListener);
//        pagerBanner.setOnTouchListener(mBannerTouchListener);
        pagerBanner.setAdapter(mBannerAdapter);
    }


    private int[] sColor = {Color.RED, Color.BLUE, Color.YELLOW};
    private PagerAdapter mBannerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public Object instantiateItem(ViewGroup container, int pos) {
            ImageView view = new ImageView(MainActivity.this);
            view.setImageDrawable(new ColorDrawable(sColor[pos % 3]));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        public void destroyItem(ViewGroup container, int pos, Object object) {
            container.removeView((View) object);
        }

        public String getPageTitle(int pos) {
            String title = "" + pos;
            return title;
        }
    };
}
