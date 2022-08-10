package com.app.virtusatest.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class assets {

    @SerializedName("asset_id")
    @Expose
    var asset_id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("price_usd")
    @Expose
    var price_usd: Float? = null

    @SerializedName("data_start")
    @Expose
    var data_start: Date? = null

    @SerializedName("type_is_crypto")
    @Expose
    var type_is_crypto: Int? = null

    @SerializedName("data_end")
    @Expose
    var data_end: Date? = null

    @SerializedName("error")
    @Expose
    var error: String? = null
}