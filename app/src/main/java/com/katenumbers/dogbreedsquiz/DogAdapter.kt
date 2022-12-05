package com.katenumbers.dogbreedsquiz

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.recyclerview.widget.RecyclerView
import com.katenumbers.dogbreedsquiz.databinding.RecyclerListItemBinding
import com.katenumbers.dogbreedsquiz.models.Dog

class DogAdapter(private val dogs: List<Dog>, private val onClick: (Dog) -> Unit = {}): RecyclerView.Adapter<DogAdapter.ViewHolder>() {
    class ViewHolder(val binding: RecyclerListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogs[position]
        val resourceID: Int = dog.image
        holder.binding.imageView2.setImageResource(resourceID)
        holder.binding.name.text = dog.name
        holder.binding.section.text = dog.section
        holder.binding.group.text = dog.group
        holder.binding.country.text = dog.country
    }

    override fun getItemCount(): Int {
        return dogs.size
    }
}