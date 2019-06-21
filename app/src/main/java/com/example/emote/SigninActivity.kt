package com.example.emote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signin.*

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
                    Toast.makeText(this, "You are already an Emote member.", Toast.LENGTH_LONG).show()
                } else if (id_str.length != 11) {
                    Toast.makeText(this, "Invalid phone number.", Toast.LENGTH_LONG).show()
                } else if (pw_str.length < 4) {
                    Toast.makeText(this, "Password must be at least 4 length.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                    DB().insert(DB.User("", id_str, "0", pw_str, "0"))
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
