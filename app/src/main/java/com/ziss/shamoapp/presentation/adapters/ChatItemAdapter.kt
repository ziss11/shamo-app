package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.databinding.ChatItemBinding

class ChatItemAdapter : RecyclerView.Adapter<ChatItemAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

//    private var messages = arrayListOf<Objects>()

    interface OnItemClickCallback {
        fun onItemClicked()
    }

    inner class ListViewHolder(private val binding: ChatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                ivImage.loadImage(R.drawable.ic_logo)
                tvTitle.text = "Shoe Store"
                tvSubtitle.text = "Good night, This item is on"
                tvTime.text = "Now"
            }
            itemView.setOnClickListener { onItemClickCallback.onItemClicked() }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 20

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

//    fun setMessages(messages: List<Objects>) {
//        val diffCallback = ListDiffUtils(this.messages, messages) { it }
//        val diffUtil = DiffUtil.calculateDiff(diffCallback)
//
//        this.messages.clear()
//        this.messages.addAll(messages)
//
//        diffUtil.dispatchUpdatesTo(this)
//    }
}