package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.FavoriteItemBinding

class FavoriteItemAdapter : RecyclerView.Adapter<FavoriteItemAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onFavoriteClickCallback: OnFavoriteClickCallback

    interface OnItemClickCallback {
        fun onItemClicked()
    }

    interface OnFavoriteClickCallback {
        fun onFavoriteClicked()
    }

    inner class ListViewHolder(private val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                ivImage.loadImage(R.drawable.shoes_example)
                tvTitle.text = "Terrex Urban Low"
                tvPrice.text = 25000.toLocalCurrency()
                btnFavorite.setOnClickListener { onFavoriteClickCallback.onFavoriteClicked() }
            }

            itemView.setOnClickListener { onItemClickCallback.onItemClicked() }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteItemAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoriteItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteItemAdapter.ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 10

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnFavoriteClickCallback(onFavoriteClickCallback: OnFavoriteClickCallback) {
        this.onFavoriteClickCallback = onFavoriteClickCallback
    }
}