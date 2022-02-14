package com.recyclerview.kotlinproject

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter(private val todos: MutableList<ToDoData>)
    :RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo, parent, false)
        )
    }

    fun addToDo(todo : ToDoData){
        todos.add(todo)
        notifyItemInserted(todos.size- 1)
    }

    fun deleteToDo(){
        todos.removeAll { todo ->
            todo.isChecked

        }
        notifyDataSetChanged()
    }

    private fun togglecb(output:TextView ,isChecked :Boolean){
        if (isChecked){
            output.paintFlags = output.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            output.paintFlags = output.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.itemView.apply {
            output.text =todos[position].title
            idnum.text = (position+1).toString()
            cbdone.isChecked = todos[position].isChecked
            togglecb( output ,todos[position].isChecked)
            cbdone.setOnCheckedChangeListener { _, isChecked ->
                togglecb(output,isChecked)
                todos[position].isChecked = !todos[position].isChecked
            }
        }
    }


    override fun getItemCount(): Int {
        return todos.size
    }
}
