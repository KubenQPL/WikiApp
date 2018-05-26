package pl.jakubneukirch.wikiapp.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import pl.jakubneukirch.wikiapp.info.InfoFragment
import pl.jakubneukirch.wikiapp.random.RandomFragment

const val INFO_FRAGMENT = 0
const val RANDOM_FRAGMENT = 1
const val SAVED_FRAGMENT = 2

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val list: ArrayList<Fragment> = arrayListOf()

    init {
        list.add(InfoFragment.newInstance())
        list.add(RandomFragment.newInstance())
        list.add(InfoFragment.newInstance())
    }

    override fun getItem(position: Int): android.support.v4.app.Fragment = list[position]


    override fun getCount(): Int = list.size
}