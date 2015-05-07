package com.example.zhy_9.parallaxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by ZHY_9 on 2015/5/7.
 */
public class FirstGuideFragement extends BaseGuideFragment{

    final long ANIMATION_DURATION = 500;
    final long ANIMATION_OFFSET = 200;

    private int[] mAnimationViewIds = { R.id.guide_item_2, R.id.guide_item_3, R.id.guide_item_4,
            R.id.guide_item_5, R.id.guide_item_6, R.id.guide_item_7,
            R.id.guide_item_8, R.id.guide_item_9, R.id.guide_item_10,
            R.id.guide_item_11, R.id.guide_item_12, R.id.guide_item_13};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_first, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int i = 0; i < mAnimationViewIds.length; i++){
            Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.splash_guide_items);
            animation.setDuration(ANIMATION_DURATION);
            animation.setStartOffset(ANIMATION_OFFSET);
            view.findViewById(mAnimationViewIds[i]).startAnimation(animation);
        }
    }

    @Override
    public int[] getChildViewIds() {
        return new int[]{
                R.id.guide_item_1
        };
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_guide_first;
    }
}
