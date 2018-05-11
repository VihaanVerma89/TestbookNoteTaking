package com.example.vihaan.testbooknotetaking.ui.notesScreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.vihaan.testbooknotetaking.R
import com.example.vihaan.testbooknotetaking.models.notes.Note
import com.example.vihaan.testbooknotetaking.models.notes.Tabs

class  NotesAdapter(val context: Context,
                    val items:ArrayList<Any>,
                    val notesLitener: NotesLitener
): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    val ITEM_NOTE= 0
    var ITEM_TABS = 1

    interface NotesLitener{
        fun onNoteClicked(note: Note)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent?.getContext())
        val view: View

        when (viewType)
        {
            ITEM_NOTE ->{
                view = inflater.inflate(R.layout.list_item_note, parent, false)
               viewHolder = NoteViewHolder(view)
            }

            ITEM_TABS->{
                view = inflater.inflate(R.layout.list_item_tabs, parent, false)
                viewHolder = TabsViewHolder(view)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if(holder is NoteViewHolder)
        {
            bindNoteViewHolder(holder, position, payloads)
        }
    }


    fun bindNoteViewHolder(holder: NoteViewHolder, position: Int, items: MutableList<Any>)
    {
        holder as NoteViewHolder
        val note = this.items.get(position) as Note
        Glide.with(context)
                .load(note.imageUri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.noteIV)

        holder.noteTV.text = note.text
        holder.itemView.setOnClickListener{
            notesLitener.onNoteClicked(note)
        }
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        val item = items.get(position)
        if(item is Tabs)
        {
            return ITEM_TABS
        }
        return ITEM_NOTE
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var noteIV = itemView.findViewById<ImageView>(R.id.noteIV)
        var noteTV = itemView.findViewById<TextView>(R.id.noteTV)
    }

    class TabsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {

    }
}