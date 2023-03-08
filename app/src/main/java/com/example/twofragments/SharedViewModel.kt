package com.example.twofragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val _data = MutableStateFlow<Cat>(Cat(0, ""))
    val data: StateFlow<Cat> = _data.asStateFlow()

    private val _listCat = MutableSharedFlow<MutableList<Cat>>()
    val stateListCat = _listCat.asSharedFlow()

    val catRepository = CatRepository()

    fun getCats() {
        val list = mutableListOf<Cat>()
        viewModelScope.launch {
            catRepository.getData().collect {
                list.add(it)
                _listCat.emit(list)
            }
        }
    }

    fun update(cat: Cat) {
        _data.value = cat
    }
}