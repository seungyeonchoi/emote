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
            0 ->"나만의\n게시판"
            1 ->"공유\n게시판"
            2 ->"통계와\n프로필"
            else -> null
        }

    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        //Log.e("FragmentPagerAdapter", "destroyItem position : $position")
    }
    override fun getCount() = 3
}