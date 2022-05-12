package com.example.m6l4taskskt.Helper

import android.util.Log
import com.example.m6l4taskskt.Network.VolleyHttp

object Logger {

    fun d(tag: String?, msg: String?) {
        if (VolleyHttp.IS_TESTER) Log.d(tag, msg!!)
    }

    fun i(tag: String?, msg: String?) {
        if (VolleyHttp.IS_TESTER) Log.i(tag, msg!!)
    }

    fun v(tag: String?, msg: String?) {
        if (VolleyHttp.IS_TESTER) Log.v(tag, msg!!)
    }

    fun e(tag: String?, msg: String?) {
        if (VolleyHttp.IS_TESTER) Log.e(tag, msg!!)
    }
}