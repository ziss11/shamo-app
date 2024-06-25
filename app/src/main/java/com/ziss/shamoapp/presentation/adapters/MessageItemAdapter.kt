package com.ziss.shamoapp.presentation.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.BubbleMessageItemBinding

class MessageItemAdapter : RecyclerView.Adapter<MessageItemAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: BubbleMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(isSender: Boolean = true, withProduct: Boolean = false) {
            binding.apply {
                tvMessage.text = "This is a message"
                tvTime.text = "11:19 PM"

                productInMessageItem.ivImage.loadImage(R.drawable.shoes_example)
                productInMessageItem.tvTitle.text = "Predator 20.3 Firm Ground"
                productInMessageItem.tvPrice.text = 20500.toLocalCurrency()
                productInMessageItem.container.visibility =
                    if (withProduct) View.VISIBLE else View.GONE

                setBubbleMessageStyle(binding, isSender)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BubbleMessageItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageItemAdapter.ListViewHolder, position: Int) {
        if (position == 0) {
            holder.bind(withProduct = true)
        } else {
            if (position % 2 == 0) {
                holder.bind(isSender = false, withProduct = false)
            } else {
                holder.bind()
            }
        }
    }

    override fun getItemCount(): Int = 10

    private fun setBubbleMessageStyle(binding: BubbleMessageItemBinding, isSender: Boolean) {
        if (isSender) {
            binding.container.gravity = Gravity.END
            binding.bubbleMessageContainer.setBackgroundResource(R.drawable.sender_bubble_message_bg)
        } else {
            binding.container.gravity = Gravity.START
            binding.bubbleMessageContainer.setBackgroundResource(R.drawable.receiver_bubble_message_bg)
        }
    }
}