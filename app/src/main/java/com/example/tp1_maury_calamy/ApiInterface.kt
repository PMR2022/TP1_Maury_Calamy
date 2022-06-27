package com.example.tp1_maury_calamy
import com.example.tp1_maury_calamy.DataClass.ItemApi
import com.example.tp1_maury_calamy.DataClass.listList
import com.example.tp1_maury_calamy.DataClass.listApi
import com.example.tp1_maury_calamy.DataClass.listItem
import retrofit2.http.*


interface ApiInterface {
    @Headers("hash: 1ae544e6fdef4e71d2a2c3797e8cad13")
    @GET("lists")
    suspend fun getList() : listList

    @Headers("hash: 1ae544e6fdef4e71d2a2c3797e8cad13")
    @GET("lists/{idList}/items")
    suspend fun getItems(@Path("idList") idList: Any): listItem

    @Headers("hash: 1ae544e6fdef4e71d2a2c3797e8cad13")
    @POST("lists")
    suspend fun createList(@Query("label") listName: Any) : listApi

    @Headers("hash: 1ae544e6fdef4e71d2a2c3797e8cad13")
    @POST("lists/{idList}/items")
    suspend fun createItem(@Path("idList") idList:Any ,@Query("label") itemName: Any) : ItemApi

    @Headers("hash: 1ae544e6fdef4e71d2a2c3797e8cad13")
    @PUT("lists/{idList}/items/{idItem}")
    suspend fun check(@Path("idList") idList:Any,@Path("idItem") idItem: Any, @Query("check") checked : Any)

}