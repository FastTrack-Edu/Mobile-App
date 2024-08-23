package com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.CurriculumsItem

class CurriculumAdapter(private val curriculumList: List<CurriculumsItem>) :
    RecyclerView.Adapter<CurriculumAdapter.CurriculumViewHolder>() {

    private var expandedPosition = -1
    private var previousExpandedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurriculumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curriculum, parent, false)
        return CurriculumViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurriculumViewHolder, position: Int) {
        val item = curriculumList[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = curriculumList.size

    inner class CurriculumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val curriculumTitle: TextView = itemView.findViewById(R.id.curriculumTitle)
        private val expandCollapseIcon: ImageView = itemView.findViewById(R.id.expandCollapseIcon)
        private val subsCurriculumLayout: LinearLayout = itemView.findViewById(R.id.subsCurriculumLayout)

        @SuppressLint("SetTextI18n")
        fun bind(item: CurriculumsItem, position: Int) {
            val isExpanded = position == expandedPosition

            if (isExpanded) {
                curriculumTitle.setTextAppearance(R.style.MediumHeadline) // Menerapkan style
                curriculumTitle.textSize = 14f  // Mengatur ukuran teks
                curriculumTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.primary)) // Mengatur warna teks menjadi biru setelah setTextAppearance
                expandCollapseIcon.setColorFilter(ContextCompat.getColor(itemView.context, R.color.primary))
                expandCollapseIcon.setImageResource(R.drawable.ic_arrow_up)
                subsCurriculumLayout.visibility = View.VISIBLE
            } else {
                curriculumTitle.setTextAppearance(R.style.MediumHeadline) // Menerapkan style yang sama
                curriculumTitle.textSize = 14f  // Ukuran teks lebih kecil saat collapse
                curriculumTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.textprimary)) // Warna teks default saat collapsed
                expandCollapseIcon.setColorFilter(ContextCompat.getColor(itemView.context, R.color.textprimary))
                expandCollapseIcon.setImageResource(R.drawable.ic_arrow_down)
                subsCurriculumLayout.visibility = View.GONE
            }


            itemView.setOnClickListener {
                if (isExpanded) {
                    expandedPosition = -1
                } else {
                    previousExpandedPosition = expandedPosition
                    expandedPosition = position
                }
                notifyItemChanged(previousExpandedPosition)
                notifyItemChanged(position)
            }

            subsCurriculumLayout.removeAllViews()
            item.subcurriculums?.filterNotNull()?.forEach { subItem ->
                val subItemView = TextView(itemView.context).apply {
                    text = "• ${subItem.name}"
                    setPadding(16, 0, 0, 0)
                    setTextAppearance(R.style.RegularSubHeadline)
                    textSize = 14f
                }
                subsCurriculumLayout.addView(subItemView)
            }
        }
    }
}
