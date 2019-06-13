package com.example.emote


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_statistics.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Statistics : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    lateinit var tv:Array<TextView>
    var data1 = intArrayOf(3, 0, 2, 5, 1, 2, 0, 1, 1, 0, 0, 0)
    var data2 = intArrayOf(5, 4, 9, 10, 14, 3, 5, 7, 2, 10, 4, 2)
    var data3 = intArrayOf(24, 12, 43, 52, 12, 15, 4, 20, 5, 17, 6, 7)
    var a_data =
        arrayOf("게임, 공부", "독서, 게임, 운동, 쇼핑, 공부, 맛집탐방, 게임",
            "독서, 게임, 업무, 운동, 쇼핑, 공부, 영화감상, 여행, 맛집탐방, 게임")
    var p_data = arrayOf("새천년관, PC방", "새천년관, PC방, 우리집, 헬스장",
        "새천년관, 스타시티, CGV건대점, 서가앤쿡, PC방, 우리집, 헬스장, 연구실")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        addListener()
    }

    fun addListener() {
        statistics_spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        statistics_graph.setImageResource(R.drawable.graph_1)
                        setData(0)
                        setText(0)
                    }
                    1 -> {
                        statistics_graph.setImageResource(R.drawable.graph_2)
                        setData(1)
                        setText(1)
                    }
                    2 -> {
                        statistics_graph.setImageResource(R.drawable.graph_3)
                        setData(2)
                        setText(2)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

        cbA.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                tvA.setVisibility(VISIBLE)
            else
                tvA.setVisibility(INVISIBLE)
        })

        cbP.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                tvP.setVisibility(VISIBLE)
            else
                tvP.setVisibility(INVISIBLE)
        }
    }

    private fun setText(num: Int) {
        when (num) {
            0 -> tvA.text = a_data[0]
            1 -> tvA.text = a_data[1]
            2 -> tvA.text = a_data[2]
        }

        when (num) {
            0 -> tvP.text = p_data[0]
            1 -> tvP.text = p_data[1]
            2 -> tvP.text = p_data[2]
        }
    }

    private fun setData(p: Int) {
        var data = intArrayOf()
        when (p) {
            0 -> data = data1
            1 -> data = data2
            2 -> data = data3
        }
        for (i in 0..11) {
            tv[i].setText(data[i].toString())
        }
    }

    fun init() {
        tvA.setText(a_data[0])
        tvP.setText(p_data[0])
        tvA.setVisibility(View.INVISIBLE)
        tvP.setVisibility(View.INVISIBLE)
        tv = arrayOf<TextView>(count_1, count_2, count_3, count_4, count_5, count_6, count_7, count_8, count_9, count_10, count_11, count_12)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Statistics().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
    }
}


