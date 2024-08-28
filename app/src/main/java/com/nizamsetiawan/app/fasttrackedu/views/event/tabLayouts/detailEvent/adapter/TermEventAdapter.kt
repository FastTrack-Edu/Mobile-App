package com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.TermConditionsItem

class TermEventAdapter(private val termEventList: List<TermConditionsItem>) :
    RecyclerView.Adapter<TermEventAdapter.TermEventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermEventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_term, parent, false)
        return TermEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: TermEventViewHolder, position: Int) {
        val item = termEventList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = termEventList.size

    inner class TermEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)

        @SuppressLint("SetTextI18n")
        fun bind(item: TermConditionsItem) {
            eventTitle.text = "• ${item.description}"
        }
    }
}
