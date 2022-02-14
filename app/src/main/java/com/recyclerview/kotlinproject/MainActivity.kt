package com.recyclerview.kotlinproject

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alertbox_layout.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoAdapter = ToDoAdapter(mutableListOf())

        recyclerview.adapter = toDoAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        deleteitems()
        setupClick()

    }

    fun setupClick() {
        addbtn.setOnClickListener {
            val DialogView = LayoutInflater.from(this)
                .inflate(R.layout.alertbox_layout, null)
            val     Builder = AlertDialog.Builder(this)
                .setView(DialogView)
            val alertdialog = Builder.show()

            DialogView.add_button.setOnClickListener {
                val todoTitle = DialogView.Todoedit.text.toString()
                if (todoTitle.isNotEmpty()) {
                    val todo = ToDoData(todoTitle)
                    toDoAdapter.addToDo(todo)
                }
                alertdialog.dismiss()
            }

            DialogView.cancel_button.setOnClickListener {
                alertdialog.dismiss()
            }
        }
    }

    fun deleteitems() {
        deletebutton.setOnClickListener {
            toDoAdapter.deleteToDo()
        }
    }

}