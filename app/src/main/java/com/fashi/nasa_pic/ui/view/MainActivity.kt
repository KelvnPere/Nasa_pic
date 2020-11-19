package com.fashi.nasa_pic.ui.view


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fashi.nasa_pic.R
import com.fashi.nasa_pic.ui.adapter.NasaAdapter
import com.fashi.nasa_pic.ui.viewmodel.NasaViewModel
import com.fashi.nasa_pic.ui.viewmodel.NasaViewModelFactory

class MainActivity() : AppCompatActivity() {

    private lateinit var nasaViewModel: NasaViewModel
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById<View>(R.id.image_rv) as RecyclerView

        //Getting the instance of the Viewmodel

        nasaViewModel = ViewModelProvider(
            this,
            NasaViewModelFactory(application)
        ).get(NasaViewModel::class.java)


        nasaViewModel.getNasaResponse().observe(this, Observer {
            mAdapter?.notifyDataSetChanged()
        })

        initRecyclerView()
    }

    // Initialize RecyclerView
    private fun initRecyclerView() {
        mAdapter = NasaAdapter(this, nasaViewModel.getNasaResponse().value)
        mRecyclerView!!.setHasFixedSize(true)
        layoutManager = GridLayoutManager(this, 2)
        mRecyclerView!!.layoutManager = layoutManager
        mRecyclerView!!.adapter = mAdapter
    }

}