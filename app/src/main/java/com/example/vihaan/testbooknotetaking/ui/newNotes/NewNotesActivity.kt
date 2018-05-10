package com.example.vihaan.testbooknotetaking.ui.newNotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.vihaan.testbooknotetaking.R
import kotlinx.android.synthetic.main.app_bar_main.*

class NewNotesActivity: AppCompatActivity(){
    companion object {
        val KEY_NOTE = "note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notes)
        init()
    }


    private fun init(){
        initToolbar()
        initNoteView()
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    private fun initNoteView(){
        intent.extras
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}