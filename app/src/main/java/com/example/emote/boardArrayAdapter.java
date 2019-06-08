package com.example.emote;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class boardArrayAdapter extends ArrayAdapter {
    String[] emotion_name = {"기쁨", "화남", "슬픔", "신남", "애매", "사랑", "놀람", "뿌듯", "아픔", "짜증", "외롭", "설렘"};
    Context context;
    TypedArray imgArray = getContext().getResources().obtainTypedArray(R.array.drawable);

    public boardArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_custom, null);
        }

        ImageView iv = (ImageView)v.findViewById(R.id.list_emote_icon);
        TextView tv = (TextView)v.findViewById(R.id.list_board_name);
        if (iv != null) {
            iv.setImageResource(imgArray.getResourceId(position, -1));
        }
        if (tv != null) {
            tv.setText(emotion_name[position] + " 게시판");
        }

        return v;
    }


}
