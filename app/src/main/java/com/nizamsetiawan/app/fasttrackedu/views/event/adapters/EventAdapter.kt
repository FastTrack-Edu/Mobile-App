package com.nizamsetiawan.app.fasttrackedu.views.event.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nizamsetiawan.app.fasttrackedu.BuildConfig
import com.nizamsetiawan.app.fasttrackedu.databinding.ItemEventBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse
import com.nizamsetiawan.app.fasttrackedu.utils.toTime
import com.nizamsetiawan.app.fasttrackedu.views.event.DetailEventActivity

class EventAdapter (private val listener: OnEventClickListener? = null) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private var items: List<EventResponse> = emptyList()

    inner class ViewHolder(private val itemsBinding: ItemEventBinding) :
        RecyclerView.ViewHolder(itemsBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: EventResponse) {
            itemsBinding.apply {
                Glide.with(root)
                    .load("${BuildConfig.BASE_URL}${item.thumbnail}")
                    .into(ivTumbnail)
                tvTitleName.text = item.name
//                tvPrice.text = item.discountPrice.toString()
//                tvDiscountedPrice.text = item.price.toString()
                tvTime.text = item.eventDate?.toTime()
                tvJoined.text = item.category
                tvIntermediate.text = item.audience
                tvMaterial.text = item.organizer
                root.setOnClickListener {
                    val intent = Intent(itemView.context, DetailEventActivity::class.java)
                    intent.putExtra(DetailEventActivity.POST_ID, item.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<EventResponse>) {
        items = newItems.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    interface OnEventClickListener  {
        fun onEventButtonClicked(item: EventResponse)
    }

}