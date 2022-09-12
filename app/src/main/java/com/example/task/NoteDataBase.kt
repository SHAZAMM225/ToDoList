package com.example.task

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDataBase:RoomDatabase() {

    abstract fun getNotesDao():NotesDao


    companion object{

        @Volatile
        private var Ins:NoteDataBase?=null

        fun getDatabase(context: Context):NoteDataBase{

     return Ins ?: synchronized(this)
     {
         val instance=Room.databaseBuilder(context.applicationContext,NoteDataBase::class.java,"note-database").build()
         Ins=instance
         instance

     }

        }

    }

}