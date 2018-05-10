package com.example.vihaan.testbooknotetaking.ui.newNotes

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.vihaan.testbooknotetaking.R
import com.example.vihaan.testbooknotetaking.database.AppDatabase
import com.example.vihaan.testbooknotetaking.database.daos.NotesDao
import com.example.vihaan.testbooknotetaking.models.Note
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_new_notes.*

class NewNotesActivity : AppCompatActivity() {
    companion object {
        val KEY_NOTE = "note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notes)
        init()
    }


    private fun init() {
        initToolbar()
        initNoteView()
        initActions()
    }

    private fun initToolbar() {
        setSupportActionBar(toolBar)
        val toolbar = findViewById(R.id.toolBar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    var imageUri: Uri? = null
    private fun initNoteView() {
        var extras = intent.extras
        if (extras.containsKey(KEY_NOTE)) {

        }

        imageUri = UCrop.getOutput(intent);
        imageUri?.let {
            Glide.with(this)
                    .load(it)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(noteIV)
        }
    }

    private fun initActions() {
        saveBtn.setOnClickListener { onSaveBtnClicked() }
        cancelBtn.setOnClickListener { onBackPressed() }
    }

    private fun onSaveBtnClicked() {
        var note = Note()
        note.text = notesET.text.toString()
        imageUri?.let {
            note.imageUri = it.toString()
        }
        saveNote(note)
        finish()
    }

    private lateinit var notesDao: NotesDao
    private fun saveNote(note: Note) {
        notesDao= AppDatabase.getInstance(this).notesDao()
        notesDao.insertArticle(note)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}