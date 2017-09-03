package com.bway.dot_drag;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义圆
 */

public class CirCleView extends View {

    private float circle_x;
    private float circle_y;
    private final Paint paint;
    private final float circle_radius;
    private final int color;

    public CirCleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CirCleView);
        circle_x = a.getDimension(R.styleable.CirCleView_circle_x, 10);
        circle_y = a.getDimension(R.styleable.CirCleView_circle_x, 10);
        color = a.getColor(R.styleable.CirCleView_circle_color, Color.YELLOW);
        circle_radius = a.getDimension(R.styleable.CirCleView_circle_radius, 50);
        a.recycle();

        //初始化一个paint
        paint = new Paint();
        //给paint设置颜色
        paint.setColor(color);
        //给paint设置模式 这里是充满
        paint.setStyle(Paint.Style.FILL);
        //给paint设置抗锯齿
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //在canvas讲paint画上去
        canvas.drawCircle(circle_x, circle_y, circle_radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //圆心的X坐标设置成当前所触摸屏幕的X坐标
        circle_x = event.getX();
        //圆心的Y坐标设置成当前所触摸屏幕的Y坐标
        circle_y = event.getY();
        //event.getAction()
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("CirCleView", " 按下: X坐标 " + circle_x + "  Y坐标: " + circle_y);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("CirCleView", " 移动: X坐标 " + circle_x + "  Y坐标: " + circle_y);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("CirCleView", " 抬起: X坐标 " + circle_x + "  Y坐标: " + circle_y);
                break;
            default:
                break;
        }
        //刷新界面
        // invalidate()和postInvalidate()的区别
        //都是刷新界面  前者是在UI线程自身中使用，而后者在非UI线程中使用。
        invalidate();
        return true;

    }
}
