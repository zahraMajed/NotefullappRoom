package com.example.notefulapproom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//step 4: create your database table data class (Room entity)
/* Entity represents a table within the database.
Room creates a table for each class that has @Entity annotation*/

// 1) in this data class you should use @Entity annotation to define a table with a name
@Entity(tableName = "Notes")
data class NotesTable(
    // 2) then you should use define the structure of the table by creating column and define a PK
   /* Each Room entity must define a primary key
    that uniquely identifies each row in the corresponding database table.*/

    //use @PrimaryKey annotation to annotate a single column as a PK
    //use @ColumnInfo annotation allows specifying custom information about column
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id : Int = 0,

    @ColumnInfo(name = "Note")
    val note: String

    //so here in Notes table we have two column (ID and Note)
)
