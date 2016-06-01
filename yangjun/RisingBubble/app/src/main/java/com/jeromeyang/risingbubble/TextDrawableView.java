package com.jeromeyang.risingbubble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jeromeyang on 16/5/30.
 */
public class TextDrawableView extends TextView{

    private String TAG = "TextDrawableView";

    public final static int DRAWABLE_LEFT = 0;
    public final static int DRAWABLE_TOP = 1;
    public final static  int DRAWABLE_RIGHT = 2;
    public final static int DRAWABLE_BOTTOM = 3;

    private Drawable drawable;


    private int position = DRAWABLE_RIGHT;


    public TextDrawableView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        if (drawable != null){
            Log.e(TAG,"initview");
            switch (position){
                case DRAWABLE_LEFT : setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null); break;
                case DRAWABLE_TOP:  setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);break;
                case DRAWABLE_RIGHT : setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);break;
                case DRAWABLE_BOTTOM : setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);break;
            }
        }
    }

    public TextDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public TextDrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    public void setDrawable(Drawable drawable,int position){
        this.position = position;
        this.drawable = drawable;
    }

    public void setDrawableSize(int width,int height){
        if (drawable == null && getCompoundDrawables()[DRAWABLE_LEFT] == null&&getCompoundDrawables()[DRAWABLE_RIGHT] == null
        &&getCompoundDrawables()[DRAWABLE_TOP]==null &&getCompoundDrawables()[DRAWABLE_BOTTOM] == null){
            throw new NullPointerException();
        }else {
            Log.e(TAG,"size");
            drawable = zoomDrawable(drawable,width,height);
            switch (position){
                case DRAWABLE_LEFT : setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null); break;
                case DRAWABLE_TOP:  setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);break;
                case DRAWABLE_RIGHT : setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);break;
                case DRAWABLE_BOTTOM : setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);break;
            }
        }
    }


    private Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();




        Log.e(TAG,width+","+height + drawable.getBounds().left+","+drawable.getBounds().right+","+drawable.getMinimumWidth()+","+drawable.getMinimumHeight());

        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }







}
