package com.example.emote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    fun OnBtnClick(view: View) {
        when (view.id) {
            R.id.btn_login -> {
                val nextIntent = Intent(this, MainActivity::class.java)
                nextIntent.putExtra("uid", 1)
                startActivity(nextIntent) //selectemote확인해보셈 ~!~!
                val id_str = log_id.text.toString()
                val pw_str = log_pw.text.toString()
                log_id.setText("")
                log_pw.setText("")
                val user = DB().getUsersByQuery("tel = $id_str")
                if(user.isNullOrEmpty()) {
                    Toast.makeText(this, "아이디가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                }else if(user[0].pw != pw_str) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_LONG).show()
                    val uid = user[0].uid // 전역 변수로 넘겨주기 가능?

                }
            }
            R.id.btn_join1 -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}
