package com.example.usupbekov_adilet_5_3hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.usupbekov_adilet_5_3hw.databinding.ActivityMainBinding
import com.example.usupbekov_adilet_5_3hw.pixa.ImageModel
import com.example.usupbekov_adilet_5_3hw.pixa.PixaAdapter
import com.example.usupbekov_adilet_5_3hw.pixa.PixaModel
import com.example.usupbekov_adilet_5_3hw.pixa.RetrofitService
import com.google.android.gms.common.api.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var page = 1
    private var count = 0
    val adapter = PixaAdapter(arrayListOf())
    var globalList = arrayListOf<ImageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.pbLoading.visibility = GONE
        setContentView(binding.root)
        initListeners()
        setUpRV()
    }

    private fun setUpRV() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun initListeners() {
        binding.apply {
            btnSearch.setOnClickListener {
                pbLoading.visibility = VISIBLE
                page = 1
                adapter.list.clear()
                requestImages(page)
            }
            btnNextPage.setOnClickListener {
                page++
                requestImages(page)
            }
            nestedScrollView.setOnScrollChangeListener(View.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.measuredHeight - v.measuredHeight) {
                    count++
                    pbLoading.visibility = VISIBLE
                    if (count < 20) {
                        page++
                        requestImages(page)
                    }
                }
            })
        }
    }

    private fun ActivityMainBinding.requestImages(page: Int) {
        RetrofitService().api.getImages(etSearchWord.text.toString(), page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    response.body()?.let {
                        globalList = it.hits
                        adapter.addImages(globalList)
                        binding.recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {

                }
            })
    }
}
