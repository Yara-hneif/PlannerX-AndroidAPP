package com.example.plannerX.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.plannerX.data.entities.Note
import com.example.plannerX.data.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val getAllNotes = repository.notesFlow.asLiveData()
    val sortByHighPriority = repository.sortByHighPriority.asLiveData()
    val sortByLowPriority = repository.sortByLowPriority.asLiveData()

    fun insertData(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }

    fun searchNote(searchQuery: String) =
        repository.searchNote(searchQuery).asLiveData()
}