package com.example.vihaan.testbooknotetaking.ui.notesScreen

import android.content.Context
import com.example.vihaan.testbooknotetaking.database.AppDatabase
import com.example.vihaan.testbooknotetaking.database.daos.NotesDao
import com.example.vihaan.testbooknotetaking.models.notes.Note
import io.reactivex.Flowable

class NotesRepo private constructor(val context: Context){

    private var notesDao:NotesDao
    companion object {
        @Volatile
        private var INSTANCE: NotesRepo? = null

        fun getInstance(context: Context): NotesRepo =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: NotesRepo(context)
                }
    }

    init {
        notesDao = AppDatabase.getInstance(context).notesDao()
    }

    fun getNotes(): Flowable<MutableList<Note>> {

        return notesDao.getNotes()
    }

}