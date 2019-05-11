package com.example.emote

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            Thread.sleep(1000) //1초 대기후 실행
        }
        catch(e:InterruptedException ){
            e.printStackTrace()
        }
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}