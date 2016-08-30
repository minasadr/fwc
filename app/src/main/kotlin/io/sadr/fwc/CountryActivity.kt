package io.sadr.fwc


import android.app.Activity
import android.app.AlertDialog
import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import java.util.*

class CountryActivity : Activity() {
    val sampleCountries = arrayOf(
            Pair("", ""),
            Pair("Iran", "Tehran"),
            Pair("Germany", "Berlin"),
            Pair("Turkey", "Ankara"),
            Pair("France", "Paris"),
            Pair("Italy", "Rome"),
            Pair("", ""))

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
                    viewPager.setCurrentItem(sampleCountries.size - 2, false)
                } else if (position == sampleCountries.size - 1) {
                    viewPager.setCurrentItem(1, false)
                }
            }
        })
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
                .setIcon(R.drawable.close_octagon)
                .setTitle("Closing Application!")
                .setMessage("Are you sure you want to close the application?")
                .setPositiveButton("Yes") { dialog, which -> finish() }
                .setNegativeButton("No", null)
                .show()
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return CountryFragment.create(sampleCountries[position()])
        }

        override fun getCount(): Int {
            return sampleCountries.size
        }
    }


    private fun position(): Int = 1 + random.nextInt(sampleCountries.size - 2)
}
