package com.example.vihaan.testbooknotetaking.ui.newNotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.vihaan.testbooknotetaking.R
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_new_notes.*

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
        setSupportActionBar(toolBar)
        val toolbar = findViewById(R.id.toolBar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    private fun initNoteView(){
        var extras = intent.extras
        if(extras.containsKey(KEY_NOTE))
        {

        }

        val uri = UCrop.getOutput(intent);
        Glide.with(this)
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(noteIV)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}