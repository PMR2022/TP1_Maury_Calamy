package com.example.tp1_maury_calamy
import com.example.tp1_maury_calamy.DataClass.ListeToDo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("lists?hash=1ae544e6fdef4e71d2a2c3797e8cad13")
    fun getList() : Call<List<ListeToDo>>

    companion object {

        var BASE_URL = "http://tomnab.fr/todo-api/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}