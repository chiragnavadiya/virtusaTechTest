package com.app.virtusatest.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.virtusatest.Interface.RetrofitApi
import com.app.virtusatest.Model.assets
import com.app.virtusatest.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssetsRepository {

    // prepare call in Retrofit 2.0
    val retrofit = RetrofitClient().getRetrofitClient()

    // prepare call in Retrofit 2.0
    val retroUrlApi = retrofit!!.create(RetrofitApi::class.java)
    private var AssetsLiveData: MutableLiveData<List<assets>>? = MutableLiveData<List<assets>>()

    fun Get_Assets_List() {
        //Call And Convert
        retroUrlApi.geteassets().enqueue(object : Callback<List<assets>> {
            override fun onResponse(
                call: Call<List<assets>>,
                response: Response<List<assets>>
            ) {
                if (response.body() != null && response.code() == 200) {
                    AssetsLiveData!!.postValue(response.body())
                } else {
                    AssetsLiveData!!.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<assets>>, t: Throwable) {
                AssetsLiveData!!.postValue(null)
            }
        })
    }

    fun getassetsLiveData(): LiveData<List<assets>?>? {
        return AssetsLiveData
    }
}