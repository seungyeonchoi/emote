package com.example.emote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_show_post.*


//""
class ShowPostActivity : AppCompatActivity() {

     var pids:List<DB.Emotion>?=null
    var pid=-1
    var uid=-1
    var iter = 0
    var r_able = true
    var l_able = false //true: 공감한 상태 / false : 공감안한 상태

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_post)

        var post = DB.Post("0", "0", "", "0", "test", "0", "0", "0", "0")
        val emoteArr= arrayOf("기쁨","화남","슬픔","신남","애매","사랑","놀람","뿌듯","아픔","짜증","외롭","설렘")
         var imgArray =getResources().obtainTypedArray(R.array.drawable)


        //선택한 게시판 감정의 번호를 받아서 eid로 설정
        val i=intent
        val eid=i.getIntExtra("emotion",-1)
        Toast.makeText(this,eid.toString(),Toast.LENGTH_SHORT).show()
        pids = DB().getEmotionsbyQuery("eid = '$eid'")?.shuffled() //선택한 감정을 게시한 피드들의 컬렉션

        if(pids.isNullOrEmpty()){
            Toast.makeText(this,"이 감정을 공유한 사용자가 없습니다!",Toast.LENGTH_SHORT).show()
            finish()
        }
        else{

            text_etitle.text=emoteArr[eid]+" 게시판"
            img_emo.setImageResource(imgArray.getResourceId(eid, -1))
            showPost()
        }

        img_next.setOnClickListener {
            if (pids != null && pids!!.size > iter) {
                r_able = true
                Log.d("erorr!!", "iter : $iter, size : ${pids?.size}, ${pids==null}")
                showPost()
            }
            else {
                Toast.makeText(this, "다음글이 없습니다.", Toast.LENGTH_SHORT).show()
                Log.d("erorr!!", "iter : $iter, size : ${pids?.size}, ${pids==null}")
            }


        }
        img_like.setOnClickListener {
            if(!l_able) {
                post.heart_count = (post.heart_count.toInt() + 1).toString()
                text_count.text = post.heart_count
                DB().update(post)
                l_able = true
                img_like.setImageResource(R.drawable.like_btn)
                Toast.makeText(this, "공감하였습니다.", Toast.LENGTH_SHORT).show()
            }else{
                post.heart_count = (post.heart_count.toInt()-1).toString()
                text_count.text = post.heart_count
                DB().update(post)
                l_able=false
                img_like.setImageResource(R.drawable.img_unlike)//빈하트로 바꾸기
                Toast.makeText(this, "공감을 취소합니다.", Toast.LENGTH_SHORT).show()
            }
            Log.d("공감!", "${post.title}에 공감을 눌렀어요.")
        }
        img_report.setOnClickListener {
            if (r_able) {
                val users = DB().getUser(uid)
                var user: DB.User
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
    fun showPost(){

        pid = pids!![iter++].pid.toInt()

        val result = DB().getPostsByQuery("pid = '$pid'")
        if (result != null) {
            val post = result[0]
            uid=post.uid.toInt()
            Log.i("item",uid.toString())
            text_title.text = post.title
            text_contents.text = post.contents
            text_count.text = post.heart_count
            img_like.setImageResource(R.drawable.img_unlike)//빈하트로 바꾸기
            text_place.text = "장소 : ${post.place}"
        } else {
            Log.d("error", "$pid 에 해당하는 글이 없음.")
            Toast.makeText(this,"이 감정을 공유한 사용자가 없습니다!",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
