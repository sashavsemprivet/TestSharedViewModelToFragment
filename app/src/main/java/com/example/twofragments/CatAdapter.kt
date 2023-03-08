package com.example.twofragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twofragments.databinding.CatItemBinding

class CatAdapter(private val catList: MutableList<Cat>, var clickAdapter: ClickAdapter) :
    RecyclerView.Adapter<CatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        var binding = CatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding, clickAdapter)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(catList[position])
    }

    fun addData(newCatList: List<Cat>) {
        catList.clear()
        catList.addAll(newCatList)
        notifyDataSetChanged()
    }
}

class CatViewHolder(private val catItemBinding: CatItemBinding, var clickAdapter: ClickAdapter) :
    RecyclerView.ViewHolder(catItemBinding.root) {
    fun bind(cat: Cat) {
        catItemBinding.id.text = cat.id.toString()
        catItemBinding.name.text = cat.name

        catItemBinding.catItemSingle.setOnClickListener {
            clickAdapter.onClick(cat)
        }
    }

}
