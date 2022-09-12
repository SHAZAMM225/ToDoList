package com.example.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notesTable")
class Note(
    @ColumnInfo(name="title")
    val noteTitle:String,
    @ColumnInfo(name="desc")

    val noteDescription:String,
    @ColumnInfo(name="time")

    val noteTime:String)

{

   @PrimaryKey(autoGenerate = true)
   var id=0
}