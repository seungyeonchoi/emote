package com.example.emote

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class myBoardAdapter:RecyclerView.Adapter<myBoardAdapter.ViewHolder>() {

    val db=DB()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myBoardAdapter.ViewHolder {
        val v=LayoutInflater.from(p0.context).inflate(R.layout.board_card,p0,false)
        Log.i("doing","boardAdapter진입")
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(p0: myBoardAdapter.ViewHolder, p1: Int) {
        /*
        post array에서 받아와서
         p0.title.text=postArrays.get(p1).title
         p0.contents.text=postArrays.get(p1).contents

        */
    //  val str=db.getPosts()!!.get(0).contents
    //    p0.contents.text=str
        p0.title.text="챔스 결승"
        p0.contents.text="리버풀이 우승할 거라고 생각했었는데 어떤 나쁜 놈이 토트넘에 돈을 걸라고 했다 \n 그리고 리버풀이 2:0으로 이겨버렸다 결국 나는 2만원을 그렇게 뜯기고 말았다 마음이 아팠다"

    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var title:TextView
        var contents:TextView
        init{
            title=itemView.findViewById(R.id.mbCardTitle)
            contents=itemView.findViewById(R.id.mbCardContents)
        }

    }
}

