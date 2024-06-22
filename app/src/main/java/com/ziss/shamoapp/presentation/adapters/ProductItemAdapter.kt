package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ProductItemBinding

class ProductItemAdapter : RecyclerView.Adapter<ProductItemAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.ivImage.loadImage(R.drawable.shoes_example)
            binding.tvCategory.text = "Hiking"
            binding.tvTitle.text = "Predator 20.3 Firm Ground"
            binding.tvPrice.text = 20500.toLocalCurrency()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItemAdapter.ListViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 5
}