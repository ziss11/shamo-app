package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.CartItemBinding

class CartItemAdapter : RecyclerView.Adapter<CartItemAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onCartItemActionsCallback: OnCartItemActionsCallback

    interface OnItemClickCallback {
        fun onItemClicked()
    }

    interface OnCartItemActionsCallback {
        fun increaseQuantity()
        fun decreaseQuantity()
        fun remove()
    }

    inner class ListViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                ivImage.loadImage(R.drawable.shoes_example)
                tvProductName.text = "Terrex Urban Low"
                tvPrice.text = 25000.toLocalCurrency()
                tvQty.text = 2.toString()

                btnIncreaseQty.setOnClickListener {
                    onCartItemActionsCallback.increaseQuantity()
                }
                btnDecreaseQty.setOnClickListener {
                    onCartItemActionsCallback.decreaseQuantity()
                }
                btnRemove.setOnClickListener {
                    onCartItemActionsCallback.remove()
                }
            }

            itemView.setOnClickListener { onItemClickCallback.onItemClicked() }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 10

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnCartItemActionsCallback(onCartItemActionsCallback: OnCartItemActionsCallback) {
        this.onCartItemActionsCallback = onCartItemActionsCallback
    }
}