package com.novelot.nover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dexafree.materialList.controller.MaterialListViewAdapter;
import com.dexafree.materialList.view.MaterialListView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_circle_view);

        //
        RainbowView rainbowView = (RainbowView) findViewById(R.id.rainbowV);
        rainbowView.setAdapter(new BaseRainbowAdapter(rainbowView.getRadius()));

    }


    class BaseRainbowAdapter extends RainbowAdapter {

        final int[] WEI = new int[]{0x40, 0x40, 0x40,
                0x60, 0x60, 0x60,
                0x70, 0x70, 0x70,
                0x30, 0x30, 0x30,
                0x10, 0x10, 0x10};

        private ShanShape[] shapes;

        public BaseRainbowAdapter(float mRadius) {
            shapes = new ShanShape[15];
            for (int i = 0; i < 15; i++) {
                ShanShape shape = new ShanShape(mRadius, 180 / 15);
                shape.setWei(WEI[i]);
                shapes[i] = shape;
            }
        }

        @Override
        public int getCount() {
            return shapes.length;
        }

        @Override
        public ShanShape getShape(int position) {
            return shapes[position];
        }
    }

}
