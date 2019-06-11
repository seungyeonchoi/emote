package com.example.emote

import android.opengl.Visibility
import android.provider.ContactsContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.board_card.view.*
import org.intellij.lang.annotations.Identifier
import org.w3c.dom.Text

class myBoardAdapter(val items:MutableList<DB.Post>)
    :RecyclerView.Adapter<myBoardAdapter.ViewHolder>() {

    val db = DB()
    val image = arrayOf(
        R.drawable.happiness_icon,
        R.drawable.anger_icon,
        R.drawable.sadness_icon,
        R.drawable.excited_icon,
        R.id.hmm_icon,
        R.drawable.love_icon,
        R.drawable.terrified_icon,
        R.drawable.proud_icon,
        R.drawable.sick_icon,
        R.drawable.annoyed_icon,
        R.drawable.lonely_icon,
        R.drawable.dugeun_icon
    )


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.board_card, p0, false)
        Log.i("doing", "boardAdapter진입")
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        Log.i("doingAdapter",items.size.toString())
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val imageView = arrayOf(

            p0.mbCardEmote1,
            p0.mbCardEmote1_Stat,
            p0.mbCardEmote2,
            p0.mbCardEmote2_Stat,
            p0.mbCardEmote3,
            p0.mbCardEmote3_Stat
        )
/*
        p0.title.text = "챔스 결승"
        p0.contents.text ="리버풀이 우승할 거라고 생각했었는데 어떤 나쁜 놈이 토트넘에 돈을 걸라고 했다 \n 그리고 리버풀이 2:0으로 이겨버렸다 결국 나는 2만원을 그렇게 뜯기고 말았다 마음이 아팠다"
   */
        p0.title.text=items.get(p1).title
        //userid에 맞는 post list가 items 입니다.t=items.get(p1).title
        p0.contents.text=items.get(p1).contents
        p0.mbCardTime.text=items.get(p1).date

        //val emotion index=items.get(p1).pid.toInt()
        try{
         val emtListforPost = db.getEmotion(items.get(p1).pid.toInt()) as List<DB.Emotion>

        Log.v("emotionlist3",emtListforPost!!.size.toString())

        for(i in 0..emtListforPost.size-1){
             Log.v("doingadapter",i.toString())
            val v=imageView.get(2*i) as ImageView
            v.setImageResource(image.get(emtListforPost.get(i).eid.toInt()))
            Log.v("doingadaptersetImage",emtListforPost.get(i).eid)
            val s=imageView.get(2*i+1) as TextView
            s.text=emtListforPost.get(i).percent
            Log.v("doingadaptersetText",emtListforPost.get(i).percent)

        }
        }
        catch(e : Exception){
            Log.d("error", e.message);
        }




    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var contents: TextView
        var mbCardTime:TextView
        var trashBtn: ImageView
        var mbCardEmote1: ImageView
        var mbCardEmote1_Stat: TextView
        var mbCardEmote2: ImageView
        var mbCardEmote2_Stat: TextView
        var mbCardEmote3: ImageView
        var mbCardEmote3_Stat: TextView

        init {
            title = itemView.findViewById(R.id.mbCardTitle)
            contents = itemView.findViewById(R.id.mbCardContents)
            mbCardTime=itemView.findViewById(R.id.mbCardTime)
            mbCardEmote1 = itemView.findViewById(R.id.mbCardEmote1)
            mbCardEmote1_Stat = itemView.findViewById(R.id.mbCardEmote2_Stat)
            mbCardEmote2 = itemView.findViewById(R.id.mbCardEmote2)
            mbCardEmote2_Stat = itemView.findViewById(R.id.mbCardEmote2_Stat)
            mbCardEmote3 = itemView.findViewById(R.id.mbCardEmote3)
            mbCardEmote3_Stat = itemView.findViewById(R.id.mbCardEmote3_Stat)
            trashBtn = itemView.findViewById(R.id.trash_btn)
            trashBtn.setOnClickListener {
                var index=0
                for( i in 0..items.size){
                    if(items.get(i).title==itemView.mbCardTitle.text) {
                        index = i
                        break
                    }
                }
                items.remove(items.get(index))
                notifyDataSetChanged()

            }

        }

    }

}

