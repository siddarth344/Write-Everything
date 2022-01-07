package com.example.writeeverything.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.writeeverything.Model.Notes
import com.example.writeeverything.R
import com.example.writeeverything.databinding.ItemLayoutBinding
import com.example.writeeverything.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext : Context, var notesList : List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    class notesViewHolder(val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    fun filtering(newFilteredlist: ArrayList<Notes>) {
        notesList = newFilteredlist
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.title.setText(data.title)
        holder.binding.subTitle.setText(data.subtitle)
        holder.binding.date.setText(data.date)
        val str1 = "1"
        val str2 = "2"
        val str3 = "3"
        when(data.priority){
            str1 -> {
                holder.binding.priorityView.setBackgroundResource(R.drawable.green_shape)
            }
            str2 -> {
                holder.binding.priorityView.setBackgroundResource(R.drawable.yellow_shape)
            }
            str3 -> {
                holder.binding.priorityView.setBackgroundResource(R.drawable.red_shape)
            }
        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}