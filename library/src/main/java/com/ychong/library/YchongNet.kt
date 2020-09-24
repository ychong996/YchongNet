package com.ychong.library

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.util.concurrent.TimeUnit
import kotlin.math.log

class YchongNet {

    private val retrofit:Retrofit
    private var requestInterceptor:Interceptor
    private var responseInterceptor:Interceptor
    //private var logInterceptor:Interceptor

    companion object{
        val instance:YchongNet by lazy { YchongNet() }
    }

    init {
        //请求拦截器
        requestInterceptor = Interceptor { chain ->
            val request = chain.request()
            val builder:Request.Builder = request.newBuilder()
            //做请求头处理
            chain.proceed(builder.build())
        }
        //响应拦截器
        responseInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            //处理响应头
            response
        }
       retrofit = getRetrofit()
    }

    private fun getRetrofit() : Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://gank.io/")
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun getOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(100000,TimeUnit.MICROSECONDS)
            .readTimeout(100000,TimeUnit.MICROSECONDS)
            .writeTimeout(100000,TimeUnit.MICROSECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(responseInterceptor)
            .build()
    }

    //创建具体的服务实例
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }

}