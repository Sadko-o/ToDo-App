package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = ToDoAdapter(mutableListOf())

        rv_todo_items.adapter = todoAdapter
        rv_todo_items.layoutManager = LinearLayoutManager(this)

        button_Add_ToDo.setOnClickListener {
            val toDoTitle = editText_todo.text.toString()
            if(toDoTitle.isNotEmpty()){
                val todo = ToDo(toDoTitle)
                todoAdapter.addToDo(todo)
                editText_todo.text.clear()
            }
        }
        button_Del_ToDo.setOnClickListener {
            todoAdapter.deleteDoneToDo()
        }
    }

}