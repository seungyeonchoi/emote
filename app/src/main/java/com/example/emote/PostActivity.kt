package com.example.emote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_post.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PostActivity : AppCompatActivity() {

    lateinit var spinArr:ArrayList<SeekBar>
    lateinit var post:Post
    var uid="temp"
    var mode:String="writing"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        spinArr= arrayListOf(emoteSeekBar1,emoteSeekBar2,emoteSeekBar3)

        modeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                when (checkedId) {
                    R.id.writeModeBtn -> { //디비 등록
                        mode="write"
                    }
                    R.id.recordModeBtn -> { //디비 등록안해요 !  ! ! !
                        mode="record"
                    }
                }
            }
        }

        submit.setOnClickListener {
            if(isFilled()) {
                //db에 넣는 작업을 합시다 ,, ,,
                
                Toast.makeText(this, "등록되었습니다!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "모든 필드를 채워주세요!",Toast.LENGTH_SHORT).show()
        }
    }
    //감정선택한 갯수에 따라 프로그레스 바 isEnabled=false 처리해야함
    fun getDate():String{
        val simpleDateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA)
        return simpleDateFormat.format(Date())
    }

    fun isFilled():Boolean{
        var emotionValue=ArrayList<Int>()
        var title=""
        var contents=""
        var place=""
        var pActivity=""
        var public=false

        for(i in 0 .. 2){
            if(spinArr[i].isEnabled==true)
                if(spinArr[i].progress==0)
                    return false
                else
                    emotionValue.add(spinArr[i].progress) //감정 정도값이 아니라 기쁨/ 슬픔 같은 감정에 따른 값을 넘겨야함!
        }
        if(mode=="writing"){
            title=titleEditText.text.toString()
            if(title=="")
                return false
            contents=contentsEditText.text.toString()
            if(contents=="")
                return false
            place=placeEditText.text.toString()
            if(place=="")
                return false
            pActivity=activitySpin.selectedItem.toString()

            publicRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId != -1) {
                    when (checkedId) {
                        R.id.privateModeBtn -> { //디비 등록
                            public=false
                        }
                        R.id.publicModeBtn -> { //디비 등록안해요 !  ! ! !
                            public=true
                        }
                    }
                }
            }
            post=Post("-1",uid,getDate(),public, title,contents,emotionValue,place,pActivity)
        }
        else{ //녹음모드

        }
        return true
    }

}
