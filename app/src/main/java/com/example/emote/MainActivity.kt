package com.example.emote

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
    , Mypage.OnFragmentInteractionListener, Emoteboard.OnFragmentInteractionListener, Statistics.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var adapter:ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){

        adapter= ViewPagerAdapter(supportFragmentManager,layout_tab.tabCount)
        viewPager.adapter=adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layout_tab))
        layout_tab.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
              //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                viewPager.currentItem=p0!!.position
            }

        })
        layout_tab.setupWithViewPager(viewPager,true)
    }

}
