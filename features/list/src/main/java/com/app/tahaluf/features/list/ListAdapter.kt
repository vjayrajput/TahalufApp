package com.app.tahaluf.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tahaluf.business.university.domain.model.University
import com.app.tahaluf.features.list.databinding.ItemListBinding

class ListAdapter(
    private var universities: List<University>,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    fun updateData(newUniversities: List<University>) {
        universities = newUniversities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val university = universities[position]
        holder.bind(university, clickListener)
    }

    override fun getItemCount(): Int = universities.size

    class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(university: University, clickListener: (String) -> Unit) {
            binding.txtUniversityName.text = university.name
            /**
             * If state is empty, show country
             */
            binding.txtUniversityState.text = university.stateProvince.ifEmpty {
                university.country
            }
            binding.root.setOnClickListener { clickListener(university.name) }
        }
    }
}