package com.app.virtusatest

import com.app.virtusatest.Interface.RetrofitApi
import com.app.virtusatest.Model.assets
import com.app.virtusatest.Network.RetrofitClient
import com.app.virtusatest.Utils.CommonFunctions
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*
import java.util.Collections.list

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class APIs_Unit_Test {
    private val cf: CommonFunctions = CommonFunctions()

    // prepare call in Retrofit 2.0
    val retrofit = RetrofitClient().getRetrofitClient()

    // prepare call in Retrofit 2.0
    val retroUrlApi = retrofit!!.create(RetrofitApi::class.java)

    @Test
    fun `can get success API Call`() {
        // call the api
        //Call And Convert
        var call: Call<List<assets>> = retroUrlApi.geteassets()
        // verify the response is OK
        assertEquals(200, call.execute().code())
    }

    @Test
    fun `can get server error`() {
        // call the api
        //Call And Convert
        var call: Call<List<assets>> = retroUrlApi.geteassets()
        // verify the response is OK
        assertSame(500, call.execute().code())
    }

    @Test
    fun `can get List in array `() {
        // call the api
        //Call And Convert
        var call: Call<List<assets>> = retroUrlApi.geteassets()
        // verify the response is OK
        assertNotNull(call.execute().body())
    }
}