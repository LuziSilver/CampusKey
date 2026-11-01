package com.example.campuskey

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schoolInput = findViewById<AutoCompleteTextView>(R.id.schoolInput)
        val findButton = findViewById<Button>(R.id.findButton)

        // Fill the suggestion dropdown with every school we know about
        val schoolNames = SchoolData.schools.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, schoolNames)
        schoolInput.setAdapter(adapter)
        schoolInput.threshold = 1 // show suggestions after typing just 1 letter

        findButton.setOnClickListener {
            val typedName = schoolInput.text.toString().trim()

            // Match whatever the user typed/picked against our known school list
            val matchedIndex = SchoolData.schools.indexOfFirst {
                it.name.equals(typedName, ignoreCase = true)
            }

            if (matchedIndex == -1) {
                Toast.makeText(
                    this,
                    getString(R.string.invalid_school_message),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, ListingsActivity::class.java)
                intent.putExtra("SCHOOL_INDEX", matchedIndex)
                startActivity(intent)
            }
        }
    }
}
