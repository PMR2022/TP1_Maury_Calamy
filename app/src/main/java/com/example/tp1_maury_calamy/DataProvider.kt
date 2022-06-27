package com.example.tp1_maury_calamy


import com.example.tp1_maury_calamy.DataClass.ListeToDo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
/*
object DataProvider {


    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://tomnab.fr/todo-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInterface = retrofit.create<ApiInterface>()

    suspend fun getLists() :  ListeToDo = apiInterface.getList()

}*/