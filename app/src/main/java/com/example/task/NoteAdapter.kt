package com.example.task

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import kotlinx.coroutines.awaitAll

class NoteAdapter(
    val context: Context,
    val noteClickInterface:NoteClickInterface,
    val noteClickDelete:NoteClickDeleteInterface
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val allNotes=ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val note = itemView.findViewById<TextView>(R.id.idTitle)
        val time=itemView.findViewById<TextView>(R.id.idTime)
        val deleteBtn=itemView.findViewById<ImageView>(R.id.idDelete)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val view=LayoutInflater.from(context)
    .inflate(R.layout.list_item_item,
    parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.note.setText(allNotes.get(position).noteTitle )
    holder.time.setText("Last Update : "+allNotes.get(position).noteTime)

        holder.deleteBtn.setOnClickListener{

            noteClickDelete.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()

    }

}
interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)

}

interface NoteClickInterface{
    fun onNoteClick(note:Note)
}

