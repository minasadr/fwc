package io.sadr.fwc

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout

class DragLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RelativeLayout(context, attrs, defStyle) {

    private val mDragHelper: ViewDragHelper

    private lateinit var coverView: View

    init {
        mDragHelper = ViewDragHelper.create(this, 1f, DragHelperCallback())
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.post {
            coverView = findViewById(R.id.cover)
            val layoutParams = coverView.layoutParams
            layoutParams.height = this.height / 2
            coverView.layoutParams = layoutParams
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val action = MotionEventCompat.getActionMasked(ev)
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel()
            return false
        }
        return mDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(ev)
        return true
    }

    private inner class DragHelperCallback : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean = true

        override fun onViewPositionChanged(changedView: View?, left: Int, top: Int, dx: Int, dy: Int) {
            invalidate()
        }

        override fun onViewCaptured(capturedChild: View?, activePointerId: Int) {
            super.onViewCaptured(capturedChild, activePointerId)
        }

        override fun onViewReleased(releasedChild: View?, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
        }

        override fun onEdgeTouched(edgeFlags: Int, pointerId: Int) {
            super.onEdgeTouched(edgeFlags, pointerId)
        }

        override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
            super.onEdgeDragStarted(edgeFlags, pointerId)
        }

        override fun clampViewPositionVertical(child: View?, top: Int, dy: Int): Int {
            val topBound = paddingTop
            val bottomBound = height - coverView.height

            return Math.min(Math.max(top, topBound), bottomBound)
        }

        override fun clampViewPositionHorizontal(child: View?, left: Int, dx: Int): Int {
            return super.clampViewPositionHorizontal(child, left, dx)
        }
    }
}
