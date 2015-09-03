package com.novelot.nover;

/**
 * Created by V on 2015/9/3.
 */
public class BaseRainbowAdapter extends RainbowAdapter {

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
