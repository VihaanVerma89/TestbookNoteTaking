package com.example.vihaan.testbooknotetaking.ui.noteDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.vihaan.testbooknotetaking.R
import com.example.vihaan.testbooknotetaking.database.AppDatabase
import com.example.vihaan.testbooknotetaking.database.daos.NotesDao
import com.example.vihaan.testbooknotetaking.models.notes.Note
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_new_notes.*

class NoteDetailActivity : AppCompatActivity() {
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
        if(shareIntent())
        {

        }
        else{
            initNoteView()
        }
        initActions()
    }

    private fun initToolbar() {
        setSupportActionBar(toolBar)
        val toolbar = findViewById(R.id.toolBar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_note_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_done -> {
                onSaveBtnClicked()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun shareIntent(): Boolean{
        val action = intent.action
        val type = intent.type
        var sharedIntent: Boolean = false
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                sharedIntent = true
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                sharedIntent = true
                handleSendImage(intent); // Handle single image being sent
            }
        }
        return sharedIntent
    }

    fun handleSendText(intent: Intent) {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (sharedText != null) {
            // Update UI to reflect text being shared
            note = Note()
            note?.text = sharedText
            notesET.setText(note?.text)
        }
    }

    fun handleSendImage(intent: Intent) {
        val imageUri = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri
        if (imageUri != null) {
            // Update UI to reflect image being shared
            note = Note()
            note?.imageUri = imageUri.toString()
            imageUri?.let {
                Glide.with(this)
                        .load(it)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(noteIV)
            }
        }
    }

    var imageUri: Uri? = null
    var note: Note? = null
    private fun initNoteView() {
        var extras = intent.extras
        if (extras != null) {
            if (extras.containsKey(KEY_NOTE)) {
                //existing app note
                note = extras.getParcelable<Note>(KEY_NOTE)
                if (note?.doubt == 1) {
                    doubtTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey_selected)
                }
                if (note?.tricks == 1) {
                    tricksTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey_selected)
                }
                if (note?.concepts == 1) {

                    conceptsTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey_selected)
                }
                imageUri = Uri.parse(note?.imageUri)
                notesET.setText(note?.text)
            }
            else{
                //new note
                note = Note()
                imageUri = UCrop.getOutput(intent);
                note?.imageUri = imageUri.toString()
            }

            imageUri?.let {
                Glide.with(this)
                        .load(it)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(noteIV)
            }
        }

        doubtTagTV.setOnClickListener {
            note?.let {
                if (it.doubt == 1) {
                    it.doubt = 0
                    doubtTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey)
                } else {
                    it.doubt = 1
                    doubtTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey_selected)
                }
            }
        }
        tricksTagTV.setOnClickListener {
            note?.let {
                if (it.tricks == 1) {
                    it.tricks = 0
                    tricksTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey)
                } else {
                    it.tricks = 1
                    tricksTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey_selected)
                }
            }
        }
        conceptsTagTV.setOnClickListener {
            note?.let {
                if (it.concepts == 1) {
                    it.concepts = 0
                    conceptsTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey)
                } else {
                    it.concepts = 1
                    conceptsTagTV.background = ContextCompat.getDrawable(this, R.drawable.bg_grey_selected)
                }
            }
        }
        plusIV.setOnClickListener { }
    }

    private fun initActions() {
//        saveBtn.setOnClickListener { onSaveBtnClicked() }
//        cancelBtn.setOnClickListener { onBackPressed() }
    }

    private fun onSaveBtnClicked() {
        if (note != null && note?.id!=0) {
            note?.text = notesET.text.toString()
            updateNote(note)
        } else {
//            var note = Note()
            note?.text = notesET.text.toString()
            imageUri?.let {
                note?.imageUri = it.toString()
            }
            saveNote(this.note)
        }
        finish()
    }

    private lateinit var notesDao: NotesDao
    private fun saveNote(note: Note?) {
        note?.timeStamp = System.currentTimeMillis()
        note?.let {
            notesDao = AppDatabase.getInstance(this).notesDao()
            notesDao.insertNote(note)
        }
    }

    private fun updateNote(note: Note?) {
        if (note != null) {
            note.timeStamp = System.currentTimeMillis()
            notesDao = AppDatabase.getInstance(this).notesDao()
            notesDao.updateNote(note)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}