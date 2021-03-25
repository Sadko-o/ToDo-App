package com.example.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter (
    private val todos: MutableList<ToDo>
    ): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    private fun toggleStrikeThrough(textView_todo: TextView, isChecked: Boolean){
        if(isChecked){
            textView_todo.paintFlags = textView_todo.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            textView_todo.paintFlags = textView_todo.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDo = todos[position]
        holder.itemView.apply {
            textView_todo.text = curToDo.title
            checkbox_todo.isChecked = curToDo.isChecked
            toggleStrikeThrough(textView_todo, curToDo.isChecked)
            checkbox_todo.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(textView_todo, isChecked)
                curToDo.isChecked = !curToDo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addToDo(todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneToDo() {
        todos.removeAll { todo->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

}