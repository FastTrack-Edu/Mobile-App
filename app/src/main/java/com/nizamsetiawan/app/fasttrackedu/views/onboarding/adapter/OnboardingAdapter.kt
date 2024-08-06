package com.nizamsetiawan.app.fasttrackedu.views.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.views.onboarding.model.OnboardingModel

class OnboardingAdapter(private val onboardingModel: List<OnboardingModel>): RecyclerView.Adapter<OnboardingAdapter.OnboardingModelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingModelViewHolder {
       return OnboardingModelViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_onboading,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: OnboardingModelViewHolder, position: Int) {
        holder.bind(onboardingModel[position])
    }

    override fun getItemCount(): Int {
        return onboardingModel.size
    }

    inner class OnboardingModelViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        private val textTitle = view.findViewById<TextView>(R.id.titleTextView)
        private val textDescription = view.findViewById<TextView>(R.id.descriptionTextView)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageView)

        fun bind(onboardingModel: OnboardingModel){
            textTitle.text = onboardingModel.title
            textDescription.text = onboardingModel.description
            imageIcon.setImageResource(onboardingModel.image)

        }
    }
}