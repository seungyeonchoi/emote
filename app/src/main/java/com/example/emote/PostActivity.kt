package com.example.emote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
    lateinit var spinArr:ArrayList<SeekBar>
    lateinit var post:Post


    var mode:String="writing"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        spinArr= arrayListOf(emoteSeekBar1,emoteSeekBar2,emoteSeekBar3)
        //모드선택
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
            if(isFilled())
                Toast.makeText(this, "등록되었습니다!",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "모든 필드를 채워주세요!",Toast.LENGTH_SHORT).show()
        }
    }
    //감정선택한 갯수에 따라 프로그레스 바 isEnabled=false 처리해야함
    fun isFilled():Boolean{
        var emotionValue=ArrayList<Int>()
        var title=""
        var contents=""
        var place=""
        var pActivity=""

        for(i in 0 .. 3){
            if(spinArr[i].isEnabled==true)
                if(spinArr[i].progress==0)
                    return false
                else
                    emotionValue.add(spinArr[i].progress)
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
            post=Post(emotionValue,title,contents,place,pActivity)
        }
        else{ //녹음모드

        }
        return true
    }

}
