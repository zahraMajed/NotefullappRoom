package com.example.notefulapproom

import androidx.room.*

//step 5: create Dao interface (Data Access Objects interface)
//this interface is used to access database in better and modular way as compared to query builders or direct queries.

// 1) in this Dao interface you should use @Dao annotation to define the interface as Dao
@Dao
interface NotesDao {
    //2) define your methods to interact with the data in your app's database.

    //method to get all data:
    @Query("SELECT * FROM Notes")
    fun getAll(): List<NotesTable>

    //insert a row into the table
    @Insert
    fun insertNote(note:NotesTable)

    //update note
    @Update
    fun updateNote(note: NotesTable)

    //delete note row
    @Delete
    fun delNote(noteid: NotesTable)


    /*
    In the initial SQLite, we use the Cursor objects.
     With Room, we don’t need all the Cursor related code
     */
}