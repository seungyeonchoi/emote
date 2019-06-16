package com.example.emote

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
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

        val i=intent
        val uid=i.getStringExtra("uid")
        init()

    }

    fun alert(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("요즘 우울하거나 슬픈 일이 많네요!  \n 전문 상담가를 안내합니다. \n 02-2380-3247 (해피해피 상담)")
            .setTitle("감정 관리")
            .setIcon(R.drawable.abc_ic_star_black_48dp)
        builder.setPositiveButton("확인"){_,_->
           /* val str=Uri.parse("tel:82-02-2380-3247")
                 val intent= Intent(Intent.ACTION_DIAL,str)
            startActivity(intent)*/
        }

        val dialog = builder.create()
        dialog.show()

    }
    fun init(){

        alert()
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
