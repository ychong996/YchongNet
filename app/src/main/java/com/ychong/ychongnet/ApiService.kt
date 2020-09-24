package com.ychong.ychongnet

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface ApiService {
    @GET("api/v2/banners")
    suspend fun getTodo(): Todo
}