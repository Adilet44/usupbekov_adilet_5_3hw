package com.example.usupbekov_adilet_5_3hw.pixa

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usupbekov_adilet_5_3hw.databinding.ItemImagesBinding

class PixaAdapter(val list: ArrayList<ImageModel>): RecyclerView.Adapter<PixaAdapter.PixaViewHolder>() {

    fun addImages(listImages: ArrayList<ImageModel>) {
        list.addAll(listImages)
    }

    class PixaViewHolder(var binding: ItemImagesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageModel: ImageModel) {
            binding.imgPhoto.load(imageModel.largeImageURL)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.bind(list[position])
    }
}