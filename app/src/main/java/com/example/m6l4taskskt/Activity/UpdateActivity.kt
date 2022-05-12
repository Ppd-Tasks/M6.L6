package com.example.m6l4taskskt.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.m6l4taskskt.Model.Employee
import com.example.m6l4taskskt.R

class UpdateActivity : AppCompatActivity() {

    lateinit var et_name: EditText
    lateinit var et_salary: EditText
    lateinit var et_age: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        initViews()
    }
    fun initViews(){
        et_name = findViewById(R.id.et_name)
        et_salary = findViewById(R.id.et_salary)
        et_age = findViewById(R.id.et_age)
        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        val btn_post = findViewById<Button>(R.id.btn_post)

        val employee = intent.getSerializableExtra("employee") as Employee

        et_name.setText(employee.employee_name)
        et_salary.setText(employee.employee_salary)
        et_age.setText(employee.employee_age)

        btn_cancel.setOnClickListener {
            finish()
        }
        btn_post.setOnClickListener {
            back(employee)
        }

    }
    fun back(employee: Employee){
        val intent = Intent()

        employee.employee_name = et_name.text.toString()
        employee.employee_salary = et_salary.text.toString()
        employee.employee_age = et_age.text.toString()

        intent.putExtra("employee",employee)

        setResult(RESULT_OK,intent)
        finish()
    }
}