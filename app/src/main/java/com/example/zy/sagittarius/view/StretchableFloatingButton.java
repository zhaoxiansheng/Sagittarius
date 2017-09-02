package com.example.zy.sagittarius.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.icu.util.Measure;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ZY on 2017/8/31.
 * 自定义可折叠按钮
 */

public class StretchableFloatingButton extends ViewGroup {

  private static final int IS_SLIDE_DECREASE = 0x11;
  private static final int IS_SLIDE_INCREASE = 0X12;

  private int width;
  private int height;
  //开始圆的画笔
  private Paint startCirclePaint;
  //结束圆的画笔
  private Paint endCirclePaint;
  //中间矩形的画笔
  private Paint middleRectPaint;
  //小圆的画笔
  private Paint smallCirclePaint;
  //中间矩形
  private RectF middleRect;
  //开始圆的配置
  private int startCircleRadius;
  private Point startCenter;
  //结束圆的配置
  private int endCircleRadius;
  private Point endCenter;
  //小圆的配置
  private int smallCircleRadius;
  private Point smallCenter;
  //圆环之间的宽度
  private int y = 20;
  //矩形和圆环之间的比值
  private int y_x;
  //增加或者减少的标志
  private boolean isIncrease;

  private TextView tvContent;
  private View child;
  private int tvWidth;
  private int tvHeight;
  //文本可以伸缩的长度
  private int tx;
  private int tx_x;

  private Handler mHandler = new Handler(new Handler.Callback() {
    @Override public boolean handleMessage(Message msg) {
      switch (msg.what) {
        case IS_SLIDE_DECREASE://递减状态
          endCenter.x -= 30;
          if (endCenter.x >= startCenter.x + 30) {
            y = y_x * endCenter.x;
            tx = tx_x * endCenter.x;
            smallCircleRadius = startCircleRadius - y;
            mHandler.sendEmptyMessageDelayed(IS_SLIDE_DECREASE, 1);
          } else {
            endCenter.x = startCenter.x;
            y = 0;
            tx = startCircleRadius * 2 + 5;
            smallCircleRadius = startCircleRadius;
            setEnabled(true);
          }
          break;
        case IS_SLIDE_INCREASE://递增状态
          endCenter.x += 30;
          if (endCenter.x < width - endCircleRadius) {
            mHandler.sendEmptyMessageDelayed(IS_SLIDE_INCREASE, 1);
            y = y_x * endCenter.x;
            tx = tx_x * endCenter.x;
            smallCircleRadius = startCircleRadius - y;
          } else {
            y = 20;
            smallCircleRadius = startCircleRadius - y;
            tx = tvWidth + startCircleRadius * 2 + 10;
            endCenter.x = width - endCircleRadius;
            setEnabled(true);
          }
          break;
        default:
          break;
      }
      requestLayout();
      invalidate();
      return false;
    }
  });

  private void initUtils(Context context) {
    startCenter = new Point();
    endCenter = new Point();
    smallCenter = new Point();

    startCirclePaint = new Paint();
    startCirclePaint.setAntiAlias(true);
    startCirclePaint.setColor(Color.YELLOW);

    endCirclePaint = new Paint();
    endCirclePaint.setAntiAlias(true);
    endCirclePaint.setColor(Color.YELLOW);

    smallCirclePaint = new Paint();
    smallCirclePaint.setAntiAlias(true);//防锯齿
    smallCirclePaint.setColor(Color.BLACK);

    middleRectPaint = new Paint();
    middleRectPaint.setAntiAlias(true);
    middleRectPaint.setColor(Color.YELLOW);

    tvContent = new TextView(context);
    tvContent.setText("麻烦死了");
    tvContent.setTextColor(Color.GRAY);
    tvContent.setTextSize(16);
    addView(tvContent);
  }

  public StretchableFloatingButton(Context context) {
    super(context, null);
    initUtils(context);
  }

  public StretchableFloatingButton(Context context, AttributeSet attrs) {
    super(context, attrs, 0);
    initUtils(context);
  }

  public StretchableFloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initUtils(context);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    child.layout(startCircleRadius * 2 + 5, (height - tvHeight) / 2, tx,
        startCircleRadius + tvHeight / 2);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (width == 0) {
      //width = MeasureSpec.getSize(widthMeasureSpec);
      //height = MeasureSpec.getSize(heightMeasureSpec);
      width = 600;
      height = 100;
      startCircleRadius = height / 2;
      endCircleRadius = height / 2;
      smallCircleRadius = startCircleRadius - y;
      startCenter.set(startCircleRadius, startCircleRadius);
      endCenter.set(width - endCircleRadius, endCircleRadius);
      smallCenter.set(startCircleRadius, startCircleRadius);
      //圆环伸缩比
      y_x = y / endCenter.x;

      child = this.getChildAt(0);
      measureChild(child, width, height);
      tvHeight = child.getMeasuredHeight();
      tvWidth = child.getMeasuredWidth();

      //文本右侧的位置
      tx = startCircleRadius * 2 + 5 + tvWidth;
      //文本伸缩比
      tx_x = (tvWidth + 10) / (width - endCircleRadius * 2);
    }
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    canvas.drawCircle(startCenter.x, startCenter.y, startCircleRadius, startCirclePaint);

    middleRect = new RectF(startCenter.x, 0, endCenter.x, height);
    canvas.drawRect(middleRect, middleRectPaint);

    canvas.drawCircle(endCenter.x, endCenter.y, endCircleRadius, endCirclePaint);

    canvas.drawCircle(smallCenter.x, smallCenter.y, smallCircleRadius, smallCirclePaint);
    super.dispatchDraw(canvas);
  }

  //增加
  private void startIncrease() {
    setEnabled(false);
    isIncrease = true;
    mHandler.sendEmptyMessageDelayed(IS_SLIDE_INCREASE, 40);
  }

  //减小
  private void startDecrease() {
    setEnabled(false);
    isIncrease = false;
    mHandler.sendEmptyMessageDelayed(IS_SLIDE_DECREASE, 40);
  }

  public void startScroll() {
    if (isIncrease) {
      startDecrease();
    } else {
      startIncrease();
    }
  }
}
