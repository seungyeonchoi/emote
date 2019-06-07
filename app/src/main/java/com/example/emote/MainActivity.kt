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

    lateinit var adapter:ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        /* 데이터 베이스 관련 
            select.
            val result = DB().getPostsByQuery("pid = 1") // result에 List<Post> 
            result?.forEach { Log.d("result : ", "${it.title}") }
        */
        /*
            객체 선언후 insert. 빈곳은 안채워도 됨
            val user = DB.User("","01001101110", "0", "12321", "0")
            val post = DB.Post("","1", "", "내ㅐㅐ용", "새천년관", "3", "title", "2", "1")
            val emotion = DB.Emotion("1","1", "100")
            DB().insert(post)
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
