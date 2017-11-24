package com.inventivestack.swipecards.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;

/**
 * Created by akumar1 on 11/24/2017.
 */

abstract class BaseCardView extends AdapterView {

    private int heightMeasureSpec;
    private int widthMeasureSpec;


    public BaseCardView(Context context) {
        super(context);
    }

    public BaseCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
    }


    public int getWidthMeasureSpec() {
        return widthMeasureSpec;
    }

    public int getHeightMeasureSpec() {
        return heightMeasureSpec;
    }
}


