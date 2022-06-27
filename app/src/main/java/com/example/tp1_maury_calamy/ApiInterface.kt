package com.example.tp1_maury_calamy
import com.example.tp1_maury_calamy.DataClass.ListeToDo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("hash: 1ae544e6fdef4e71d2a2c3797e8cad13")
    @GET("lists")
    suspend fun getList() : List<ListeToDo>

}