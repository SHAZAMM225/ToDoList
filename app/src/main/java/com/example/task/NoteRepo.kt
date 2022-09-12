package com.example.task

import androidx.lifecycle.LiveData

class NoteRepo(private val noteDao:NotesDao)    {

    val allNotes:LiveData<List<Note>> =noteDao.getAllNotes()

    suspend fun insert(note: Note) {noteDao.insert(note)}

    suspend fun delete(note: Note) {noteDao.delete(note)}


    suspend fun update(note: Note) {noteDao.update(note)}

}