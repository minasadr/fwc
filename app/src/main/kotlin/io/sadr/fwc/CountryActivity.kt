package io.sadr.fwc

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.DisplayMetrics
import android.view.View
import com.github.amlcurran.showcaseview.ShowcaseView
import com.github.amlcurran.showcaseview.targets.PointTarget
import java.util.*

class CountryActivity : Activity() {
    companion object {
        const val SHOWCASE = "SHOWCASE"
    }

    val random = Random()

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_country)

        val viewPager = findViewById(R.id.pager) as ViewPager

        viewPager.adapter = PagerAdapter(fragmentManager)
        viewPager.currentItem = position()
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    viewPager.setCurrentItem(COUNTRIES.size - 2, false)
                } else if (position == COUNTRIES.size - 1) {
                    viewPager.setCurrentItem(1, false)
                }
            }
        })

        val showcase = intent.getBooleanExtra(SHOWCASE, false)
        if (showcase) {
            Showcase(this)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val viewPager = findViewById(R.id.pager) as ViewPager
            viewPager.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return CountryFragment.create(COUNTRIES[position()])
        }

        override fun getCount(): Int {
            return COUNTRIES.size
        }
    }

    private fun position(): Int = 1 + random.nextInt(COUNTRIES.size - 2)

    private class Showcase(activity: Activity) {
        val metrics = DisplayMetrics()
        var counter = 0
        val showcaseView: ShowcaseView

        init {
            activity.windowManager.defaultDisplay.getRealMetrics(metrics)
            showcaseView = ShowcaseView.Builder(activity)
                    .setTarget(PointTarget(metrics.widthPixels / 2, metrics.heightPixels * 3 / 4))
                    .setOnClickListener { buildOnClickListener() }
                    .setContentTitle("View the Answer")
                    .setContentText("Swipe the Cover Up to see the Answer")
                    .setStyle(5)
                    .build()
            showcaseView.setButtonText("Next")
        }

        private fun buildOnClickListener() {
            when (++counter) {
                1 -> {
                    showcaseView.setShowcase(PointTarget(metrics.widthPixels, metrics.heightPixels / 2), true)
                    showcaseView.setContentTitle("Next Question")
                    showcaseView.setContentText("Swipe to the Left to see the Next Question")
                }
                2 -> {
                    showcaseView.setShowcase(PointTarget(0, metrics.heightPixels / 2), true)
                    showcaseView.setContentTitle("Next Question")
                    showcaseView.setContentText("Swipe to the Right does the same")
                }
                3 -> {
                    showcaseView.setShowcase(PointTarget(metrics.widthPixels / 2, 0), true)
                    showcaseView.setContentTitle("Leave Fullscreen")
                    showcaseView.setContentText("Swipe to the Bottom to exit Fullscreen mode")
                }
                4 -> {
                    showcaseView.setShowcase(PointTarget(metrics.widthPixels / 2, metrics.heightPixels), true)
                    showcaseView.setContentTitle("Leave Fullscreen")
                    showcaseView.setContentText("Swipe to the Top does the same")
                }
                5 -> showcaseView.hide()
            }
        }
    }
}
