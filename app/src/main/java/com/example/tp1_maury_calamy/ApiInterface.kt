package com.example.tp1_maury_calamy
import com.example.tp1_maury_calamy.DataClass.*
import retrofit2.http.*


interface ApiInterface {
    @GET("lists")
    suspend fun getList(@Header("hash") hash : String) : listList

    @GET("lists/{idList}/items")
    suspend fun getItems(@Header("hash") hash : String, @Path("idList") idList: Any): listItem

    @GET("lists/1113/items")
    suspend fun getItems1113(@Header("hash") hash : String): listItem

    @POST("lists")
    suspend fun createList(@Header("hash") hash : String,@Query("label") listName: Any) : listApi

    @POST("lists/{idList}/items")
    suspend fun createItem(@Header("hash") hash : String, @Path("idList") idList:Any ,@Query("label") itemName: Any) : ItemApi

    @PUT("lists/{idList}/items/{idItem}")
    suspend fun check(@Header("hash") hash : String, @Path("idList") idList:Any,@Path("idItem") idItem: Any, @Query("check") checked : Any)

    @POST("authenticate")
    suspend fun auth(@Query("user")login:String,@Query("password")password:String) : authentification
}