package com.example.vihaan.testbooknotetaking.ui.questsions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vihaan.testbooknotetaking.R


class QuestionFragment: Fragment(){


    companion object {
        fun newInstance(bundle: Bundle) = QuestionFragment().apply { arguments = bundle }
        val KEY_EDGE = "edge"
        val KEY_SHORT_CODE_MEDIA="shortcode_media";
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        return view
    }


}