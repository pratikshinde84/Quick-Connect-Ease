package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.secureserver.R;

public class ManualActivity extends AppCompatActivity {
    TextView tv_next;
    ViewPager viewPager;
    LinearLayout layoutDots;
    int[] layouts;
    TextView[] dots;
    MyViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        tv_next = findViewById(R.id.tv_next);
        viewPager = findViewById(R.id.viewPager);
        layoutDots = findViewById(R.id.layout_Dots);

        layouts = new int[]{
                R.layout.intro_one, R.layout.intro_two, R.layout.intro_three
        };

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {
                    Intent i=new Intent(ManualActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        viewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        addBottomDots(0);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layouts.length - 1) {
                tv_next.setText("Start");
            } else {
                tv_next.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];
        int[] activeColors = getResources().getIntArray(R.array.active);
        int[] inActiveColors = getResources().getIntArray(R.array.inactive);
        layoutDots.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(inActiveColors[currentPage]);
            layoutDots.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[currentPage].setTextColor(activeColors[currentPage]);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {

        LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject( View view,  Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,  Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + 1;
    }
}
