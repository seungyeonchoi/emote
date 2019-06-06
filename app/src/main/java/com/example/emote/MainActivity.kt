package com.example.emote

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
    , Mypage.OnFragmentInteractionListener, Emoteboard.OnFragmentInteractionListener, Statistics.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    private val IP_ADDRESS = "http://kiseok.dothome.co.kr/sql.php"

    lateinit var adapter:ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        /*
        MySQL 명령어.
        button_insert.setOnClickListener{
            val task = InsertData()
            task.execute(IP_ADDRESS, "select * from user")
        }
        }
        */
    }

    fun init(){

        adapter= ViewPagerAdapter(supportFragmentManager,layout_tab.tabCount)
        viewPager.adapter=adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                layout_tab.getTabAt(0)?.setIcon(R.drawable.icon_1_w)
                layout_tab.getTabAt(1)?.setIcon(R.drawable.icon_2_w)
                layout_tab.getTabAt(2)?.setIcon(R.drawable.icon_3_w)

                when(p0) {
                    0 -> layout_tab.getTabAt(0)?.setIcon(R.drawable.icon_1_b)
                    1 -> layout_tab.getTabAt(1)?.setIcon(R.drawable.icon_2_b)
                    2 -> layout_tab.getTabAt(2)?.setIcon(R.drawable.icon_3_b)
                }
            }

        })

        layout_tab.setupWithViewPager(viewPager)
        layout_tab.getTabAt(0)?.setIcon(R.drawable.icon_1_b)
        layout_tab.getTabAt(1)?.setIcon(R.drawable.icon_2_w)
        layout_tab.getTabAt(2)?.setIcon(R.drawable.icon_3_w)

    }

}
