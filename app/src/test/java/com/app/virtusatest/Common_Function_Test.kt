package com.app.virtusatest

import com.app.virtusatest.Utils.CommonFunctions
import org.junit.Test
import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Common_Function_Test {
    private val cf: CommonFunctions = CommonFunctions()

    @Test
    fun gettime() {
        assertEquals(SimpleDateFormat("HH:mm").format(Date()), cf.gettime(Date()))
    }

    @Test
    fun Formatdate() {
        assertEquals(SimpleDateFormat("dd/MM/yyyy").format(Date()), cf.Formatdate(Date()))
    }
}