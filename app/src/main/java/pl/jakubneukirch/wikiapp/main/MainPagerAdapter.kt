package pl.jakubneukirch.wikiapp.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import pl.jakubneukirch.wikiapp.search.SearchFragment

const val SEARCH_FRAGMENT = 0
const val LIST_FRAGMENT = 1
const val SAVED_FRAGMENT = 2

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val list: ArrayList<Fragment> = arrayListOf()

    init {
        list.add(SearchFragment.newInstance())
        list.add(SearchFragment.newInstance())
        list.add(SearchFragment.newInstance())
    }

    override fun getItem(position: Int): android.support.v4.app.Fragment = list[position]


    override fun getCount(): Int = list.size
}