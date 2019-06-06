package com.example.emote

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class myBoardAdapter:RecyclerView.Adapter<myBoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myBoardAdapter.ViewHolder {
        val v=LayoutInflater.from(p0.context).inflate(R.layout.board_card,p0,false)
        Log.i("doing","boardAdapter진입")
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
            return 1
    }

    override fun onBindViewHolder(p0: myBoardAdapter.ViewHolder, p1: Int) {
       p0.title.text="오늘기분짱"
        p0.contents.text="토트넘이 챔스 우승해서 기분이 최고당"
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