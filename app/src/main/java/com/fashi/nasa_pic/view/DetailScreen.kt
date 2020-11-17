package com.fashi.nasa_pic.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.fashi.nasa_pic.R
import com.fashi.nasa_pic.adapter.DetailViewPagerAdapter
import com.fashi.nasa_pic.adapter.ZoomOutPageTransformer
import com.fashi.nasa_pic.model.ImageModelClass
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class DetailScreen : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private val viewItems: MutableList<Any> = ArrayList()
    private val clickedPosition: Int by lazy {
        intent.getIntExtra("position", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)

        viewPager2 = findViewById(R.id.detail_view_pager)
        viewPager2.setPageTransformer(ZoomOutPageTransformer())

        val viewPagerAdapter = DetailViewPagerAdapter(this,viewItems)
        viewPager2.adapter = viewPagerAdapter
        viewPager2.setCurrentItem(clickedPosition,false)
        addItemsFromJSON()

    }

    private fun addItemsFromJSON() {
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
                val images = ImageModelClass(name,image, explanation, date)
                viewItems.add(images)
            }
        } catch (e: JSONException) {
            Log.d(DetailScreen.TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d(DetailScreen.TAG, "addItemsFromJSON: ", e)
        }
    }

    @Throws(IOException::class)
    private fun readJSONDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String? = null
            inputStream = assets.open("data.json")
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

    companion object {
        private const val TAG = "DetailsScreen"
    }


}