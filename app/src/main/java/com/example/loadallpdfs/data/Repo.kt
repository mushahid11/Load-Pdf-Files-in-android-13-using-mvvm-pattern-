package com.example.loadallpdfs.data


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.loadallpdfs.util.DataClass
import java.io.File

class Repo(activity: Context) : DataClass() {

    private var files = MutableLiveData<List<File>>()



    init {
        files.value = loadAllPdfs(activity)
    }

    fun getFiles(): MutableLiveData<List<File>> = files
}