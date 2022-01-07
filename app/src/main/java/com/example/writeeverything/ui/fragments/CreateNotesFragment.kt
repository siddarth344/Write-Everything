package com.example.writeeverything.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat.format
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.writeeverything.Model.Notes
import com.example.writeeverything.R
import com.example.writeeverything.ViewModel.NotesViewModel
import com.example.writeeverything.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat

import java.util.*

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)


        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.donebtnCN.setOnClickListener {
            val title : String = binding.eTextTitleCN.text.toString()
            val subTitle : String = binding.eTextSubTitleCN.text.toString()
            val notes : String = binding.eTextNotesCN.text.toString()
            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat("MMM d, yyyy")
            val currDate = sdf.format(cal.time).toString()
            val data = Notes(null, title = title, subtitle = subTitle, notes = notes, date = currDate, priority = priority)
            //Log.e("@@@@",""+title)
            viewModel.addNotes(data)
            Toast.makeText(requireContext(), "Note Created Successfully", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.action_createNotesFragment_to_homeFragment)
        }
        return binding.root
    }

}

