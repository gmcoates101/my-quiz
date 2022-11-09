package com.example.myquiz

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), OnClickListener {

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

    private val options = ArrayList<TextView>()
    private var currentQuestion = 1
    private val questions: ArrayList<Question> = Constants.getQuestions()
    private var selectedOption = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        controlSetup()

        submitButton.setOnClickListener {
            submit()
        }

        quizProgressBar.max = questions.size
        setQuestion(0)
    }

    private fun controlSetup() {
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

        options.add(0, optionOneTextView)
        options.add(0, optionTwoTextView)
        options.add(0, optionThreeTextView)
        options.add(0, optionFourTextView)

        optionOneTextView.setOnClickListener(this)
        optionTwoTextView.setOnClickListener(this)
        optionThreeTextView.setOnClickListener(this)
        optionFourTextView.setOnClickListener(this)
        submitButton.setOnClickListener(this)
    }

    private fun setQuestion(index: Int) {
        val question = questions[index]

        questionTextView.text = question.question
        questionImageView.setImageResource(question.image)
        optionOneTextView.text = question.optionOne
        optionTwoTextView.text = question.optionTwo
        optionThreeTextView.text = question.optionThree
        optionFourTextView.text = question.optionFour

        val progress = index + 1
        val progressString = "$progress of ${quizProgressBar.max}"

        quizProgressBar.progress = progress
        quizProgressTextView.text = progressString

        if (progress == quizProgressBar.max) {
            submitButton.text = getString(R.string.quiz_complete)
        }
    }

    private fun selectedOptionView(selectedTextView: TextView, selectedId: Int) {
        defaultOptionsView()

        selectedTextView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        selectedTextView.setTypeface(selectedTextView.typeface, Typeface.BOLD)

        selectedOption = selectedId
    }

    private fun defaultOptionsView() {
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(view: View?) {

        when(view?.id) {
            R.id.optionOneTextView -> selectedOptionView((view as TextView), 1)
            R.id.optionTwoTextView -> selectedOptionView((view as TextView), 2)
            R.id.optionThreeTextView -> selectedOptionView((view as TextView), 3)
            R.id.optionFourTextView -> selectedOptionView((view as TextView), 4)
            R.id.submitButton -> submit()
        }
    }

    private fun submit() {
        val question = questions[currentQuestion - 1]

        if (question.correctAnswer == selectedOption) {
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show()
        }

//        if (currentQuestion < quizProgressBar.max) {
//            setQuestion(currentQuestion)
//            currentQuestion++
//            questionScrollView.smoothScrollTo(0, 0)
//        }
    }
}