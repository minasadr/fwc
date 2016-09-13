package io.sadr.fwc

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import java.util.*

open class CountryActivity : Activity() {

    private val pager = Pager()

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_country)

        val viewPager = findViewById(R.id.view_pager) as ViewPager

        viewPager.currentItem = pager.next()
        viewPager.adapter = PagerAdapter(pager, fragmentManager)

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    viewPager.setCurrentItem(COUNTRIES.size - 2, false)
                } else if (position == COUNTRIES.size - 1) {
                    viewPager.setCurrentItem(1, false)
                }
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val viewPager = findViewById(R.id.view_pager) as ViewPager
            viewPager.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }
}

private class PagerAdapter(val pager: Pager, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return CountryFragment.create(COUNTRIES[pager.next()])
    }

    override fun getCount(): Int {
        return COUNTRIES.size
    }
}

private class Pager {

    private val random = Random()

    fun next(): Int = 1 + random.nextInt(COUNTRIES.size - 2)
}
