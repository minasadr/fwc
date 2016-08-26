package io.sadr.fwc


import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager

class CountryActivity : Activity() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_country)

        val viewPager = findViewById(R.id.pager) as ViewPager

        viewPager.adapter = PagerAdapter(fragmentManager)
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return CountryFragment.create(position)
        }

        override fun getCount(): Int {
            return 50000
        }
    }

}
