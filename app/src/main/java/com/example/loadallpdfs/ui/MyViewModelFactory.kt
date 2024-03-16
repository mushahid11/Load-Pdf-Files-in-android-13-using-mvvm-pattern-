package com.example.loadallpdfs.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loadallpdfs.data.Repo

class MyViewModelFactory(private val parameter: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(parameter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}