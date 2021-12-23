package com.fjhidalgo.dxctechnology.module.main.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fjhidalgo.dxctechnology.databinding.ActivityMainBinding
import com.fjhidalgo.dxctechnology.module.main.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        setListener()
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

    private fun subscribeObservers() {


    }
}