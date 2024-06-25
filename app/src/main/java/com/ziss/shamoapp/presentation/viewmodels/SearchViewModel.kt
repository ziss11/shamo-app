package com.ziss.shamoapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    private var _query = MutableLiveData("")
    val query get() = _query

    fun setQuery(value: String) {
        _query.value = value
    }
}