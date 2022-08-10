package com.app.virtusatest.Utils

import java.text.SimpleDateFormat
import java.util.*

class CommonFunctions {

    // Define a method to format date to dd/MM/yyyy
    fun Formatdate(fromdate: Date): String {
        return SimpleDateFormat("dd/MM/yyyy").format(fromdate)
    }

    // Define a method to get time from date and time
    fun gettime(fromdate: Date): String {
        return SimpleDateFormat("HH:mm").format(fromdate)
    }
}