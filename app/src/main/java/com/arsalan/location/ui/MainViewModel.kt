package com.arsalan.location.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.arsalan.location.model.LocationModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.util.Date

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<List<LocationModel>>(listOf())
    val uiState: StateFlow<List<LocationModel>> = _uiState.asStateFlow()


    // TODO: Delete after add service

    init {
        fakeData()
    }


    private fun fakeData() {
        viewModelScope.launch {
            withTimeout(111300L) {
                repeat(5000) { i ->
                    val myMutableList = mutableListOf<LocationModel>()
                    myMutableList.add(
                        LocationModel(
                            lat = 10000.0 + i,
                            lng = 300000.0 + i,
                            dateAndTime = Date().toString()
                        )
                    )
                    _uiState.value += myMutableList
                    Log.d(
                        "ArslanTest",
                        "${_uiState.value.size}  ${myMutableList.size}   ${if (_uiState.value.isNotEmpty()) _uiState.value.last().dateAndTime else ""}"
                    )
                    delay(5000)
                }
            }
        }
    }
}