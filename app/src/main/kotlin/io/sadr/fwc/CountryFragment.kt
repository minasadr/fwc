package io.sadr.fwc

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CountryFragment : Fragment() {

    companion object {
        const val ARG_PAGE = "Page"

        fun create(pageNumber: Int): CountryFragment {
            val fragment = CountryFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE, pageNumber)
            fragment.arguments = args
            return fragment
        }
    }

    private var pageNumber: Int = 0

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        pageNumber = arguments.getInt(ARG_PAGE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_country, container, false) as ViewGroup

        (rootView.findViewById(R.id.country_name) as TextView).text = pageNumber.toString()

        return rootView
    }
}
