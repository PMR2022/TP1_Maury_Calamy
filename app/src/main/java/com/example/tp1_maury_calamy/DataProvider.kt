package com.example.tp1_maury_calamy



import com.example.tp1_maury_calamy.DataClass.listActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object DataProvider {
    private val token = "1ae544e6fdef4e71d2a2c3797e8cad13"

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://tomnab.fr/todo-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create<ApiInterface>()

    suspend fun getLists() : listActivity = service.getList()

}