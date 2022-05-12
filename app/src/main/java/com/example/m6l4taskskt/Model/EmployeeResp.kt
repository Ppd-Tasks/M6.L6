package com.example.m6l4taskskt.Model

import com.google.gson.annotations.SerializedName

data class EmployeeResp(
    @SerializedName ("id")
    val id:String? = null,
    @SerializedName ("employee_name")
    val employee_name:String? = null,
    @SerializedName ("employee_salary")
    val employee_salary:String? = null,
    @SerializedName ("employee_age")
    val employee_age:String? = null,
    @SerializedName ("profile_image")
    val profile_image:String? = null,

)