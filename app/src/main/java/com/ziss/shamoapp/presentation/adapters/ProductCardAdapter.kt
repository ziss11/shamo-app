package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ProductCardBinding

class ProductCardAdapter : RecyclerView.Adapter<ProductCardAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked()
    }

    inner class ListViewHolder(private val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                ivImage.loadImage(R.drawable.shoes_example)
                tvCategory.text = "Hiking"
                tvTitle.text = "COURT VISION 2.0"
                tvPrice.text = 20500.toLocalCurrency()
            }

            itemView.setOnClickListener { onItemClickCallback.onItemClicked() }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCardAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductCardBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductCardAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 5

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}