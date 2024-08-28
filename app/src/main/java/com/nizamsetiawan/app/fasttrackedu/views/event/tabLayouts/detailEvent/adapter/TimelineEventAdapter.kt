package com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.TimelinesItem
import com.nizamsetiawan.app.fasttrackedu.utils.formatDateRange

class TimelineEventAdapter(private val timelineEventList: List<TimelinesItem>) :
    RecyclerView.Adapter<TimelineEventAdapter.TimeLineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timeline, parent, false)
        return TimeLineViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        val item = timelineEventList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = timelineEventList.size

    inner class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitle: TextView = itemView.findViewById(R.id.timelineTitle)
        private val eventDate: TextView = itemView.findViewById(R.id.date_time)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: TimelinesItem) {
            eventTitle.text = item.name
            eventDate.text = formatDateRange(item.startDate!!, item.endDate!!)
        }
    }
}