package com.ychong.ychongnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.ychong.library.YchongNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity() {
    private lateinit var requestTv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestTv = findViewById(R.id.requestTv)
        requestTv.setOnClickListener {
            GlobalScope.launch {
                requestData()
            }
        }

    }

    private suspend fun requestData() {
        val todo: Todo = withContext(Dispatchers.IO) {
            YchongNet.instance.create(ApiService::class.java).getTodo()
        }
        Log.e("fdafafaf", todo.toString())

    }


}