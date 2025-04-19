package com.example.superhero

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _heroes = MutableLiveData<List<Superhero>>()
    val heroes: LiveData<List<Superhero>> = _heroes

    fun searchHeroes(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.searchHeroes(query)
                if(response.isSuccessful) {
                    _heroes.value = response.body()?.results ?: emptyList()
                }
            } catch(e: Exception) {
                Log.e("MainViewModel", "Error: ${e.message}")
            }
        }
    }
}