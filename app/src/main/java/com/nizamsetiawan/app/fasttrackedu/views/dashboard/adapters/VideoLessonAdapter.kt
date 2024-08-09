package com.nizamsetiawan.app.fasttrackedu.views.dashboard.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nizamsetiawan.app.fasttrackedu.BuildConfig
import com.nizamsetiawan.app.fasttrackedu.databinding.ItemVideoLessonBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.DetailVideoLessonActivity

class VideoLessonAdapter(private val listener: OnVideoLessonClickListener? = null) :
    RecyclerView.Adapter<VideoLessonAdapter.ViewHolder>() {
    private var items: List<VideoLessonResponse> = emptyList()

    inner class ViewHolder(private val itemsBinding: ItemVideoLessonBinding) :
        RecyclerView.ViewHolder(itemsBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: VideoLessonResponse) {
            itemsBinding.apply {
                Glide.with(root)
                    .load("${BuildConfig.BASE_URL}${item.thumbnail}")
                    .into(ivTumbnail)
                tvTitleName.text = item.name
                tvRating.text = item.rating.toString()
                tvPrice.text = item.discountPrice.toString()
                tvDiscountedPrice.text = item.price.toString()
                Glide.with(root)
                    .load("${BuildConfig.BASE_URL}${item.mentor?.photo}")
                    .into(ivProfile)
                tvName.text = item.mentor?.name
                //example
                tvTime.text = item.discount.toString()
                tvJoined.text = item.enrolledMembers?.size.toString() + " Bergabung"
                tvAdvanced.text = item.level
                tvMaterial.text = item.curriculums?.size.toString() + " Materi"
                root.setOnClickListener {
                    val intent = Intent(itemView.context, DetailVideoLessonActivity::class.java)
                    intent.putExtra(DetailVideoLessonActivity.POST_ID, item.id)
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
            ItemVideoLessonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<VideoLessonResponse>) {
        items = newItems.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    interface OnVideoLessonClickListener  {
        fun onVideoLessonButtonClicked(item: VideoLessonResponse)
    }

}