package com.zj.example.textswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * TextSwitcher demo
 * @author zhengjiong
 * @date 2014年10月13日14:18:22
 */
public class MainActivity extends Activity {
    private int mCounter;

    private Button mButtonNext;
    private Button mButtonPrev;
    private TextSwitcher mTextSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mButtonPrev = (Button) findViewById(R.id.prev);
        mButtonNext = (Button) findViewById(R.id.next);
        mTextSwitcher = (TextSwitcher) findViewById(R.id.switcher);

        mTextSwitcher.setFactory(viewFactory);

        final Animation left_in = AnimationUtils.loadAnimation(this, R.anim.slide_from_left_in);
        final Animation right_out = AnimationUtils.loadAnimation(this, R.anim.slide_to_right_out);

        final Animation right_in = AnimationUtils.loadAnimation(this, R.anim.slide_from_right_in);
        final Animation left_out = AnimationUtils.loadAnimation(this, R.anim.slide_to_left_out);

        mButtonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mTextSwitcher.setInAnimation(left_in);
                mTextSwitcher.setOutAnimation(right_out);

                mCounter++;
                mTextSwitcher.setText(String.valueOf(mCounter));
            }
        });
        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextSwitcher.setInAnimation(right_in);
                mTextSwitcher.setOutAnimation(left_out);
                mCounter--;
                mTextSwitcher.setText(String.valueOf(mCounter));
            }
        });
    }

    private ViewSwitcher.ViewFactory viewFactory = new ViewSwitcher.ViewFactory() {
        @Override
        public View makeView() {

            TextView textView = new TextView(MainActivity.this);
            textView.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            textView.setTextAppearance(MainActivity.this, android.R.style.TextAppearance_Large);

            return textView;
        }
    };

}
