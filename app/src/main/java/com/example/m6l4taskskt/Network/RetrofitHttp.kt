package com.example.m6l4taskskt.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "http://dummy.restapiexample.com/api/v1/"
    val SERVER_PRODUCTION = "http://dummy.restapiexample.com/api/v1/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    fun server():String{
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val employeeService: EmployeeService = retrofit.create(EmployeeService::class.java)


    //...

}