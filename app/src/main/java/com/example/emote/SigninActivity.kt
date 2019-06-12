package com.example.emote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class SigninActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

    }
    fun OnBtnClick(view: View) {
        when (view.id) {
            R.id.btn_join2 -> {
                val id_str = sign_id.text.toString()
                val pw_str = sign_pw.text.toString()

                val user = DB().getUsersByQuery("tel = $id_str")
                if (user?.size!! > 0) {
                    Toast.makeText(this, "이미 Emote 회원입니다.", Toast.LENGTH_LONG).show()
                } else if (id_str.length != 11) {
                    Toast.makeText(this, "유효하지 않은 전화번호 입니다.", Toast.LENGTH_LONG).show()
                } else if (pw_str.length < 4) {
                    Toast.makeText(this, "비밀번호의 길이가 4 이상이어야 합니다.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "회원가입 되었습니다.", Toast.LENGTH_LONG).show()
                    DB().insert(DB.User("", id_str, "0", pw_str, "0"))
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
