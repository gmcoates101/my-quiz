package com.example.myquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity() {

    private lateinit var questionScrollView: ScrollView
    private lateinit var questionTextView: TextView
    private lateinit var questionImageView: ImageView
    private lateinit var optionOneTextView: TextView
    private lateinit var optionTwoTextView: TextView
    private lateinit var optionThreeTextView: TextView
    private lateinit var optionFourTextView: TextView
    private lateinit var quizProgressBar: ProgressBar
    private lateinit var quizProgressTextView: TextView
    private lateinit var submitButton: Button

    private var currentQuestion = 1
    private val questions: ArrayList<Question> = Constants.getQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        questionTextView = findViewById(R.id.questionTextView)
        questionImageView = findViewById(R.id.questionImageView)
        optionOneTextView = findViewById(R.id.optionOneTextView)
        optionTwoTextView = findViewById(R.id.optionTwoTextView)
        optionThreeTextView = findViewById(R.id.optionThreeTextView)
        optionFourTextView = findViewById(R.id.optionFourTextView)

        quizProgressBar = findViewById(R.id.quizProgressBar)
        quizProgressTextView = findViewById(R.id.quizProgressTextView)

        submitButton = findViewById(R.id.submitButton)

        questionScrollView = findViewById(R.id.questionScrollView)

        quizProgressBar.max = questions.size
        Log.i("questions.size", "${questions.size}")

        submitButton.setOnClickListener {
            submit()
        }

        setQuestion(0)
    }

    private fun setQuestion(index: Int) {
        val question = questions[index]

        questionTextView.text = question.question
        questionImageView.setImageResource(question.image)
        optionOneTextView.text = question.optionOne
        optionTwoTextView.text = question.optionTwo
        optionThreeTextView.text = question.optionThree
        optionFourTextView.text = question.optionFour

        quizProgressBar.progress = index + 1
        val progress = "${index + 1} of ${quizProgressBar.max}"
        quizProgressTextView.text = progress
    }

    private fun submit() {
        setQuestion(currentQuestion)
        currentQuestion++
        submitButton.isEnabled = currentQuestion < quizProgressBar.max
        questionScrollView.smoothScrollTo(0, 0)
    }
}