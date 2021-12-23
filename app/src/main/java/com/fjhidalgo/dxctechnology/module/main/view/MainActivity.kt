package com.fjhidalgo.dxctechnology.module.main.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fjhidalgo.dxctechnology.data.network.model.ImageModel
import com.fjhidalgo.dxctechnology.databinding.ActivityMainBinding
import com.fjhidalgo.dxctechnology.module.main.adapter.ImagesAdapter
import com.fjhidalgo.dxctechnology.module.main.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mainActivityViewModel: MainActivityViewModel? = null

    private var adapter: ImagesAdapter? = null
    private var imagesList: ArrayList<ImageModel> = ArrayList<ImageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        subscribeObservers()
        setListener()
        setRecyclerImages()
    }

    private fun setListener() {

        binding.etFind.setOnEditorActionListener { v, actionId, event ->

            if(actionId == EditorInfo.IME_ACTION_SEARCH){

                mainActivityViewModel!!.getSearchWord(binding.etFind.text.toString())

                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0)

                true
            } else {
                false
            }
        }
    }

    private fun setRecyclerImages(){

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.setHasFixedSize(true)
            it.itemAnimator = DefaultItemAnimator()

            adapter = ImagesAdapter(this, layoutInflater, imagesList, this)
            it.adapter = adapter
            adapter?.notifyDataSetChanged()
        }

    }

    private fun subscribeObservers() {

        mainActivityViewModel!!.imageListResultLiveData.observe(this, {

            if (it.size == 0) {

                binding.viewNotResult.visibility = View.VISIBLE

            } else {

                binding.viewNotResult.visibility = View.INVISIBLE
                imagesList = ArrayList(it)
                adapter!!.imageList = imagesList
                adapter!!.notifyDataSetChanged()
            }
        })

        mainActivityViewModel!!.errorListResultLiveData.observe(this, {

            binding.viewNotResult.visibility = View.VISIBLE
        })
    }
}