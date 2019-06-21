package com.example.emote

import android.content.Intent
import android.os.Bundle
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

                val id_str = log_id.text.toString()
                val pw_str = log_pw.text.toString()
                log_id.setText("")
                log_pw.setText("")
                val user = DB().getUsersByQuery("tel = $id_str")
                if(user.isNullOrEmpty()) {
                    Toast.makeText(this, "reject", Toast.LENGTH_LONG).show()
                }else if(user[0].pw != pw_str) {
                    Toast.makeText(this, "reject", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "OK!", Toast.LENGTH_LONG).show()
                    val uid = user[0].uid // 전역 변수로 넘겨주기 가능?
                    nextIntent.putExtra("uid",uid)
                    startActivity(nextIntent) //selectemote확인해보셈 ~!~!
                }
            }
            R.id.btn_join1 -> {
                startActivity(Intent(this, SigninActivity::class.java))
            }
        }
    }
}
