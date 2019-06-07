package com.example.emote

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

class ViewPagerAdapter(fm: FragmentManager,val  tabCount:Int) : FragmentPagerAdapter(fm)
{
    override fun getItem(p0: Int): Fragment? {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return when(p0){
            0 ->Mypage()
            1 ->Emoteboard()
            2 ->Statistics()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 ->"My"
            1 ->"Others"
            2 ->"Profile"
            else -> null
        }
        return null
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        //Log.e("FragmentPagerAdapter", "destroyItem position : $position")
    }
    override fun getCount() = 3
}