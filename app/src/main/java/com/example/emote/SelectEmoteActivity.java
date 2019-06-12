package com.example.emote;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class SelectEmoteActivity extends AppCompatActivity {
    ImageView[] emoteArray;
    int[] emoteStatArray;
    ImageButton nextBtn;
    TypedArray defaultArray;
    TypedArray selectedArray;
    int count;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_emote);
        Intent intent = getIntent();
        uid=intent.getStringExtra("uid");
        init();
    }

    private void init() {
        emoteArray = new ImageView[12];
        emoteStatArray = new int[12];
        emoteArray[0] = (ImageView)findViewById(R.id.happiness_icon);
        emoteArray[1] = (ImageView)findViewById(R.id.anger_icon);
        emoteArray[2] = (ImageView)findViewById(R.id.sadness_icon);
        emoteArray[3] = (ImageView)findViewById(R.id.excited_icon);
        emoteArray[4] = (ImageView)findViewById(R.id.hmm_icon);
        emoteArray[5] = (ImageView)findViewById(R.id.love_icon);
        emoteArray[6] = (ImageView)findViewById(R.id.terrified_icon);
        emoteArray[7] = (ImageView)findViewById(R.id.proud_icon);
        emoteArray[8] = (ImageView)findViewById(R.id.sick_icon);
        emoteArray[9] = (ImageView)findViewById(R.id.annoyed_icon);
        emoteArray[10] = (ImageView)findViewById(R.id.lonely_icon);
        emoteArray[11] = (ImageView)findViewById(R.id.dugeun_icon);

        //emotestat배열 초기화, 0은 선택 안된 상태 1은 선택 된 상태
        for (int i=0; i<12; i++) {
            emoteStatArray[i] = 0;
        }
        defaultArray = getApplicationContext().getResources().obtainTypedArray(R.array.drawable);
        selectedArray = getApplicationContext().getResources().obtainTypedArray(R.array.drawable2);
        nextBtn = (ImageButton)findViewById(R.id.next_btn);

        addListener();
        addbtnListener();
    }

    private void addbtnListener() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>3) {
                    Toast.makeText(getApplicationContext(), "3개 이하의 감정을 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
                else if (count == 0) {
                    Toast.makeText(getApplicationContext(), "1개 이상의 감정을 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                    intent.putExtra("uid",uid);
                    int mCount=1;
                    for (int i=0; i<12; i++){
                        if(emoteStatArray[i]==1){
                            String tag="emotion"+String.valueOf(mCount);
                            intent.putExtra(tag, i);
                            mCount++;
                            Log.i("record",tag);
                        }
                    }
                    startActivity(intent);
                }
            }
        });
    }

    private void addListener() {

        emoteArray[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[0] == 0) {
                    emoteArray[0].setImageResource(selectedArray.getResourceId(0, -1));
                    emoteStatArray[0] = 1;
                    count++;
                }
                else {
                    emoteArray[0].setImageResource(defaultArray.getResourceId(0, -1));
                    emoteStatArray[0] = 0;
                    count--;
                }
            }
        });

        emoteArray[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[1] == 0) {
                    emoteArray[1].setImageResource(selectedArray.getResourceId(1, -1));
                    emoteStatArray[1] = 1;
                    count++;
                }
                else {
                    emoteArray[1].setImageResource(defaultArray.getResourceId(1, -1));
                    emoteStatArray[1] = 0;
                    count--;
                }
            }
        });

        emoteArray[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[2] == 0) {
                    emoteArray[2].setImageResource(selectedArray.getResourceId(2, -1));
                    emoteStatArray[2] = 1;
                    count++;
                }
                else {
                    emoteArray[2].setImageResource(defaultArray.getResourceId(2, -1));
                    emoteStatArray[2] = 0;
                    count--;
                }
            }
        });

        emoteArray[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[3] == 0) {
                    emoteArray[3].setImageResource(selectedArray.getResourceId(3, -1));
                    emoteStatArray[3] = 1;
                    count++;
                }
                else {
                    emoteArray[3].setImageResource(defaultArray.getResourceId(3, -1));
                    emoteStatArray[3] = 0;
                    count--;
                }
            }
        });

        emoteArray[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[4] == 0) {
                    emoteArray[4].setImageResource(selectedArray.getResourceId(4, -1));
                    emoteStatArray[4] = 1;
                    count++;
                }
                else {
                    emoteArray[4].setImageResource(defaultArray.getResourceId(4, -1));
                    emoteStatArray[4] = 0;
                    count--;
                }
            }
        });

        emoteArray[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[5] == 0) {
                    emoteArray[5].setImageResource(selectedArray.getResourceId(5, -1));
                    emoteStatArray[5] = 1;
                    count++;
                }
                else {
                    emoteArray[5].setImageResource(defaultArray.getResourceId(5, -1));
                    emoteStatArray[5] = 0;
                    count--;
                }
            }
        });

        emoteArray[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[6] == 0) {
                    emoteArray[6].setImageResource(selectedArray.getResourceId(6, -1));
                    emoteStatArray[6] = 1;
                    count++;
                }
                else {
                    emoteArray[6].setImageResource(defaultArray.getResourceId(6, -1));
                    emoteStatArray[6] = 0;
                    count--;
                }
            }
        });

        emoteArray[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[7] == 0) {
                    emoteArray[7].setImageResource(selectedArray.getResourceId(7, -1));
                    emoteStatArray[7] = 1;
                    count++;
                }
                else {
                    emoteArray[7].setImageResource(defaultArray.getResourceId(7, -1));
                    emoteStatArray[7] = 0;
                    count--;
                }
            }
        });

        emoteArray[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[8] == 0) {
                    emoteArray[8].setImageResource(selectedArray.getResourceId(8, -1));
                    emoteStatArray[8] = 1;
                    count++;
                }
                else {
                    emoteArray[8].setImageResource(defaultArray.getResourceId(8, -1));
                    emoteStatArray[8] = 0;
                    count--;
                }
            }
        });

        emoteArray[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[9] == 0) {
                    emoteArray[9].setImageResource(selectedArray.getResourceId(9, -1));
                    emoteStatArray[9] = 1;
                    count++;
                }
                else {
                    emoteArray[9].setImageResource(defaultArray.getResourceId(9, -1));
                    emoteStatArray[9] = 0;
                    count--;
                }
            }
        });

        emoteArray[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[10] == 0) {
                    emoteArray[10].setImageResource(selectedArray.getResourceId(10, -1));
                    emoteStatArray[10] = 1;
                    count++;
                }
                else {
                    emoteArray[10].setImageResource(defaultArray.getResourceId(10, -1));
                    emoteStatArray[10] = 0;
                    count--;
                }
            }
        });

        emoteArray[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emoteStatArray[11] == 0) {
                    emoteArray[11].setImageResource(selectedArray.getResourceId(11, -1));
                    emoteStatArray[11] = 1;
                    count++;
                }
                else {
                    emoteArray[11].setImageResource(defaultArray.getResourceId(11, -1));
                    emoteStatArray[11] = 0;
                    count--;
                }
            }
        });

    }

}
