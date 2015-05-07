package com.example.zhy_9.parallaxdemo;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;


public class MainActivity extends ActionBarActivity {

    final float PARALLAX_COEFFICIENT = 1.2f;
    final float DISTANCE_COEFFICIENT = 0.5f;

    TextSwitcher mTextSwitcher;

    ViewPager mPager;
    CirclePageIndicator mPagerIndicator;

    FragmentAdapter mAdapter;

    SparseArray<int[]> mLayoutViewIdsMap = new SparseArray<int[]>();

    private void addGuide(BaseGuideFragment fragment) {
        mAdapter.addItem(fragment);
        mLayoutViewIdsMap.put(fragment.getRootViewId(), fragment.getChildViewIds());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSwitcher = (TextSwitcher) findViewById(R.id.tip);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerIndicator = (CirclePageIndicator) findViewById(R.id.indicator);

        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        addGuide(new FirstGuideFragment());
        addGuide(new SecondGuideFragment());
        addGuide(new ThirdGuideFragment());
        addGuide(new FourthGuideFragment());
        addGuide(new FifthGuideFragment());
        addGuide(new SixthGuideFragment());

        mPager.setAdapter(mAdapter);
        mPagerIndicator.setViewPager(mPager);

        mPager.setPageTransformer(true, new ParallaxTransformer(PARALLAX_COEFFICIENT, DISTANCE_COEFFICIENT));
        mPagerIndicator.setOnPageChangeListener(new GuidePageChangeListener());
    }

    class ParallaxTransformer implements ViewPager.PageTransformer{

        float parallaxCoefficient;
        float distanceCoefficient;

        public ParallaxTransformer(float parallaxCoefficient, float distanceCoefficient){
            this.parallaxCoefficient = parallaxCoefficient;
            this.distanceCoefficient = distanceCoefficient;
        }

        @Override
        public void transformPage(View view, float v) {
            float scrollXOffset = view.getWidth()*parallaxCoefficient;

            ViewGroup pageViewWrapper = (ViewGroup) view;
            int[] layer = mLayoutViewIdsMap.get(pageViewWrapper.getChildAt(0).getId());
            for (int id: layer){
                View page = view.findViewById(id);
                if (page != null){
                    page.setTranslationX(scrollXOffset*v);
                }
                scrollXOffset *= distanceCoefficient;
            }
        }
    }

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener{

        ArgbEvaluator mColorEvaluator;

        int mPageWidth,mTotalScrollWidth;

        int mGuideStartBackgroundColor,mGuideEndBackgroundColor;

        String[] mGuideTips;

        public GuidePageChangeListener() {
            mColorEvaluator = new ArgbEvaluator();

            mPageWidth = getWindowManager().getDefaultDisplay().getWidth();
            mTotalScrollWidth = mPageWidth * mAdapter.getCount();

            mGuideStartBackgroundColor = getResources().getColor(R.color.guide_start_background);
            mGuideEndBackgroundColor = getResources().getColor(R.color.guide_end_background);

            mGuideTips = getResources().getStringArray(R.array.array_guide_tips);
        }


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float ratio = (mPageWidth * position + positionOffsetPixels) / (float) mTotalScrollWidth;
            Integer color = (Integer) mColorEvaluator.evaluate(ratio, mGuideStartBackgroundColor, mGuideEndBackgroundColor);
            mPager.setBackgroundColor(color);

        }

        @Override
        public void onPageSelected(int position) {
            if (mGuideTips != null && mGuideTips.length > position) {
                mTextSwitcher.setText(mGuideTips[position]);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

}
