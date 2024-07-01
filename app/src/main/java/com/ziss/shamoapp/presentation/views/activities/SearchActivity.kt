package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.common.hideSoftKeyboard
import com.ziss.shamoapp.databinding.ActivitySearchBinding
import com.ziss.shamoapp.presentation.adapters.ProductItemAdapter
import com.ziss.shamoapp.presentation.viewmodels.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupSearchedList()

        binding.etSearch.addTextChangedListener { query ->
            viewModel.setQuery(query.toString())
            Log.d(TAG, "onCreate: $query")
        }
        binding.btnBack.setOnClickListener { finish() }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideSoftKeyboard()
        binding.etSearch.clearFocus()
        return super.dispatchTouchEvent(ev)
    }

    private fun setupSearchedList() {
        val adapter = ProductItemAdapter()
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = MarginVerticalItemDecoration(30)

        adapter.setOnItemClickCallback(object : ProductItemAdapter.OnItemClickCallback {
            override fun onItemClicked() {
                ProductDetailActivity.start(this@SearchActivity, 1)
            }
        })

        binding.apply {
            rvSearchedList.adapter = adapter
            rvSearchedList.layoutManager = layoutManager
            rvSearchedList.addItemDecoration(itemDecoration)
        }

        viewModel.query.observe(this) { query ->
            setSearchedListVisibility(query.isNotEmpty())
        }
    }

    private fun setSearchedListVisibility(isVisible: Boolean) {
        binding.rvSearchedList.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    companion object {
        private val TAG = SearchActivity::class.java.simpleName

        @JvmStatic
        fun start(context: Context) {
            Intent(context, SearchActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}