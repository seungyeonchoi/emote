package com.example.emote

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
    lateinit var post:Post
    var uid="temp"
    var mode:String="writing"
    var isRecord=false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        initPermission()
        spinArr= arrayListOf(emoteSeekBar1,emoteSeekBar2,emoteSeekBar3)

        modeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                when (checkedId) {
                    R.id.writeModeBtn -> {
                        mode = "write"
                        recordBtn.visibility = View.GONE
                        submit.visibility= View.VISIBLE
                        publicModeBtn.isEnabled=true
                        emoteSeekBar1.isEnabled=true
                        emoteSeekBar2.isEnabled=true
                        emoteSeekBar3.isEnabled=true
                    }
                    R.id.recordModeBtn -> {
                        mode = "record"
                        emoteSeekBar1.progress=0
                        emoteSeekBar2.progress=0
                        emoteSeekBar3.progress=0

                        emoteSeekBar1.isEnabled=false
                        emoteSeekBar2.isEnabled=false
                        emoteSeekBar3.isEnabled=false
                        recordBtn.visibility = View.VISIBLE
                        submit.visibility= View.INVISIBLE
                        publicModeBtn.isEnabled=false
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
                    Toast.makeText(this, "녹음을 시작합니다..", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e("SampleAudioRecorder", "Exception:" + e)
                }
                //alert 다이얼로그 띄우기
                val builder = AlertDialog.Builder(this) //builder: 다이얼로그의 속성 설정만 해줌!
                builder.setMessage("녹음을 끝내시겠습니까?")
                    .setTitle(mFileName + "녹음중 ...")

                builder.setPositiveButton("저장") { //오른쪽 버튼
                        _, _ ->
                    //인자 2개라는 의미
                    if (recorder != null) {
                        recorder!!.stop()
                        recorder!!.release()
                        recorder = null
                        Toast.makeText(this, "녹음이 저장되었습니다", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("취소") { //왼쪽버튼
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
                Toast.makeText(this,"제목을 입력해주세요!", Toast.LENGTH_SHORT).show()
            }

        }
        submit.setOnClickListener {
            if(isFilled()) {
                //db에 넣는 작업을 합시다 ,, ,,
                titleEditText.text.clear()
                contentsEditText.text.clear()
                placeEditText.text.clear()
                emoteSeekBar1.progress=0
                emoteSeekBar2.progress=0
                emoteSeekBar3.progress=0
                activitySpin.setSelection(0)
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
        if(mode=="write"){
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
                        R.id.privateModeBtn -> {
                            public=false
                        }
                        R.id.publicModeBtn -> {
                            public=true
                        }
                    }
                }
            }
            post=Post("-1",uid,getDate(),public, title,contents,emotionValue,place,pActivity)
        }
        else{ //녹음모드

            //녹음해서 저장하고 title은 녹음 content는 파일저장우치/파일명/확장자, 무적권 나만보기 모드
        }
        return true
    }
    fun initPermission(){
        var requestArr=arrayOf(android.Manifest.permission.RECORD_AUDIO,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if(checkAppPermission(requestArr))
            Toast.makeText(this,"권한이 승인됨",Toast.LENGTH_SHORT).show()
        else
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
                if (checkAppPermission (permissions))
                { // 퍼미션 동의했을 때 할 일
                    Toast.makeText(applicationContext,"권한이 승인됨",Toast.LENGTH_SHORT).show()
                }
                else { // 퍼미션 동의하지 않았을 때 할일 // 앱종료 finish();
                    Toast.makeText(applicationContext,"권한이 승인안됨", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

    }


}
