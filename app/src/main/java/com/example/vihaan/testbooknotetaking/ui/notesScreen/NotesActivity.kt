package com.example.vihaan.testbooknotetaking.ui.notesScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.vihaan.testbooknotetaking.R
import kotlinx.android.synthetic.main.app_bar_main.*

class NotesActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        initViews()
    }

    private fun initViews(){
        initToolbar()
    }

    private fun initToolbar(){
        setSupportActionBar(toolBar)
        val toolbar = findViewById(R.id.toolBar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}