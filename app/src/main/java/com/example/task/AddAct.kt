package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddAct : AppCompatActivity() {
    lateinit var title:EditText
    lateinit var desc:EditText
    lateinit var add:Button
    lateinit var viewModel:NoteViewModel
    var noteId=-1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        title=findViewById(R.id.idEditTitle)
        desc=findViewById(R.id.idEditDesc)
        add=findViewById(R.id.idEditBtn)

        viewModel=ViewModelProvider(this,ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            ).get(NoteViewModel::class.java)

        val noteType=intent.getStringExtra("noteType")

        if ( noteType.equals("Edit"))
        {//update
val nTitle=intent.getStringExtra("noteTitle")
            val ndesc=intent.getStringExtra("notedescription")
            noteId=intent.getIntExtra("noteId",-1)

            add.setText("Update")
            title.setText(nTitle)
            desc.setText(ndesc)
        }else
        {
            //insert
            add.setText("Save ")

        }

        add.setOnClickListener {
            val nTitle=title.text.toString()
            val descr=desc.text.toString()

            if ( noteType.equals("Edit"))
            {
                val date=SimpleDateFormat("dd MMM,yyy -HH:mm")
                val curentDate=date.format(Date())
                val updateNote=Note(nTitle,descr,curentDate)
                updateNote.id=noteId
                viewModel.updateNote(updateNote)

                Toast.makeText(this,"updated successfully !",Toast.LENGTH_LONG).show()
            }else{
                if ( nTitle.isNotEmpty() && descr.isNotEmpty())
                {
                    val date=SimpleDateFormat("dd MMM,yyy -HH:mm")
                    val curentDate=date.format(Date())
                    viewModel.addNote(Note(nTitle,descr,curentDate))
                    Toast.makeText(this,"Note Added :)",Toast.LENGTH_LONG).show()

                }


            }

startActivity(Intent(applicationContext,MainActivity::class.java))

this.finish()
        }

    }





    }

