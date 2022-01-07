package com.example.writeeverything.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.writeeverything.Model.Notes
import com.example.writeeverything.R
import com.example.writeeverything.ViewModel.NotesViewModel
import com.example.writeeverything.databinding.FragmentEditNotesBinding
import com.example.writeeverything.ui.adapters.NotesAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class EditNotesFragment : Fragment() {
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragmen
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        binding.eTextTitleEN.setText(oldNotes.data.title)
        binding.eTextSubTitleEN.setText(oldNotes.data.subtitle)
        binding.eTextNotesEN.setText(oldNotes.data.notes)
        val str1 = "1"
        val str2 = "2"
        val str3 = "3"
        when(oldNotes.data.priority){
            str1 -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            }
            str2 -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            }
            str3 -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            }
        }
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
        binding.donebtnEN.setOnClickListener {
            updateNotes(it)
        }



        return binding.root
    }


    private fun updateNotes(view: View) {
        val title : String = binding.eTextTitleEN.text.toString()
        val subTitle : String = binding.eTextSubTitleEN.text.toString()
        val notes : String = binding.eTextNotesEN.text.toString()
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("MMM d, yyyy")
        val currDate = sdf.format(cal.time).toString()
        val data = Notes(oldNotes.data.id, title = title, subtitle = subTitle, notes = notes, date = currDate, priority = priority)
        //Log.e("@@@@",""+title)
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(), "Note Updated Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_notes,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delte_menu){
            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            val bottomSheetYes = bottomSheet.findViewById<TextView>(R.id.yes_dialog)
            val bottomSheetNo = bottomSheet.findViewById<TextView>(R.id.no_dialog)

            bottomSheet.show()
            bottomSheetYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                Toast.makeText(requireContext(),"deleted successfully! please press back",Toast.LENGTH_LONG).show()
                //Navigation.findNavController(it).navigate(R.id.action_editNotesFragment_to_homeFragment)
                bottomSheet.dismiss()

            }
            bottomSheetNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}