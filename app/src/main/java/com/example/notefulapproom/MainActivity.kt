package com.example.notefulapproom

import android.content.DialogInterface
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    //my views
    lateinit var edNotes: EditText
    lateinit var btnSumbmit: Button

    //my variables
    //lateinit var NotesList: ArrayList<List<Any>>

    //step 7: Managing Data
    // 1) create an instance of the database.
    lateinit var NotesDB:NotesDatabase
    // 2) call Dao method that you need

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotesDB= NotesDatabase.getInstance(this)
        edNotes=findViewById(R.id.edNots)
        btnSumbmit=findViewById(R.id.btnSubmit)

        //getNote()

        btnSumbmit.setOnClickListener(){
            if(edNotes.text.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    // 2) call Dao method that you need (here insertNote)
                    NotesDB.getNotesDao().insertNote(NotesTable(0,edNotes.text.toString()))
                    getNote()
                }
                Toast.makeText(applicationContext, "data saved successfully!", Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(applicationContext, "please fill the missing entry!", Toast.LENGTH_SHORT).show()
            edNotes.text.clear()
        }//end btnSum listener



    }//end onCreate()

    fun getNote(){
        CoroutineScope(Dispatchers.IO).launch{
            withContext(Dispatchers.Main){
                //2) call Dao method that you need (here insertNote)
                rv_main.adapter=RecycelerAdapter(this@MainActivity,NotesDB.getNotesDao().getAll())
                rv_main.layoutManager=LinearLayoutManager(this@MainActivity)
            }
        }
    }//end getNote()

    fun updataNote(notesTable: NotesTable){
        val alert=AlertDialog.Builder(this)
        alert.setTitle("update note")
        alert.setMessage("Enter your updated note below ")

        val editNote=EditText(this)
        editNote.setText(notesTable.note)
        alert.setView(editNote)

        alert.setNegativeButton("Save" ,DialogInterface.OnClickListener(){
            dialog, which ->
            if (editNote.text.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    NotesDB.getNotesDao().updateNote(NotesTable(notesTable.id, editNote.text.toString()))
                    getNote()
                }
            }
        })//end setNegativeButton

        alert.setPositiveButton("Cancel" ,DialogInterface.OnClickListener(){
                dialog, which -> dialog.cancel()
        })//end positiveButton

        alert.create().show()
    }

    fun delNote(notesTable: NotesTable){
        CoroutineScope(Dispatchers.IO).launch {
            NotesDB.getNotesDao().delNote(NotesTable(notesTable.id,notesTable.note))
            getNote()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            getNote()
            Toast.makeText(applicationContext, "up-to-data", Toast.LENGTH_SHORT).show()
            }
    }//end onConfigurationChanged()


}//end class