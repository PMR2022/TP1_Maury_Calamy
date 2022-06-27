package com.example.tp1_maury_calamy
import com.example.tp1_maury_calamy.DataClass.listActivity
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiInterface {
    @Headers("hash: 1ae544e6fdef4e71d2a2c3797e8cad13")
    @GET("lists")
    suspend fun getList() : listActivity

}