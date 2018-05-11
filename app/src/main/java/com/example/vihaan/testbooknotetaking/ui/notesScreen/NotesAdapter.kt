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
import com.example.vihaan.testbooknotetaking.database.AppDatabase
import com.example.vihaan.testbooknotetaking.models.notes.Note
import com.example.vihaan.testbooknotetaking.models.notes.Tabs

class NotesAdapter(val context: Context,
                   val items: ArrayList<Any>,
                   val notesLitener: NotesLitener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_NOTE = 0
    var ITEM_TABS = 1

    interface NotesLitener {
        fun onNoteClicked(note: Note)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent?.getContext())
        val view: View

        when (viewType) {
            ITEM_NOTE -> {
                view = inflater.inflate(R.layout.list_item_note, parent, false)
                viewHolder = NoteViewHolder(view)
            }

            ITEM_TABS -> {
                view = inflater.inflate(R.layout.list_item_tabs, parent, false)
                viewHolder = TabsViewHolder(view)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NoteViewHolder) {
            bindNoteViewHolder(holder, position)
        } else if (holder is TabsViewHolder) {
            bindTabsViewHolder(holder, position)
        }
    }

    fun bindTabsViewHolder(holder: TabsViewHolder, position: Int) {
        val notesDao = AppDatabase.getInstance(context).notesDao()
        holder.doubtCountTV.text= notesDao.getDoubtCount().toString()
        holder.tricksCountTV.text= notesDao.getTricksCount().toString()
        holder.conceptCountTV.text= notesDao.getConceptCount().toString()
    }

    //    fun bindNoteViewHolder(holder: NoteViewHolder, position: Int, items: MutableList<Any>)
    fun bindNoteViewHolder(holder: NoteViewHolder, position: Int) {
        holder as NoteViewHolder
        val note = this.items.get(position) as Note
        Glide.with(context)
                .load(note.imageUri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.noteIV)

        holder.noteTV.text = note.text
        holder.itemView.setOnClickListener {
            notesLitener.onNoteClicked(note)
        }
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        val item = items.get(position)
        if (item is Tabs) {
            return ITEM_TABS
        }
        return ITEM_NOTE
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteIV = itemView.findViewById<ImageView>(R.id.noteIV)
        var noteTV = itemView.findViewById<TextView>(R.id.noteTV)
    }

    class TabsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var doubtTV = itemView.findViewById(R.id.doubtTV) as TextView
        var doubtCountTV = itemView.findViewById(R.id.doubtCountTV) as TextView
        var tricksTV = itemView.findViewById(R.id.tricksTV) as TextView
        var tricksCountTV = itemView.findViewById(R.id.tricksCountTV) as TextView
        var conceptsTV = itemView.findViewById(R.id.conceptsTV) as TextView
        var conceptCountTV = itemView.findViewById(R.id.conceptCountTV) as TextView
        var moreTV = itemView.findViewById(R.id.moreTV) as TextView

    }
}