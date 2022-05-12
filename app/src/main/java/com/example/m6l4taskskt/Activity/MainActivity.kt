package com.example.m6l4taskskt.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.m6l4taskskt.Adapter.EmployeeAdapter
import com.example.m6l4taskskt.Helper.Logger
import com.example.m6l4taskskt.Model.Employee
import com.example.m6l4taskskt.Model.EmployeeResp
import com.example.m6l4taskskt.Network.RetrofitHttp
import com.example.m6l4taskskt.Network.VolleyHandler
import com.example.m6l4taskskt.Network.VolleyHttp
import com.example.m6l4taskskt.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var recyclerView: RecyclerView
    private var posters = ArrayList<Employee>()
    lateinit var progressBar: ProgressBar
    lateinit var name:String
    lateinit var salary:String
    lateinit var age:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }
    fun initViews(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(this,1))
        progressBar = findViewById(R.id.progress)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floating)
        progressBar.visibility = View.GONE

        apiPosterListVolley()

        floatingActionButton.setOnClickListener {
            callCreateActivity()
        }
    }

    fun refreshAdapter(employee: ArrayList<Employee>){
        val adapter = EmployeeAdapter(this,employee)
        recyclerView.setAdapter(adapter)
    }

    fun dialogPoster(employee: Employee){
        AlertDialog.Builder(this)
            .setTitle("Delete Poster")
            .setMessage("Are you sure you want to delete this poster?")
            .setPositiveButton(
                android.R.string.yes
            ){ dialog,which -> apiPosterDeleteVolley(employee) }
            .setNegativeButton(android.R.string.no){dialog,which -> callUpdateActivity(employee)}
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


    fun callCreateActivity(){
        val intent = Intent(this,CreateActivity::class.java)
        createLauncher.launch(intent)
    }

    fun callUpdateActivity(employee: Employee){
        val intent = Intent(this,UpdateActivity::class.java)
        intent.putExtra("poster",employee)

        updateLauncher.launch(intent)
    }

    var createLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            name = data!!.getStringExtra("title")!!
            salary = data.getStringExtra("body")!!
            age = data.getStringExtra("age")!!
            apiPosterPostVolley()

        }
    }

    var updateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            val employee:Employee = data!!.getSerializableExtra("poster") as Employee
            apiPosterPutVolley(employee)

        }
    }

    fun apiPosterListVolley(){
        progressBar.visibility = View.VISIBLE
        VolleyHttp.get(VolleyHttp.API_LIST_POST,VolleyHttp.paramsEmpty(),object : VolleyHandler{
            override fun onSuccess(response: String?) {
                val employeeArray = Gson().fromJson(response,Array<Employee>::class.java)
                posters.clear()
                posters.addAll(employeeArray)
                progressBar.visibility = View.GONE
                refreshAdapter(posters)
            }

            override fun onError(error: String?) {
                Logger.d("@@@",error)
            }

        })
    }

    fun apiPosterPutVolley(employee: Employee){

        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + employee.id, VolleyHttp.paramsUpdate(employee), object : VolleyHandler{
            override fun onSuccess(response: String?) {
                apiPosterListVolley()
            }

            override fun onError(error: String?) {

            }

        })
    }

    fun apiPosterPostVolley(){
        val poster = Employee(1,name,salary,age,"")

        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster),object : VolleyHandler{
            override fun onSuccess(response: String?) {
                apiPosterListVolley()
            }

            override fun onError(error: String?) {
                Logger.d("@@@",error!!)
            }

        })
    }

    fun apiPosterDeleteVolley(employee: Employee){
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + employee.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("@@@",response)
                apiPosterListVolley()
            }

            override fun onError(error: String?) {
                Logger.d("@@@",error)
            }

        })
    }

    fun workWithVolley(){
        //textView = findViewById(R.id.textView)

        //val poster = Employee(1,"John",1200,32,"")



        /*  VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(),object : VolleyHandler {
          override fun onSuccess(response: String?) {
              Logger.d("ValleyHttp",response!!)
              textView.text = response
          }

          override fun onError(error: String?) {
              Logger.d("ValleyHttp",error!!)
              textView.text = error
          }

      }) */

        /*  VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster),object : VolleyHandler{
              override fun onSuccess(response: String?) {
                  Logger.d("@@@",response!!)
                  textView.text = response
              }

              override fun onError(error: String?) {
                  Logger.d("@@@",error!!)
                  textView.text = error
              }

          }) */

        /*  VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster), object : VolleyHandler{
              override fun onSuccess(response: String?) {
                  Log.d("@@@",response!!)
                  textView.text = response
              }

              override fun onError(error: String?) {
                  Log.d("@@@",error!!)
              }

          }) */

        /*  VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler{
              override fun onSuccess(response: String?) {
                  Log.d("@@@",response!!)
                  textView.text = response
              }

              override fun onError(error: String?) {
                  Log.d("@@@",error!!)
              }

          }) */
    }

    fun workWithRetrofit() {

        //val poster = Employee(2, "John", 1200, 32, "")

      /*  RetrofitHttp.employeeService.listPost().enqueue(object : Callback<ArrayList<EmployeeResp>> {
            override fun onResponse(call: Call<ArrayList<EmployeeResp>>, response: Response<ArrayList<EmployeeResp>>) {
                Logger.d("@@@",response.body().toString())
                textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<ArrayList<EmployeeResp>>, t: Throwable) {
                Logger.d("@@@",t.message.toString())
                textView.text = t.message.toString()
            }

        }) */


       /*  RetrofitHttp.employeeService.createPost(poster).enqueue(object : Callback<EmployeeResp>{
             override fun onResponse(call: Call<EmployeeResp>, response: Response<EmployeeResp>) {
                 Logger.d("@@@",response.body().toString())
                 textView.text = response.body().toString()
             }

             override fun onFailure(call: Call<EmployeeResp>, t: Throwable) {
                 Logger.d("@@@",t.message.toString())
                 textView.text = t.message.toString()
             }

         }) */

        /*  RetrofitHttp.employeeService.updatePost(poster.id,poster).enqueue(object : Callback<EmployeeResp>{
              override fun onResponse(call: Call<EmployeeResp>, response: Response<EmployeeResp>) {
                  Logger.d("@@@",response.body().toString())
                  textView.text = response.body().toString()
              }

              override fun onFailure(call: Call<EmployeeResp>, t: Throwable) {
                  Logger.d("@@@",t.message.toString())
                  textView.text = t.message.toString()
              }

          }) */

        /* RetrofitHttp.posterService.deletePost(poster.id).enqueue(object : Callback<PosterResp>{
             override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                 Logger.d("@@@",response.body().toString())
                 textView.text = response.body().toString()
             }

             override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                 Logger.d("@@@",t.message.toString())
                 textView.text = t.message.toString()
             }

         }) */
    }
}