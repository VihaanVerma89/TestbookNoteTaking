package com.example.vihaan.testbooknotetaking.ui.questsions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vihaan.testbooknotetaking.R


class QuestionFragment1: Fragment(){

    companion object {
        fun newInstance(bundle: Bundle) = QuestionFragment1().apply { arguments = bundle }
        var tags = arrayListOf<String>("SSC", "Logical Reasoning","Non verbal reasoning","Cubes and dice", "Problems on dice")
        val KEY_POSITION ="position"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val position= arguments?.getInt(KEY_POSITION)
        val layout = ""
        val view = inflater.inflate(R.layout.fragment_question_1, container, false)
        return view
    }


}