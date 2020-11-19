package com.fashi.nasa_pic.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fashi.nasa_pic.data.model.NasaModelClass
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class NasaViewModel(application: Application) : AndroidViewModel(application)  {

    companion object {
        private val TAG = "NasaRepository"
    }

    private val viewItems: ArrayList<NasaModelClass> = ArrayList()

    fun getNasaResponse(): MutableLiveData<List<NasaModelClass>> {
        setItemsFromJSON()
        val data: MutableLiveData<List<NasaModelClass>> = MutableLiveData()
        data.value = viewItems
        return data
    }

    private fun setItemsFromJSON() {
        try {
            val jsonDataString = readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val name = itemObj.getString("title")
                val image = itemObj.getString("url")
                val explanation = itemObj.getString("explanation")
                val date = itemObj.getString("date")
                // val date = itemObj.getString("date")
                val images = NasaModelClass(name,image, explanation, date)
                viewItems.add(images)
            }
        } catch (e: JSONException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        }
    }

    @Throws(IOException::class)
    private fun readJSONDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String? = null
            val json = getApplication<Application>().assets.open("data.json")
            inputStream = json
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                builder.append(jsonString)
            }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
        return String(builder)
    }
}

