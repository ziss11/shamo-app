package com.ziss.shamoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.databinding.GalleryItemBinding

class GalleryAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<GalleryAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(drawable: Int) {
            binding.ivImage.loadImage(drawable)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ListViewHolder, position: Int) {
        holder.bind(images[position % images.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}