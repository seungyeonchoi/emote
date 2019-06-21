package com.example.emote;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class boardArrayAdapter extends ArrayAdapter {
    String[] emotion_name = {"happiness","anger","sadness","excited","hmm...","love","terrified","proud","sick","annoyed","lonely","heart fluttering"};
    Context context;
    TypedArray imgArray = getContext().getResources().obtainTypedArray(R.array.drawable);

    public boardArrayAdapter( Context context, int resource, Object[] objects) {
        super(context, resource, objects);
        this.context = context;
        objects = emotion_name;
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
            tv.setText(emotion_name[position] + " board");
        }

        return v;
    }


}
