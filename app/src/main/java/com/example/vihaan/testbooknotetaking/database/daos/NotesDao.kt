package com.example.vihaan.testbooknotetaking.database.daos

import android.arch.persistence.room.*
import com.example.vihaan.testbooknotetaking.models.Note
import io.reactivex.Flowable

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): Flowable<MutableList<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}