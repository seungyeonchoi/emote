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
        p0.title.text="슬픔"
        p0.contents.text="사실 우승한건 토트넘이 아니라 리버풀이었당 나리는 2만원을 뜯기고 말았당"

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

