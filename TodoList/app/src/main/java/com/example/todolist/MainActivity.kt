package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.room.Todo
import com.example.todolist.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this // 라이브 데이터를 활용하기 위함

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel

        add_btn.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
               viewModel.insert(Todo(todo_edit.text.toString()))
            }
        }
    }
}