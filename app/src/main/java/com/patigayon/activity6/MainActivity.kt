package com.patigayon.activity6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.patigayon.activity6.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonClick = AnimationUtils.loadAnimation(this, R.anim.button_click)

        with(binding) {
            alertDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showAlertDialog()
            }

            datePickerDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showDatePickerDialog()
            }

            timePickerDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showTimePickerDialog()
            }

            customDialogButton.setOnClickListener {
                it.startAnimation(buttonClick)
                showCustomDialog()
            }
        }
    }

    private fun showAlertDialog() {
        val editText = EditText(this)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert_dialog_title)
        builder.setMessage(R.string.alert_dialog_message)
        builder.setView(editText)
        builder.setPositiveButton(R.string.alert_dialog_ok) { _, _ ->
            val userInput = editText.text.toString()
            binding.alertDialogSelection.text = getString(R.string.alert_dialog_user_input, userInput)
            Toast.makeText(applicationContext, R.string.pressed_ok, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(R.string.alert_dialog_cancel) { _, _ ->
            Toast.makeText(applicationContext, R.string.cancelled, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, monthOfYear, dayOfMonth ->
            val dateMsg = getString(R.string.date_picker_title, "$dayOfMonth/${monthOfYear + 1}/$selectedYear")
            binding.datePickerSelection.text = dateMsg
            Toast.makeText(applicationContext, dateMsg, Toast.LENGTH_SHORT).show()
        }, year, month, day).show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val timeMsg = getString(R.string.time_picker_title, "$selectedHour:$selectedMinute")
            binding.timePickerSelection.text = timeMsg
            Toast.makeText(applicationContext, timeMsg, Toast.LENGTH_SHORT).show()
        }, hour, minute, true).show()
    }

    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog_layout, null)
        val customDialogEditText = dialogView.findViewById<EditText>(R.id.customDialogEditText)

        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.custom_dialog_title)
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.customDialogButton).setOnClickListener {
            val userInput = customDialogEditText.text.toString()
            binding.customDialogSelection.text = getString(R.string.custom_dialog_input_text, userInput)
            dialog.dismiss()
            Toast.makeText(applicationContext, getString(R.string.custom_dialog_input_text, userInput), Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }
}
