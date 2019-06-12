package com.example.emote


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Statistics : Fragment() {

    interface OnFragmentInteractionListener{

    }
    var emoArrSize:Int = 5
    var emoMaxSize:Int = 3
    var emotionArr = Array<Array<String>>(emoArrSize){Array<String>(emoMaxSize){""}} // 5일간의 감정 정보 3개
    var emotionRatio = Array<Array<Int>>(emoArrSize){Array<Int>(emoMaxSize){0}} // 5일간의 감정 정보들의 퍼센테이지
    var emotionScore = Array<Int>(emoArrSize){100}    // 5일간의 감정 점수
    var topEmoArr = Array<String>(emoArrSize){""}   // 날짜 별로 퍼센트가 제일 높은 감정
//    var emoView = arrayOf(
//        stat_emo1,stat_emo2,stat_emo3,stat_emo4,stat_emo5
//    )

//******************감정 정보 불러오기*********************************

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        calculateEmo()
        initLayout()
    }

    fun initLayout(){
        for(i in R.id.checkbox01..R.id.checkbox03){//장소, 인물, 활동
            val checkbox=activity!!.findViewById<CheckBox>(i)
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    //체크되면 무엇을 하면 좋을까요????
                }
            }
        }
    }
    fun calculateEmo(){
        for(i in 0 until emoArrSize){
            var max:Int = 0
            //var topEmo:String = ""
            for(j in 0 until emoMaxSize) {
                emotionScore[i] += when (emotionArr[i][j]) {
                    "" -> emotionRatio[i][j]    // 긍정적인 감정
                    else -> -emotionRatio[i][j] // 부정적인 감정
                }
                if (emotionRatio[i][j] > max) {
                    topEmoArr[i] = emotionArr[i][j]
                    max = emotionRatio[i][j]
                }
            }
            /*
            for(i in R.id.stat_emo1 .. R.id.stat_emo5){
                val imageView=activity!!.findViewById<ImageView>(i)
                imageView.setImageResource(R.drawable.icon_1_b)
            }*/
                //topEmoArr별로 이모티콘 세팅
//            for(i in R.id.stat_emo1..R.id.stat_emo5){
//                val imageView=activity!!.findViewById<ImageView>(i)
//                var index=0
//                when(topEmoArr[index]){
//                    "hmm"->{imageView.setImageResource(R.drawable.hmm_icon)}
//                    "anger"->{imageView.setImageResource(R.drawable.anger_icon)}
//                    "dugeun"->{imageView.setImageResource(R.drawable.dugeun_icon)}
//                    "excited"->{imageView.setImageResource(R.drawable.excited_icon)}
//                    "happiness"->{imageView.setImageResource(R.drawable.happiness_icon)}
//                    "love"->{imageView.setImageResource(R.drawable.love_icon)}
//                    "lonely"->{imageView.setImageResource(R.drawable.lonely_icon)}
//                    "proud"->{imageView.setImageResource(R.drawable.proud_icon)}
//                    "sad"->{imageView.setImageResource(R.drawable.sadness_icon)}
//                    "terrified"->{imageView.setImageResource(R.drawable.terrified_icon)}
//                    "annoyed"->{imageView.setImageResource(R.drawable.annoyed_icon)}
//                }
//
//            }

        }

        for(i in 0..3){
            // emotionScore[i]부터 emotionScore[i+1]까지 선 긋기
        }


    }
}
