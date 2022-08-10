package com.app.virtusatest

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.app.virtusatest.Network.InternetConnection
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class Internet_connection_Test {

    lateinit var appContext: Context

    @Before
    fun setup() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun CheckInternetPermissionandstatus() {
        Assert.assertTrue(InternetConnection().isNetworkAvailable(appContext))
    }
    
}