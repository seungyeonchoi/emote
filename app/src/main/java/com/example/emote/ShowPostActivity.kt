package com.example.emote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


//""
class ShowPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_post)

        var post = DB.Post("0", "0", "", "0", "test", "0", "0", "0", "0")
        val eid = 1
        var iter = 0
        var r_able = true
        var l_able = true
        val pids = DB().getEmotionsbyQuery("eid = '$eid'")?.shuffled()

        var pid = ""
        if (pids != null && pids[iter] != null) {
            pid = pids[iter++]?.pid
            Log.d("pid bring me here", pid)
        }
        else {
            Log.d("error", "$eid 에 해당하는 글이 없음.")
            //이전 화면으로 돌아가기.
        }
        val result = DB().getPostsByQuery("pid = $pid")
        if (result != null) {
            post = result[0]
            text_etitle.text = DB().getName(DB.Emotion(eid.toString(), "", "")) + " 게시판"
            text_title.text = post.title
            text_contents.text = post.contents
            text_count.text = post.heart_count
            text_place.text = "장소 : ${post.place}"
            Log.d("ppppppppp : ", post.pid)

        } else {
            Log.d("error", "$pid 에 해당하는 글이 없음.")
            //이전 화면으로 돌아가기.
        }
        img_next.setOnClickListener {
            if (pids != null && pids.size > iter) {
                pid = pids[iter++]?.pid
                r_able = true
                Log.d("erorr!!", "iter : $iter, size : ${pids?.size}, ${pids==null}")

                val result = DB().getPostsByQuery("pid = $pid")
                if (result != null) {
                    post = result[0]
                    text_etitle.text = DB().getName(DB.Emotion(eid.toString(), "", "")) + " 게시판"
                    text_title.text = post.title
                    text_contents.text = post.contents
                    text_count.text = post.heart_count
                    text_place.text = "장소 : ${post.place}"
                    Log.d("ppppppppp : ", post.pid)
                } else {
                    Log.d("error", "$pid 에 해당하는 글이 없음.")
                    //이전 화면으로 돌아가기.
                }
            }
            else {
                Toast.makeText(this, "다음글이 없습니다.", Toast.LENGTH_SHORT).show()
                Log.d("erorr!!", "iter : $iter, size : ${pids?.size}, ${pids==null}")
            }


        }
        img_like.setOnClickListener {
            if(l_able) {
                post.heart_count = (post.heart_count.toInt() + 1).toString()
                DB().update(post)
                text_count.text = post.heart_count
                l_able = false
            }else{
                Toast.makeText(this, "이미 공감한 게시글입니다.", Toast.LENGTH_SHORT).show()
            }
            Log.d("공감!", "${post.title}에 공감을 눌렀어요.")
        }
        img_report.setOnClickListener {
            if (r_able) {
                val users = DB().getUser(post.uid.toInt())
                val user: DB.User
                if (users != null) {
                    users[0].caution = (users[0].caution.toInt() + 1).toString()
                    user = users[0]
                    Toast.makeText(this, "신고가 접수되었습니다.", Toast.LENGTH_SHORT).show()
                    r_able = false
                } else {
                    user = DB.User("", "", "", "", "")
                    Log.d("error!!!", "해당하는 사용자가 없음(탈퇴?).")
                    Toast.makeText(this, "탈퇴하거나 밴 당한 사용자입니다.", Toast.LENGTH_SHORT).show()
                }
                DB().update(user)
            }
            else{
                Toast.makeText(this, "이미 신고한 게시글입니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
