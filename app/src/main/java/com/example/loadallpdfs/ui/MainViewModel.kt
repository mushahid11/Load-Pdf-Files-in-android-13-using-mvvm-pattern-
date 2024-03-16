package com.example.loadallpdfs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.loadallpdfs.data.Repo
import java.io.File

class MainViewModel(repo: Repo) : ViewModel() {

    private var _files = repo.getFiles()

     val files:LiveData<List<File>>
            get() = _files


}