package com.example.study.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.databinding.ItemReviewBinding
import com.example.study.model.Review

class ReviewsAdapter {
    class ReviewsListAdapter : RecyclerView.Adapter<ReviewsListAdapter.ViewHolder>() {
        var reviewsList: MutableList<Review> = emptyList<Review>().toMutableList()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val itemBinding: ItemReviewBinding = ItemReviewBinding.bind(view)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val review = reviewsList[position]
            with(holder.itemBinding) {
                review.multimedia?.src?.let {
                    Glide.with(holder.itemView).load(it).into(post)
                }
                header.text = review.displayTitle
                textPreview.text = review.summaryShort
                criticName.text = review.byline
                calendarData.text = review.dateUpdated
            }
        }

        override fun getItemCount(): Int {
            return reviewsList.size
        }
    }

}