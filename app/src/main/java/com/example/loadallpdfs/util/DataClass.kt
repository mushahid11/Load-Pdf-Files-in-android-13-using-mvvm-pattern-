package com.example.loadallpdfs.util

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import java.io.File

abstract class DataClass {

    private lateinit var allPDFFilesList:MutableList<File>

    fun loadAllPdfs(activity: Context): List<File> {

        allPDFFilesList = arrayListOf()

        val projection = arrayOf(
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE
        )
        val sortOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        val selection = MediaStore.Files.FileColumns.MIME_TYPE + " = ?"
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf")
        val selectionArgs = arrayOf(mimeType)
        val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Files.getContentUri("external")
        }
        activity.contentResolver?.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder
        ).use { cursor ->
            assert(cursor != null)
            if (cursor!!.moveToFirst()) {
                val columnData = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
                do {
                    val path = cursor.getString(columnData)
                    val file = File(path)
                    if (file.exists()) {
                        //you can get your pdf files
                        allPDFFilesList.add(File(cursor.getString(columnData)))

                    }
                } while (cursor.moveToNext())
            }
        }



        return allPDFFilesList
    }



}