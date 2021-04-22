package com.example.todolist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.Todo

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "todo_db"
    ).build()

    var todos : LiveData<List<Todo>>

    init{
        todos = getAll()
    }

    fun getAll(): LiveData<List<Todo>>{
        return db.todoDao().getAll()
    }

    fun insert(todo: Todo){
        db.todoDao().insert(todo)
    }


}