package com.example.vihaan.testbooknotetaking.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.vihaan.testbooknotetaking.database.daos.NotesDao
import com.example.vihaan.testbooknotetaking.models.notes.Note

/**
 * Created by vihaan on 3/27/18.
 */

@Database(entities = arrayOf(Note::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context)
                            .also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "app.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
    }
}