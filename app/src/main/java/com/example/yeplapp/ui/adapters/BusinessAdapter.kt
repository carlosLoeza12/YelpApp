package com.example.yeplapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.yeplapp.core.addressFormat
import com.example.yeplapp.core.categoriesFormat
import com.example.yeplapp.data.model.Businesses
import com.example.yeplapp.databinding.ItemBusinessBinding

class BusinessAdapter(private val list: List<Businesses>,
private val itemClickListener: OnBusinessClickListener) : RecyclerView.Adapter<BusinessAdapter.ViewHolder>() {

    interface OnBusinessClickListener{
        fun onBusinessClick(businesses: Businesses, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessAdapter.ViewHolder {
       val itemBinding = ItemBusinessBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(itemBinding)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != -1
            } ?: return@setOnClickListener
            itemClickListener.onBusinessClick(list[position], position)
        }
        return holder
    }

    override fun onBindViewHolder(holder: BusinessAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemBusinessBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(businesses: Businesses){
            binding.imgBusiness.load(businesses.image_url)
            binding.txtBusinessName.text = businesses.name
            binding.txtBusinessAddress.text = businesses.location.display_address.addressFormat()
            binding.txtBusinessCategory.text = businesses.categories.categoriesFormat()
            binding.txtRating.text = businesses.rating.toString()
        }
    }

}