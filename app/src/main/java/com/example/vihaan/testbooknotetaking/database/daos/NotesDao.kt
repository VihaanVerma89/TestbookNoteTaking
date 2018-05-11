package com.example.vihaan.testbooknotetaking.database.daos

import android.arch.persistence.room.*
import com.example.vihaan.testbooknotetaking.models.notes.Note
import io.reactivex.Flowable

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): Flowable<MutableList<Note>>

    @Query("SELECT count(*) FROM notes where doubt=1")
    fun getDoubtCount(): Int

    @Query("SELECT count(*) FROM notes where tricks=1")
    fun getTricksCount(): Int

    @Query("SELECT count(*) FROM notes where concepts=1")
    fun getConceptCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}