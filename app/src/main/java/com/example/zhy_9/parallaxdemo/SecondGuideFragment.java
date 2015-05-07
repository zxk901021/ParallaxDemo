package com.example.zhy_9.parallaxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZHY_9 on 2015/5/7.
 */
public class SecondGuideFragment extends BaseGuideFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_second, container, false);
    }

    @Override
    public int[] getChildViewIds() {
        return new int[]{
                R.id.guide_item_1,
                R.id.guide_item_2,
                R.id.guide_item_3
        };
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_guide_second;
    }
}
