package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ProductItemBinding

class SearchProductItemAdapter : RecyclerView.Adapter<SearchProductItemAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                ivImage.loadImage(R.drawable.shoes_example)
                tvCategory.text = "Hiking"
                tvTitle.text = "Predator 20.3 Firm Ground"
                tvPrice.text = 20500.toLocalCurrency()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchProductItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchProductItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 15
}