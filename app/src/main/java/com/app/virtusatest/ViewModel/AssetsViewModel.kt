package com.app.virtusatest.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.virtusatest.Repository.AssetsRepository
import com.app.virtusatest.Model.assets

class AssetsViewModel(application: Application) : AndroidViewModel(application) {

    private var assets_api_call: AssetsRepository? = null
    var assetsResponseLiveData: LiveData<List<assets>?>? = null

    fun init() {
        assets_api_call = AssetsRepository()
        assetsResponseLiveData = assets_api_call!!.getassetsLiveData()
    }

    fun GetAssetsList(): LiveData<List<assets>?>? {
        assets_api_call!!.Get_Assets_List()
        return getassetsResponseLiveData()
    }

    fun getassetsResponseLiveData(): LiveData<List<assets>?>? {
        return assetsResponseLiveData
    }
}