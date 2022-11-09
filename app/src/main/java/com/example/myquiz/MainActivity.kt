package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.startButton)
        startButton.setOnClickListener {

            val nameEditText: EditText = findViewById(R.id.nameEditText)
            if(nameEditText.text.isEmpty()) {
                Toast.makeText(this, "You need to enter your name", Toast.LENGTH_LONG).show()
            } else {
                startActivity(Intent(this, QuizQuestionsActivity::class.java))
                finish()
            }
        }
    }
}