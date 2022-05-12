package com.example.m6l4taskskt.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m6l4taskskt.Activity.MainActivity
import com.example.m6l4taskskt.Model.Employee
import com.example.m6l4taskskt.R

class EmployeeAdapter(var activity: MainActivity, var items:ArrayList<Employee>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val poster = items[position]

        if (holder is StoryViewHolder) {
            val tv_name = holder.tv_name
            val tv_salary = holder.tv_salary
            val tv_age = holder.tv_age
            val linearDelete = holder.linearDelete

            linearDelete.setOnLongClickListener {
                activity.dialogPoster(poster)
                false
            }

            tv_name.text = poster.employee_name.toUpperCase()
            tv_salary.text = poster.employee_salary.toUpperCase()
            tv_age.text = poster.employee_age.toUpperCase()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("CutPasteId")
    class StoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tv_name:TextView
        var tv_salary:TextView
        var tv_age:TextView
        var linearDelete:LinearLayout
        var viewForeground:LinearLayout

        init {
            tv_name = view.findViewById(R.id.tv_name)
            tv_salary = view.findViewById(R.id.tv_salary)
            tv_age = view.findViewById(R.id.tv_age)
            linearDelete = view.findViewById(R.id.view_foreground)
            viewForeground = view.findViewById(R.id.view_foreground)
        }
    }
}