package com.jeromeyang.risingbubble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Jeromeyang on 16/5/30.
 */
public class RisingBubble extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private static final String TAG = "RisingBubble";

    private SurfaceHolder mHolder;

    private Canvas mCanvas;

    private boolean isDrawing = false;

    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);



    public RisingBubble(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    public RisingBubble(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RisingBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG,"surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG,"surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG,"surfaceDestroyed");
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();

        while (isDrawing){
            Draw();
        }

        long end = System.currentTimeMillis();
        if (end - start < 100){
            try{
                Thread.sleep(100 - (end - start));
            }catch (InterruptedException e){
            }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:



                    break;
        }
        return super.onTouchEvent(event);
    }


    private void Draw(){
        try {
            mCanvas = mHolder.lockCanvas();

        }catch (Exception e){

        }finally {
            if (mCanvas != null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
