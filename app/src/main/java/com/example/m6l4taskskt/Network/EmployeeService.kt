package com.example.m6l4taskskt.Network

import com.example.m6l4taskskt.Model.EmployeeResp
import com.example.m6l4taskskt.Model.Employee
import retrofit2.Call
import retrofit2.http.*

interface EmployeeService {

    @Headers(
        "Content-type:application/json"
    )

    @GET("employees")
    fun listPost(): Call<ArrayList<EmployeeResp>>

    @GET("employee/{id}")
    fun singlePost(@Path("id")id:Int):Call<EmployeeResp>

    @POST("create")
    fun createPost(@Body post: Employee):Call<EmployeeResp>

    @PUT("update/{id}")
    fun updatePost(@Path("id")id: Int,@Body post: Employee):Call<EmployeeResp>

    @DELETE("delete/{id}")
    fun deletePost(@Path("id")id:Int):Call<EmployeeResp>

}