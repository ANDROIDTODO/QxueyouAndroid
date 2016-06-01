package com.jeromeyang.risingbubble;

/**
 * Created by Jeromeyang on 16/6/1.
 */
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class MySurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {
    private Paint paint = new Paint();
    private Timer timer;
    private TimerTask task;
    public MySurfaceView(Context context) {
        super(context);
        paint.setColor(Color.YELLOW);
        getHolder().addCallback(this);
    }

    private CopyOnWriteArrayList<Bolls> bollses = new CopyOnWriteArrayList<>();



    public void draw() {
        Canvas canvas = getHolder().lockCanvas();// 锁定画布
        // 绘制图形
        if (canvas == null){
            return;
        }
        canvas.drawColor(Color.WHITE);// 初始化画布
        for(int i=0;i<bollses.size();i++){
            if (bollses.get(i).getCy()-bollses.get(i).getRadius()<0){
                bollses.remove(i);
            }
            try {
                canvas.drawCircle(bollses.get(i).getCx(), bollses.get(i).getCy(), bollses.get(i).getRadius(), paint);
                bollses.get(i).draw();
            }catch (ArrayIndexOutOfBoundsException e){

            }

        }

        getHolder().unlockCanvasAndPost(canvas);// 解锁画布
    }
    public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        };
        timer.schedule(task, 16, 16);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("add",bollses.size()+"");
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                Bolls bolls = new Bolls(this,x,y);
                bollses.add(bolls);
                Log.e("add",bollses.size()+"");
                break;
        }
        return super.onTouchEvent(event);
    }

    public void stopTimer() {
        timer.cancel();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startTimer();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopTimer();
    }
}
