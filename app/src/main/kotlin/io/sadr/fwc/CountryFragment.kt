package io.sadr.fwc

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CountryFragment : Fragment() {

    companion object {
        const val ARG_COUNTRY = "Country"
        const val ARG_CAPITAL = "Capital"

        fun create(country: Pair<String, String>): CountryFragment {
            val fragment = CountryFragment()
            val args = Bundle()
            args.putString(ARG_COUNTRY, country.first)
            args.putString(ARG_CAPITAL, country.second)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup,
                              state: Bundle?): View {

        val fragment = inflater.inflate(R.layout.fragment_country, container, false) as ViewGroup

        val country = arguments.getString(ARG_COUNTRY)
        val capital = arguments.getString(ARG_CAPITAL)

        (fragment.findViewById(R.id.country_name) as TextView).text = country
        (fragment.findViewById(R.id.capital_name) as TextView).text = capital
        return fragment
    }
}
