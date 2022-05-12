package com.example.m6l4taskskt.Network

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.m6l4taskskt.Application.MyApplication
import com.example.m6l4taskskt.Helper.Logger
import com.example.m6l4taskskt.Model.Employee
import org.json.JSONObject

class VolleyHttp {
    companion object{
        val TAG = VolleyHttp::class.java.simpleName
        val IS_TESTER = true
        val SERVER_DEVELOPMENT = "http://dummy.restapiexample.com/api/v1"
        val SERVER_PRODUCTION = "http://dummy.restapiexample.com/api/v1"

        fun server(url: String): String {
            if (IS_TESTER)
                return SERVER_DEVELOPMENT + url
            return SERVER_PRODUCTION + url
        }

        fun headers(): HashMap<String, String> {
            val headers = HashMap<String, String>()
            headers["Content-type"] = "application/json;"
            return headers
        }

        /**
         *  Request Method`s
         */

        fun get(api: String, params: HashMap<String, String>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(
                Method.GET, server(api),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
                override fun getParams(): MutableMap<String, String> {
                    return params
                }
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        fun post(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(
                Method.POST, server(api),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    return headers()
                }

                override fun getBody(): ByteArray {
                    return JSONObject(body as Map<*, *>).toString().toByteArray()
                }
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        fun put(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(
                Method.PUT, server(api),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    return headers()
                }

                override fun getBody(): ByteArray {
                    return JSONObject(body as Map<*, *>).toString().toByteArray()
                }
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        fun del(url: String, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(
                Method.DELETE, server(url),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        var API_LIST_POST = "/employees"
        var API_SINGLE_POST = "/employee/{id}" //id
        var API_CREATE_POST = "/create"
        var API_UPDATE_POST = "/update/{id}" //id
        var API_DELETE_POST = "/delete/{id}" //id

        /**
         *  Request Param`s
         */

        fun paramsEmpty(): HashMap<String, String> {
            return HashMap<String, String>()
        }

        fun paramsCreate(employee: Employee): HashMap<String, Any> {
            val params = HashMap<String, Any>()
            params.put("name", employee.employee_name)
            params.put("salary", employee.employee_salary)
            params.put("age", employee.employee_age)
            params.put("image", employee.profile_image)
            return params
        }

        fun paramsUpdate(employee: Employee): HashMap<String, Any> {
            val params = HashMap<String, Any>()
            params.put("id", employee.id)
            params.put("name", employee.employee_name)
            params.put("salary", employee.employee_salary)
            params.put("age", employee.employee_age)
            params.put("image", employee.profile_image)
            return params
        }

        /**
         *  Response Parse`s
         */


    }
}