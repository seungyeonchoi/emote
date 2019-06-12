package com.example.emote

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mypage.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Mypage.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Mypage.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Mypage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var uid=""
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
        return inflater.inflate(R.layout.fragment_mypage, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
              super.onActivityCreated(savedInstanceState)
        if(activity!=null){
            val intent=activity!!.intent
            if(intent!=null)
                if(intent.hasExtra("uid"))
                    uid=intent.getStringExtra("uid")
        }

        val myBoardListview=activity!!.findViewById<RecyclerView>(R.id.myBoardListview)
        val layoutManager=LinearLayoutManager(activity)
        Log.v("nnnnnn",layoutManager.toString())
        myBoardListview.layoutManager=layoutManager

        val items=DB().getPostsByQuery(uid) as MutableList<DB.Post>

        val adapter=myBoardAdapter(items)
        Log.v("items","adapter선언")
        myBoardListview.adapter=adapter
        Log.v("items","adapter연결")

        myBoardBtn.setOnClickListener {
            val intent=Intent(activity,SelectEmoteActivity::class.java)
            intent.putExtra("uid",uid)
            Log.i("item","아이디"+uid)
            startActivity(intent)
        }

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
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Mypage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Mypage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
