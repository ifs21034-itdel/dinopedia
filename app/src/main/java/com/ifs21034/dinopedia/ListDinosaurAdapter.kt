package com.ifs21034.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21034.dinopedia.databinding.ItemRowDinosaurBinding

class ListDinosaurAdapter(private val listDinosaur: ArrayList<Dinosaur>) :
    RecyclerView.Adapter<ListDinosaurAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding = ItemRowDinosaurBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:Int) {
        val dinosaur = listDinosaur[position]
        holder.binding.ivItemDinosaur.setImageResource(dinosaur.icon)
        holder.binding.tvItemDinosaur.text = dinosaur.name
        holder.binding.tvItemReview.text = dinosaur.review
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinosaur[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinosaur.size

    class ListViewHolder(var binding: ItemRowDinosaurBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dinosaur)
    }
}
