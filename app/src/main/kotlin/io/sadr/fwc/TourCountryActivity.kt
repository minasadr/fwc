package io.sadr.fwc

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import com.github.amlcurran.showcaseview.ShowcaseView
import com.github.amlcurran.showcaseview.targets.PointTarget

class TourCountryActivity : CountryActivity() {

    private var pageNumber = 1
    private val metrics = DisplayMetrics()
    private lateinit var showcaseView: ShowcaseView

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        showcaseView = ShowcaseView.Builder(this).build()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        show(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        show(this)
    }

    private fun show(activity: Activity) {
        showcaseView.hide()
        activity.windowManager.defaultDisplay.getRealMetrics(metrics)
        showcaseView = ShowcaseView.Builder(activity)
                .withMaterialShowcase()
                .setOnClickListener { showNext(1) }
                .setStyle(R.style.CustomShowcaseTheme)
                .build()
        showNext(0)
    }

    private fun showNext(inc: Int) {
        pageNumber += inc

        when (pageNumber) {
            1 -> {
                showcaseView.setShowcase(PointTarget(metrics.widthPixels / 2, metrics.heightPixels * 3 / 4), true)
                showcaseView.setContentTitle("View the answer")
                showcaseView.setContentText("Swipe the cover up to see the answer")
            }
            2 -> {
                showcaseView.setShowcase(PointTarget(metrics.widthPixels, metrics.heightPixels / 2), true)
                showcaseView.setContentTitle("Next question")
                showcaseView.setContentText("Swipe to the left to see the next question")
            }
            3 -> {
                showcaseView.setShowcase(PointTarget(0, metrics.heightPixels / 2), true)
                showcaseView.setContentTitle("Next question")
                showcaseView.setContentText("Swipe to the right does the same")
            }
            4 -> {
                showcaseView.setShowcase(PointTarget(metrics.widthPixels / 2, 0), true)
                showcaseView.setContentTitle("Leave fullscreen")
                showcaseView.setContentText("Swipe to the bottom to exit fullscreen mode")
            }
            5 -> {
                showcaseView.setShowcase(PointTarget(metrics.widthPixels / 2, metrics.heightPixels), true)
                showcaseView.setContentTitle("Leave fullscreen")
                showcaseView.setContentText("Swipe to the top does the same")
                showcaseView.setButtonText("Close")
            }
            6 -> {
                showcaseView.hide()
                val intent = Intent(this, CountryActivity::class.java)
                this.startActivity(intent)
            }
        }
    }
}
