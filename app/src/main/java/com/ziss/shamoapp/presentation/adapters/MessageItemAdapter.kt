package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.databinding.MessageItemBinding

class MessageItemAdapter : RecyclerView.Adapter<MessageItemAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
//                ivImage.loadImage(R.drawable.ic_logo)
                tvTitle.text = "Shoe Store"
                tvSubtitle.text = "Good night, This item is on"
                tvTime.text = "Now"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageItemAdapter.ListViewHolder {
        val binding = MessageItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 20
}