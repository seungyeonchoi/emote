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


    }

