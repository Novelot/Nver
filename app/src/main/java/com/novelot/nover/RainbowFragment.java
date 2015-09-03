package com.novelot.nover;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 */
public class RainbowFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.sample_circle_view, null);
        RainbowView rainbowView = (RainbowView) v.findViewById(R.id.rainbowV);
        rainbowView.setAdapter(new BaseRainbowAdapter(rainbowView.getRadius()));
        return v;
    }


}
