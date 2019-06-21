package com.example.emote

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_post.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PostActivity : AppCompatActivity() {
    val PERMISSION_REQUEST=1234

    lateinit var spinArr:ArrayList<SeekBar>
    lateinit var imvArr:ArrayList<ImageView>
    lateinit var emoteArr:ArrayList<Int>
    lateinit var post:Post
    var uid=""
    var mode:String="writing"
    var public="0"
   var selectedEmote= mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        initPermission()
        val defaultArray = applicationContext.resources.obtainTypedArray(R.array.drawable)


        spinArr= arrayListOf(emoteSeekBar1,emoteSeekBar2,emoteSeekBar3)
        imvArr=arrayListOf(emotion_1,emotion_2,emotion_3)
        val i=intent
        if(i.hasExtra("uid"))
            uid=i.getStringExtra("uid")
        Log.i("item","아이디"+uid)
        for(j in 1 .. 3){
           if(i.hasExtra("emotion"+j.toString())){
               selectedEmote.add(i.getIntExtra("emotion"+j,-1))
               imvArr[j-1].setImageResource(defaultArray.getResourceId(selectedEmote[j-1],-1))
           }
            else{
               spinArr[j-1].visibility=View.INVISIBLE
           }
        }
        publicRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                when (checkedId) {
                    R.id.privateModeBtn -> {
                        public="0"
                    }
                    R.id.publicModeBtn -> {
                        public="1"
                    }
                }
            }
        }
        modeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                when (checkedId) {
                    R.id.writeModeBtn -> {
                        mode = "write"
                        recordBtn.visibility = View.GONE
                        submit.visibility= View.VISIBLE
                        publicModeBtn.isEnabled=true
                        var mCount=0
                        for(i in selectedEmote) {
                            if (i != -1) {
                                spinArr[mCount++].visibility = View.VISIBLE
                            } else
                                spinArr[mCount++].visibility = View.INVISIBLE
                        }
                            contentsEditText.visibility=View.VISIBLE
                    }
                    R.id.recordModeBtn -> {
                        mode = "record"
                        for(i in spinArr){
                            i.progress=0
                            i.visibility=View.INVISIBLE
                        }
                        recordBtn.visibility = View.VISIBLE
                        submit.visibility= View.VISIBLE
                        publicModeBtn.isEnabled=false
                        contentsEditText.visibility=View.INVISIBLE
                    }
                }
            }
        }

        recordBtn.setOnClickListener {
            val ftitle=titleEditText.text.toString()
            if(ftitle.isNotEmpty()) {
                titleEditText.text.clear()
                var mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/emote"
                val dir = File(mFileName)
                if (!dir.exists()) {
                    dir.mkdir()
                }
                val now = System.currentTimeMillis()
                val date = Date(now)
                val fName = SimpleDateFormat("yyyyMMdd").format(date) + ftitle
                var num = 1
                var rAddr = ""
                while (true) {
                    rAddr = mFileName + "/" + fName + "_" + num.toString() + ".m4A"
                    val file = File(rAddr)
                    if (file.exists()) {
                        num++
                    } else
                        break

                }

                var recorder: MediaRecorder? = MediaRecorder()
                recorder!!.reset()
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
                recorder.setOutputFile(rAddr)

                try {

                    recorder.prepare()
                    recorder.start()
                    Toast.makeText(this, "start record..", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e("SampleAudioRecorder", "Exception:" + e)
                }
                //alert 다이얼로그 띄우기
                val builder = AlertDialog.Builder(this) //builder: 다이얼로그의 속성 설정만 해줌!
                builder.setMessage("Do you want to stop?")
                    .setTitle(" ...")

                builder.setPositiveButton("save") { //오른쪽 버튼
                        _, _ ->
                    //인자 2개라는 의미
                    if (recorder != null) {
                        recorder!!.stop()
                        recorder!!.release()
                        recorder = null
                        Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("cancel") { //왼쪽버튼
                        dialog, _ ->
                    val file=File(rAddr)
                    if(file.exists()){
                        file.delete()
                    }
                    dialog.cancel()
                }

                val dialog = builder.create() //찐으로 다이얼로그 만들어짐
                dialog.show()
            }
            else{
                Toast.makeText(this,"Please Write post's title!", Toast.LENGTH_SHORT).show()
            }

        }
        submit.setOnClickListener {
            if(isFilled()) {
                val mPost = DB.Post("",post.pb, getDate(), post.contents, post.place, "0", post.title, post.activity, uid)
                DB().insert(mPost)
                var items=DB().getPosts() as MutableList<DB.Post>

                    for(i in items.size-1 downTo 0){
                        if(items[i].title==post.title && items[i].place==post.place) //pid를 가져올 조건은 더 생각해봐야할듯
                            post.pid=items[i].pid
                    }
//                if(items!=null) {
//                    items = items as MutableList<DB.Post>
//                    for(i in items.size-1 downTo 0){
//                        if(items[i].title==post.title && items[i].place==post.place) //pid를 가져올 조건은 더 생각해봐야할듯
//                            post.pid=items[i].pid
//                    }
//                }
//                else
//                    post.pid="5"
                var mCount=0
                for(i in selectedEmote){
                    if(i!=-1) {
                        val mEmotion=DB.Emotion(i.toString(),post.pid,(spinArr[mCount++].progress).toString())
                        DB().insert(mEmotion)
                    }
                }

                titleEditText.text.clear()
                contentsEditText.text.clear()
                placeEditText.text.clear()
                emoteSeekBar1.progress=0
                emoteSeekBar2.progress=0
                emoteSeekBar3.progress=0
                activitySpin.setSelection(0)

                Toast.makeText(this, "save!", Toast.LENGTH_SHORT).show()
                val i=Intent(this,MainActivity::class.java)
                i.putExtra("uid",uid)
                startActivity(i)
            }
            else
                Toast.makeText(this, "please write down all item!",Toast.LENGTH_SHORT).show()
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

        for(i in 0 .. 2){
            if(spinArr[i].visibility==View.VISIBLE)
                if(spinArr[i].progress==0)
                    return false
                else
                    emotionValue.add(spinArr[i].progress) //감정 정도값이 아니라 기쁨/ 슬픔 같은 감정에 따른 값을 넘겨야함!
        }
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


            post=Post("-1",uid,getDate(),public, title,contents,emotionValue,place,pActivity)

        return true
    }
    fun initPermission(){
        var requestArr=arrayOf(android.Manifest.permission.RECORD_AUDIO,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if(!checkAppPermission(requestArr))
            askPermission(requestArr,PERMISSION_REQUEST) //제일 처음엔 권한정보없으므로 자동으로 else로 넘어옴!
    }

    fun checkAppPermission(requestPermission: Array<String>): Boolean { //인자 : 요청하는 퍼미션 정보
        val requestResult = BooleanArray(requestPermission.size)
        for (i in requestResult. indices ) {
            requestResult[i] = ContextCompat.checkSelfPermission( this, requestPermission[i] ) == PackageManager. PERMISSION_GRANTED
            if (!requestResult[i]) {
                return false //다시 request 함 ! (사용자한테 다시 퍼미션 허락 요구)
            }
        }
        return true
    }//요청하는 퍼미션이 전부 체크되어야 true 됨!(중간에 사용자가 하나라도 거부하면 false 로 종료됨ㅎㅂㅎ)

    fun askPermission(requestPermission: Array<String>, REQ_PERMISSION: Int) {
        ActivityCompat.requestPermissions( this, requestPermission, REQ_PERMISSION )
    } // askPermission

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_REQUEST -> {
                if (!checkAppPermission (permissions))
                { // 퍼미션 동의하지 않았을 때 할일 // 앱종료 finish();
                    Toast.makeText(applicationContext,"reject", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

    }


}
