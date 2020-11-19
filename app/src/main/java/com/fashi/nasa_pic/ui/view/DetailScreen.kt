package com.fashi.nasa_pic.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.fashi.nasa_pic.R
import com.fashi.nasa_pic.ui.adapter.DetailViewPagerAdapter
import com.fashi.nasa_pic.utils.ZoomOutPageTransformer
import com.fashi.nasa_pic.ui.viewmodel.NasaViewModel
import com.fashi.nasa_pic.ui.viewmodel.NasaViewModelFactory

class DetailScreen : AppCompatActivity() {

    private lateinit var nasaViewModel: NasaViewModel
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


        nasaViewModel = ViewModelProvider(
            this,
            NasaViewModelFactory(application)
        ).get(NasaViewModel::class.java)

        val viewPagerAdapter = DetailViewPagerAdapter(this,nasaViewModel.getNasaResponse().value)
        viewPager2.adapter = viewPagerAdapter
        viewPager2.setCurrentItem(clickedPosition,false)


    }


    companion object {
        private const val TAG = "DetailsScreen"
    }


}