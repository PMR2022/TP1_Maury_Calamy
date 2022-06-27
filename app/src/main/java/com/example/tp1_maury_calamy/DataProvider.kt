package com.example.tp1_maury_calamy


import com.example.tp1_maury_calamy.DataClass.ListeToDo
import com.example.tp1_maury_calamy.DataClass.list
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

object DataProvider {
    private val token = "1ae544e6fdef4e71d2a2c3797e8cad13"

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://tomnab.fr/todo-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create<ApiInterface>()

    suspend fun getLists() : list = service.getList()

}