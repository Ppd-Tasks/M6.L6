package com.example.m6l4taskskt.Model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@field:SerializedName("employee_name")
	val employeeName: String? = null,

	@field:SerializedName("employee_salary")
	val employeeSalary: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("employee_age")
	val employeeAge: Int? = null
)
