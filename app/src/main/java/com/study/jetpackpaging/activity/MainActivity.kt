package com.study.jetpackpaging.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.study.jetpackpaging.adapter.MovieAdapter
import com.study.jetpackpaging.databinding.ActivityMainBinding
import com.study.jetpackpaging.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val movieViewModel by viewModels<MovieViewModel>()
    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val movieAdapter = MovieAdapter(this)

        mBinding.apply {
            rv.adapter = movieAdapter

        }
        lifecycleScope.launchWhenCreated {
            movieViewModel.loadMovie().collectLatest {
                movieAdapter.submitData(it)
            }
        }

    }
}