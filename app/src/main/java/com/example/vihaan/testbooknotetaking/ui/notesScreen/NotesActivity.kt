package com.example.vihaan.testbooknotetaking.ui.notesScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import com.example.vihaan.testbooknotetaking.R
import com.example.vihaan.testbooknotetaking.models.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity: AppCompatActivity(), NotesAdapter.NotesLitener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        initViews()
    }

    private fun initViews(){
        initToolbar()
        initRecyclerView()
        loadNotes()
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

    private fun loadNotes(){
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

    private fun showNotes(notes: List<Note>)
    {
        items.clear()
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

    }
}