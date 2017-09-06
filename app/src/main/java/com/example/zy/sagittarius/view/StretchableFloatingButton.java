package com.example.zy.sagittarius.view;

import android.content.Context;
import android.content.res.TypedArray;
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

import com.example.zy.sagittarius.R;

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
  private Paint mPaint;
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

  //控件背景颜色
  private int bgColor;
  //内圈小圆颜色
  private int innerCircleColor;
  //字体颜色
  private int textColor;
  //文字
  private String text;
  //字体大小
  private float textSize;
  //速度增减
  private float speed;
  //旋转角度
  private float degrees;

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

  private void initAttrs(Context context, AttributeSet attrs){
    startCenter = new Point();
    endCenter = new Point();
    smallCenter = new Point();

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StretchableFloatingButton);
    bgColor = typedArray.getColor(R.styleable.StretchableFloatingButton_bac_color, Color.YELLOW);
    innerCircleColor = typedArray.getColor(R.styleable.StretchableFloatingButton_inner_circle_color, Color.BLACK);
    textColor = typedArray.getColor(R.styleable.StretchableFloatingButton_text_color, Color.BLACK);
    textSize = typedArray.getFloat(R.styleable.StretchableFloatingButton_text_size, 20);
    text = typedArray.getString(R.styleable.StretchableFloatingButton_text);
    speed = typedArray.getFloat(R.styleable.StretchableFloatingButton_speed, 80);
    degrees = typedArray.getFloat(R.styleable.StretchableFloatingButton_degrees, 90);
    typedArray.recycle();

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Color.WHITE);

    tvContent = new TextView(context);
    tvContent.setText(text);
    tvContent.setTextColor(textColor);
    tvContent.setTextSize(textSize);
    addView(tvContent);
  }

  public StretchableFloatingButton(Context context) {
    super(context, null);
  }

  public StretchableFloatingButton(Context context, AttributeSet attrs) {
    super(context, attrs, 0);
    initAttrs(context, attrs);
  }

  public StretchableFloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initAttrs(context, attrs);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    child.layout(startCircleRadius * 2 + 5, (height - tvHeight) / 2, tx,
        startCircleRadius + tvHeight / 2);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (width == 0) {
      // TODO: 2017/9/6 目前还有问题 测量不准确
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
    mPaint.setColor(Color.WHITE);
    canvas.drawCircle(startCenter.x, startCenter.y, startCircleRadius, mPaint);

    middleRect = new RectF(startCenter.x, 0, endCenter.x, height);
    canvas.drawRect(middleRect, mPaint);

    canvas.drawCircle(endCenter.x, endCenter.y, endCircleRadius, mPaint);

    mPaint.setColor(Color.BLACK);
    canvas.drawCircle(smallCenter.x, smallCenter.y, smallCircleRadius, mPaint);
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
