package com.shoping.yt

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by fmy on 2017/11/16.
 */
class MyRecycleView : RecyclerView {

    constructor(context: Context) : this(context, attrs = null) {}

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, defStyle = 0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
//        if (e?.action == MotionEvent.ACTION_MOVE) {
//            return false
//        }
        return super.onInterceptTouchEvent(e)
    }

//    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
//
//        var heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, View.MeasureSpec.AT_MOST)
//        super.onMeasure(widthSpec, heightMeasureSpec)
//    }
}