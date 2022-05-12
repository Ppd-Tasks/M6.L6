package com.example.m6l4taskskt.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.m6l4taskskt.R

class CreateActivity : AppCompatActivity() {
    lateinit var name:String
    lateinit var salary:String
    lateinit var age:String
    lateinit var et_name: EditText
    lateinit var et_salary: EditText
    lateinit var et_age: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        initViews()
    }

    fun initViews(){
        et_name = findViewById(R.id.et_name)
        et_salary = findViewById(R.id.et_salary)
        et_age = findViewById(R.id.et_age)
        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        val btn_post = findViewById<Button>(R.id.btn_post)

        btn_cancel.setOnClickListener {
            finish()
        }
        btn_post.setOnClickListener {
            back()
        }

    }
    fun back(){
        val intent = Intent()

        name = et_name.text.toString()
        salary = et_salary.text.toString()
        age = et_age.text.toString()

        intent.putExtra("title",name)
        intent.putExtra("body",salary)
        intent.putExtra("body",age)

        setResult(RESULT_OK,intent)
        finish()
    }
}