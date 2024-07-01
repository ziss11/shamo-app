package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ProductItemBinding
import com.ziss.shamoapp.presentation.adapters.ProductCardAdapter.OnItemClickCallback

class ProductItemAdapter : RecyclerView.Adapter<ProductItemAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked()
    }

    inner class ListViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                ivImage.loadImage(R.drawable.shoes_example)
                tvCategory.text = "Hiking"
                tvTitle.text = "Predator 20.3 Firm Ground"
                tvPrice.text = 20500.toLocalCurrency()
            }

            itemView.setOnClickListener { onItemClickCallback.onItemClicked() }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 5

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}