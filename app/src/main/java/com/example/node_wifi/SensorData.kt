package com.example.node_wifi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SensorData(
    val strTemp:Double,
    val strHumidity:Int
):Parcelable
// Data class to store the values coming from Node MCU.