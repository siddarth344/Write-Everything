package com.example.writeeverything.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.writeeverything.Model.Notes

@Dao
interface  NotesDao {
    @Query("select * from Notes")
    fun getNotes():LiveData<List<Notes>>

    @Query("select * from Notes WHERE priority = 3")
    fun getHighNotes():LiveData<List<Notes>>

    @Query("select * from Notes WHERE priority = 2")
    fun getMediumNotes():LiveData<List<Notes>>

    @Query("select * from Notes WHERE priority = 1")
    fun getLowNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note : Notes)

    @Query("Delete from Notes where id = :id")
    fun deleteNotes(id : Int)

    @Update
    fun updateNotes(notes : Notes)

}