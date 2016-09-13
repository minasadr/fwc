package io.sadr.fwc

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout

class DragLayout
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyle: Int = 0) : RelativeLayout(context, attrs, defStyle) {

    private val dragHelper: ViewDragHelper

    init {
        dragHelper = ViewDragHelper.create(this, 1f, DragHelperCallback())
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val action = MotionEventCompat.getActionMasked(ev)
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            dragHelper.cancel()
            return false
        }
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        dragHelper.processTouchEvent(ev)
        return true
    }

    private inner class DragHelperCallback : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean = true

        override fun onViewPositionChanged(changedView: View,
                                           left: Int,
                                           top: Int,
                                           dx: Int,
                                           dy: Int) {

            invalidate()
        }

        override fun clampViewPositionVertical(view: View, top: Int, dy: Int): Int {
            val bottomBound = height - view.height

            return Math.min(Math.max(top, paddingTop), bottomBound)
        }

        override fun onViewReleased(view: View, xvel: Float, yvel: Float) {
            val h = view.height
            val center = view.top + h / 2
            if (center < height / 2) {
                view.top = 0
                view.bottom = h
            } else {
                view.top = height - view.height
                view.bottom = height
            }
        }
    }
}
