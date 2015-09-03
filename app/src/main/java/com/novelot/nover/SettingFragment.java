package com.novelot.nover;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by V on 2015/9/3.
 */
public class SettingFragment extends Fragment {

    private static final String NOVER = "声明<br><br><br>" +
            "这是一款专为女性设计的APP,<br>" +
            "但其并非适合所有的女性.<br><br>" +
            "如果你消极,依赖,<br>" +
            "对不起,这里不适合你;<br><br>" +
            "但是,<br>" +
            "如果你独立,自主,<br>" +
            "欢迎你成为这里的一员!<br><br>";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setBackgroundColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        //tv.setHeight(400);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(30);
        tv.setText(Html.fromHtml(NOVER));
        return tv;
    }
}
