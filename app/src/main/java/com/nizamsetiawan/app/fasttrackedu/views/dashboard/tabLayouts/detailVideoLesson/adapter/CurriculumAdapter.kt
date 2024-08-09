package com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.databinding.ItemCurriculumBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.CurriculumsItem
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.SubcurriculumsItem

class CurriculumAdapter(
    private val curriculums: List<CurriculumsItem>
) : RecyclerView.Adapter<CurriculumAdapter.CurriculumViewHolder>() {

    private var expandedPosition = RecyclerView.NO_POSITION

    inner class CurriculumViewHolder(private val binding: ItemCurriculumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(curriculum: CurriculumsItem, position: Int) {
            binding.apply {
                curriculumTitle.text = curriculum.name

                // Handle subcurriculums
                if (curriculum.subcurriculums.isNullOrEmpty()) {
                    subsCurriculumLayout.visibility = View.GONE
                } else {
                    setupSubcurriculums(curriculum.subcurriculums)
                }

                root.setOnClickListener {
                    // Toggle expansion
                    expandedPosition = if (adapterPosition == expandedPosition) {
                        RecyclerView.NO_POSITION
                    } else {
                        adapterPosition
                    }
                    notifyDataSetChanged()
                }

                subsCurriculumLayout.visibility =
                    if (adapterPosition == expandedPosition) View.VISIBLE else View.GONE

                // Update the arrow direction based on the expanded state
                val arrowIcon = if (adapterPosition == expandedPosition) {
                    R.drawable.ic_arrow_up
                } else {
                    R.drawable.ic_arrow_down
                }
                expandCollapseIcon.setImageResource(arrowIcon)
            }
        }

        private fun setupSubcurriculums(subcurriculums: List<SubcurriculumsItem?>?) {
            binding.subsCurriculumLayout.removeAllViews()

            subcurriculums?.forEach { subcurriculum ->
                subcurriculum?.let {
                    val bulletPoint = TextView(binding.root.context).apply {
                        text = "•"
                        textSize = 20f
                    }
                    val description = TextView(binding.root.context).apply {
                        text = it.name
                        textSize = 12f
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }
                    val layout = LinearLayout(binding.root.context).apply {
                        orientation = LinearLayout.HORIZONTAL
                        addView(bulletPoint)
                        addView(description)
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }
                    binding.subsCurriculumLayout.addView(layout)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurriculumViewHolder {
        val binding = ItemCurriculumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurriculumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurriculumViewHolder, position: Int) {
        holder.bind(curriculums[position], position)
    }

    override fun getItemCount(): Int = curriculums.size
}
