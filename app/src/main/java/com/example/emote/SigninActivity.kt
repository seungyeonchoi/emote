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
        when(view.id){
            R.id.btn_back->{
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            R.id.btn_join2->{
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }
    }
}
