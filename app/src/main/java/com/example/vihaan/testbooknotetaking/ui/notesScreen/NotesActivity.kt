package com.example.vihaan.testbooknotetaking.ui.notesScreen

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import android.widget.Toast
import com.example.vihaan.testbooknotetaking.R
import com.example.vihaan.testbooknotetaking.models.notes.Note
import com.example.vihaan.testbooknotetaking.models.notes.Tabs
import com.example.vihaan.testbooknotetaking.ui.noteDetail.NoteDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_notes.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions()
class NotesActivity: AppCompatActivity(), NotesAdapter.NotesLitener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        initViews()
        loadNotesWithPermissionCheck()
    }

    private fun initViews(){
        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar(){
        setSupportActionBar(toolBar)
        val toolbar = findViewById(R.id.toolBar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    private lateinit var adapter: NotesAdapter
    private var items = arrayListOf<Any>()
    private fun initRecyclerView() {
        adapter = NotesAdapter(this,items, this)
        notesRV.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        notesRV.adapter = adapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
     fun loadNotes(){
        NotesRepo.getInstance(this).getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.size > 0)
                    {
                       showNotes(it)
                    }
                    else{
                        showNoNotes()
                    }

                },{


                })
    }



    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun loadImagesDenied(){
        Toast.makeText(this, "Please allow access to read files", Toast.LENGTH_LONG).show()
    }

    private fun showNotes(notes: List<Note>)
    {
        items.clear()
        items.add(Tabs())
        items.addAll(notes)
        adapter.notifyDataSetChanged()
    }

    private fun showNoNotes(){

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onNoteClicked(note: Note) {
        var intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.KEY_NOTE, note)
        startActivity(intent)
    }
}