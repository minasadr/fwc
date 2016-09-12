package io.sadr.fwc

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet

class CoverView(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) :
        CardView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        this.setMeasuredDimension(parentWidth, parentHeight / 2)
    }
}