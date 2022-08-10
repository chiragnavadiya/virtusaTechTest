package com.app.virtusatest.Network

import com.bumptech.glide.load.Key
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException

class HeaderInterceptor : Interceptor {

    val UTF8 = Charset.forName(Key.STRING_CHARSET_NAME)

    val X_TOKEN = "2483BF86-6C03-4068-A56F-67BE249B7EA3"

    // for the reference if above key not work or expired
    // val X_TOKEN = "887C3C17-0B27-487A-B828-D6F2415D21E7"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request().newBuilder().addHeader("X-CoinAPI-Key", X_TOKEN).build()
        val response = chain.proceed(request)
        try {
            showResponseBody(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

    open fun showResponseBody(response: Response) {
        val responseBody = response.body()
        val source = responseBody!!.source()
        try {
            source.request(Long.MAX_VALUE)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val contentType = responseBody!!.contentType()
        if (contentType != null) {
            try {
                contentType.charset(UTF8)
            } catch (e2: UnsupportedCharsetException) {

            }
        }
        if (responseBody!!.contentLength() != 0L) {

        }

    }
}