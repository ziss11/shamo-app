package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.databinding.FamiliarItemBinding
import com.ziss.shamoapp.presentation.views.activities.ProductDetailActivity

class FamiliarItemAdapter : RecyclerView.Adapter<FamiliarItemAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: FamiliarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.ivImage.loadImage(R.drawable.shoes_example)
            itemView.setOnClickListener {
                ProductDetailActivity.start(itemView.context, 1)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FamiliarItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FamiliarItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FamiliarItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 10
}