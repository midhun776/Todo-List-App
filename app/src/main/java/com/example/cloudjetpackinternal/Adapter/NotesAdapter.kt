package com.example.cloudjetpackinternal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudjetpackinternal.Models.NoteItemModel
import com.example.cloudjetpackinternal.R

class NotesAdapter(private val data: List<NoteItemModel>): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView = itemView.findViewById(R.id.textView4)
        val tv_note: TextView = itemView.findViewById(R.id.textView5)
        val tv_date: TextView = itemView.findViewById(R.id.textView2)
        val tv_time: TextView = itemView.findViewById(R.id.textView3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.tv_title.text = item.title
        holder.tv_note.text = item.note
        holder.tv_date.text = item.date
        holder.tv_time.text = item.time
    }
}