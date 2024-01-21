package com.example.node_wifi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class SensorDataViewModel:ViewModel() {
    private val _sensorData = MutableLiveData<SensorData>()
    val sensorData: LiveData<SensorData> = _sensorData

//    private val _temp =  MutableLiveData<Double>()
//    private val _humidity = MutableLiveData<Int>()


    private var socket: Socket? = null

    init {
        startDataStream()
    }

    private fun startDataStream() {
        Thread {
            try {
                socket = Socket("192.168.166.98", 8080) // Replace with NodeMCU's IP and assigna a port number
                val inputStream = socket!!.getInputStream() //Getting input from that port
                val reader = BufferedReader(InputStreamReader(inputStream))
                //println(reader)
                while (true) {
                    val line = reader.readLine() ?: break       // Read line by line the input comming from socket
                    val jsonObject = JSONObject(line)
                    val temperature = jsonObject.getDouble("temperature")   //Reading perticular values from JSON object
                    val humidity = jsonObject.getInt("humidity")
                    val sensorData = SensorData(temperature, humidity)
                    _sensorData.postValue(sensorData)   // updating Values in UI

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    fun stopDataStream() {
        socket?.close()
    }
}
