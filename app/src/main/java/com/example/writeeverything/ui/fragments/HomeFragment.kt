package com.example.writeeverything.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.writeeverything.Model.Notes
import com.example.writeeverything.R
import com.example.writeeverything.ViewModel.NotesViewModel
import com.example.writeeverything.databinding.FragmentHomeBinding
import com.example.writeeverything.ui.adapters.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var myOldNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList->
            myOldNotes = notesList as ArrayList<Notes>
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
            adapter = NotesAdapter(requireContext(),notesList)
            binding.rcvAllNotes.adapter = adapter
        })
        binding.allFilter.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList->
                myOldNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.highFilter.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList->
                myOldNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.mediumFilter.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, { notesList->
                myOldNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.lowFilter.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, { notesList->
                myOldNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.addNotesbtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val items = menu.findItem(R.id.search_menu)
        val searchView = items.actionView as SearchView
        searchView.queryHint = "Enter Title"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(p0: String?) {
        var newFilteredlist = arrayListOf<Notes>()
        for(singleNote in myOldNotes){
            if(singleNote.title?.toLowerCase().contains(p0?.toLowerCase()!!)){
                newFilteredlist.add(singleNote)
            }
        }
        adapter.filtering(newFilteredlist)
    }

}