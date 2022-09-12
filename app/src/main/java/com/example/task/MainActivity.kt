package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface{

lateinit var RecycleNote:RecyclerView
lateinit var add:FloatingActionButton
lateinit var viewModel:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RecycleNote=findViewById(R.id.rv_list)
        add=findViewById(R.id.addNote)

        RecycleNote.layoutManager=LinearLayoutManager(this)

        val noteAdapter=NoteAdapter(this,this,this)
        RecycleNote.adapter=noteAdapter

        viewModel=ViewModelProvider(this,ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application))
            .get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {
            list->
            list?.let {
                noteAdapter.updateList(it)
            }

        })



        add.setOnClickListener {
            val i=Intent(this@MainActivity,AddAct::class.java)
            startActivity(i)
            this.finish()
        }

    }

    override fun onNoteClick(note: Note) {
        val i=Intent(this@MainActivity,AddAct::class.java)
        i.putExtra("noteType","Edit")
        i.putExtra("noteTitle",note.noteTitle)
        i.putExtra("notedescription",note.noteDescription)
        i.putExtra("noteId",note.id)
startActivity(i)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
viewModel.deleteNote(note)
    Toast.makeText(this,"${note.noteTitle} Deleted ",Toast.LENGTH_LONG).show()

    }
}