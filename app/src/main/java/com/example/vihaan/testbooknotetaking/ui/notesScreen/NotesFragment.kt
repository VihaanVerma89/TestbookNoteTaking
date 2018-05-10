package com.example.vihaan.testbooknotetaking.ui.notesScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vihaan.testbooknotetaking.R

class NotesFragment: Fragment(){


    companion object {
        fun newInstance(bundle: Bundle) = NotesFragment().apply { arguments = bundle }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_notes, container, false);
        return view;
    }
}