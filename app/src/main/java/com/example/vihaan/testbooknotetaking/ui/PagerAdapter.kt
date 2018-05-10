package com.example.vihaan.testbooknotetaking.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by vihaan on 3/15/18.
 */

class PagerAdapter(fm: FragmentManager?,
                   val fragments : MutableList<Fragment>
) : FragmentStatePagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.9f
    }
}