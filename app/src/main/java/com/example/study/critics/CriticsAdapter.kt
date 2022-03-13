package com.example.study.critics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.databinding.CriticsBinding
import com.example.study.model.Review

class CriticsAdapter : RecyclerView.Adapter<CriticsAdapter.CriticsViewHolder>() {
    var reviewsList: MutableList<Review> = emptyList<Review>().toMutableList()

    class CriticsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemBinding : CriticsBinding = CriticsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriticsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.critics, parent, false)
        return  CriticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CriticsViewHolder, position: Int) {
        val critic = reviewsList[position]
        with(holder.itemBinding){

        }
    }

    override fun getItemCount(): Int {
        return reviewsList.size
    }
}