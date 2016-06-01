package com.jeromeyang.risingbubble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
//        TextDrawableView tv = (TextDrawableView) findViewById(R.id.tv);
//        tv.setDrawable(getResources().getDrawable(R.mipmap.ic_launcher),TextDrawableView.DRAWABLE_RIGHT);
//        tv.setDrawableSize(100,100);
    }
}
