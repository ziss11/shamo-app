package com.ziss.shamoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.CheckoutProductItemBinding

class CheckoutProductItemAdapter(private val context: Context) :
    RecyclerView.Adapter<CheckoutProductItemAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: CheckoutProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                ivImage.loadImage(R.drawable.shoes_example)
                tvTitle.text = "Terrex Urban Low"
                tvPrice.text = 25000.toLocalCurrency()
                tvQuantity.text = context.getString(R.string.quantity, 2)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutProductItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CheckoutProductItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CheckoutProductItemAdapter.ListViewHolder,
        position: Int
    ) {
        holder.bind()
    }

    override fun getItemCount() = 2
}