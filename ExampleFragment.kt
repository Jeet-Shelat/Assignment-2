package com.example.assignment2

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class ExampleFragment : Fragment() {

    private val tasks = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun getTasks(): List<Task> {
        return tasks
    }
}

data class Task(
    val name: String,
    val description: String,
    val priority: String,
    val status: String
)

class TaskDialogFragment : DialogFragment() {

    var listener: ((Task) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskName: EditText = view.findViewById(R.id.edit_text_task_name)
        val taskDescription: EditText = view.findViewById(R.id.edit_text_task_description)
        val taskPriority: Spinner = view.findViewById(R.id.spinner_task_priority)
        val taskStatusGroup: RadioGroup = view.findViewById(R.id.radio_group_task_status)
        val saveButton: Button = view.findViewById(R.id.button_save_task)

        saveButton.setOnClickListener {
            val name = taskName.text.toString()
            val description = taskDescription.text.toString()
            val priority = taskPriority.selectedItem.toString()
            val selectedStatusId = taskStatusGroup.checkedRadioButtonId
            val status = view.findViewById<RadioButton>(selectedStatusId).text.toString()

            val task = Task(name, description, priority, status)
            listener?.invoke(task)
            dismiss()
        }
    }
}
