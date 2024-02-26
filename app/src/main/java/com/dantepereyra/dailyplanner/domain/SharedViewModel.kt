package com.dantepereyra.dailyplanner.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: DateRepository // Asume la existencia de este repositorio
) : ViewModel() {
    // StateFlow para la fecha seleccionada
    private val _selectedDate = MutableStateFlow<String?>(null)
    val selectedDate: StateFlow<String?> = _selectedDate

    init {
        loadSelectedDate()
    }

    private fun loadSelectedDate() {
        viewModelScope.launch {
            _selectedDate.value = repository.getSelectedDate()
        }
    }

    fun saveSelectedDate(date: String) {
        viewModelScope.launch {
            repository.saveSelectedDate(date)
            _selectedDate.value = date
        }
    }
}