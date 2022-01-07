package com.example.writeeverything.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.writeeverything.Database.NotesDatabase
import com.example.writeeverything.Model.Notes
import com.example.writeeverything.Repository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {
    val repository : NotesRepository
    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }
    fun addNotes(notes : Notes){
        repository.insertNotes(notes)
    }

    fun getNotes() : LiveData<List<Notes>>{
        return repository.getAllNotes()
    }
    fun getHighNotes() : LiveData<List<Notes>>{
        return repository.getHighNotes()
    }
    fun getMediumNotes() : LiveData<List<Notes>>{
        return repository.getMediumNotes()
    }
    fun getLowNotes() : LiveData<List<Notes>>{
        return repository.getLowNotes()
    }

    fun deleteNotes(id : Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes : Notes){
        repository.updateNotes(notes)
    }
}
