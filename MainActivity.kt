package com.example.assignment2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var exampleFragment: ExampleFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            exampleFragment = ExampleFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, exampleFragment)
                .commit()
        }

        val buttonAddTask: Button = findViewById(R.id.button_add_task)
        val buttonViewTasks: Button = findViewById(R.id.button_view_tasks)

        buttonAddTask.setOnClickListener {
            //Jeet Shelat 12310111
            showAddTaskDialog()
        }

        buttonViewTasks.setOnClickListener {
            //Assigenment 2
            showTasks()
        }
    }

    private fun showAddTaskDialog() {
        val dialogFragment = TaskDialogFragment()
        dialogFragment.listener = { task ->
            exampleFragment.addTask(task)
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
        }
        dialogFragment.show(supportFragmentManager, "TaskDialog")
    }

    private fun showTasks() {
        val tasks = exampleFragment.getTasks()
        val message = tasks.joinToString(separator = "\n") { task ->
            "Name: ${task.name}, Description: ${task.description}, Priority: ${task.priority}, Status: ${task.status}"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

