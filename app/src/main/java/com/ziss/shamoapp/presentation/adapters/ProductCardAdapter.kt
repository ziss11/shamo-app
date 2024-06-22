package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ProductCardBinding

class ProductCardAdapter : RecyclerView.Adapter<ProductCardAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.ivImage.loadImage(R.drawable.shoes_example)
            binding.tvCategory.text = "Hiking"
            binding.tvTitle.text = "COURT VISION 2.0"
            binding.tvPrice.text = 20500.toLocalCurrency()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCardAdapter.ListViewHolder {
        val binding = ProductCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductCardAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 5
}