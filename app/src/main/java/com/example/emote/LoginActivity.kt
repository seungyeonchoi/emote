package com.example.emote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun OnBtnClick(view: View) {
        when(view.id){
            R.id.btn_login->{
                startActivity(Intent(this,SelectEmoteActivity::class.java)) //selectemote확인해보셈 ~!~!
            }
            R.id.btn_join1->{
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}
