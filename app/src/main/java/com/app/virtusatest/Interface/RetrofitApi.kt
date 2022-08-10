package com.app.virtusatest.Interface

import com.app.virtusatest.Model.assets
import retrofit2.Call
import retrofit2.http.GET


// Create a interface to define APIs name and method to access any where in code.
interface RetrofitApi {

    @GET("assets")
    fun geteassets(): Call<List<assets>>

}