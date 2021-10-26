package com.example.notefulapproom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class RecycelerAdapter (val activity: MainActivity, val NoteList:List<NotesTable>): RecyclerView.Adapter<RecycelerAdapter.itemViewHolder>() {
    class itemViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,parent,false
            ))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val noteObject=NoteList[position]
        var id= NoteList[position].id
        var note=NoteList[position].note

        holder.itemView.apply {
            tvNoteNum.text="Note $id: "
            tvNote.text=note

            EditActionButton.setOnClickListener(){
                activity.updataNote(noteObject)
            }

            DelActionButton.setOnClickListener(){
                activity.delNote(noteObject)
            }

        }//end holder
    }//end onBindViewHolder

    override fun getItemCount(): Int= NoteList.size

}