package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.MarginHorizontalItemDecoration
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ActivityProductDetailBinding
import com.ziss.shamoapp.presentation.adapters.FamiliarItemAdapter
import com.ziss.shamoapp.presentation.adapters.GalleryAdapter

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityProductDetailBinding.inflate(layoutInflater) }

    private val galleries = arrayListOf<Int>()
    private val indicatorParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
    ).apply {
        setMargins(4, 0, 4, 0)
    }
    private val galleryChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setCurrentIndicator(position % galleries.size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        setupToolbar()
        setupGallery()
        setupProductContent()
        setupFamiliarList()

        binding.btnBack.setOnClickListener(this)
        binding.btnGoToChat.setOnClickListener(this@ProductDetailActivity)
        binding.btnAddToCart.setOnClickListener(this@ProductDetailActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.vpGallery.unregisterOnPageChangeCallback(galleryChangeListener)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnBack.id -> finish()
            binding.btnGoToChat.id -> {
                ChatDetailActivity.start(this@ProductDetailActivity, 1)
            }

            binding.btnAddToCart.id -> {
                CartActivity.start(this@ProductDetailActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionToCart -> {
                CartActivity.start(this)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
    }

    private fun setupGallery() {
        galleries.addAll(
            arrayListOf(
                R.drawable.shoes_example_1,
                R.drawable.shoes_example_2,
                R.drawable.shoes_example_3
            )
        )

        binding.apply {
            vpGallery.adapter = GalleryAdapter(galleries)
            vpGallery.offscreenPageLimit = 3
            vpGallery.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
            vpGallery.registerOnPageChangeCallback(galleryChangeListener)
        }

        setupCarouselIndicator(galleries.size)
        setCurrentIndicator(0)
    }

    private fun setupCarouselIndicator(count: Int) {
        val dots = Array(count) { ImageView(this) }
        dots.forEach {
            it.setImageResource(R.drawable.carousel_indicator_unselected)
            binding.carouselIndicator.addView(it, indicatorParams)
        }
    }

    private fun setCurrentIndicator(position: Int) {
        for (i in 0 until binding.carouselIndicator.childCount) {
            val dotIndicator = binding.carouselIndicator.getChildAt(i) as ImageView
            dotIndicator.setImageResource(
                if (i == position) {
                    R.drawable.carousel_indicator_selected
                } else {
                    R.drawable.carousel_indicator_unselected
                }
            )
        }
    }

    private fun setupProductContent() {
        binding.apply {
            tvProductName.text = "TERREX URBAN LOW"
            tvProductCategory.text = "Hiking"
            tvProductPrice.text = 25000.toLocalCurrency()
            tvDescription.text =
                "Unpaved trails and mixed surfaces are easy when you have the traction and support you need. Casual enough for the daily commute. Unpaved trails and mixed surfaces are easy when you have the traction and support you need. Casual enough for the daily commute."
        }
    }

    private fun setupFamiliarList() {
        val adapter = FamiliarItemAdapter()
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val itemDecoration = MarginHorizontalItemDecoration(50)

        binding.apply {
            rvFamiliarList.adapter = adapter
            rvFamiliarList.layoutManager = layoutManager
            rvFamiliarList.addItemDecoration(itemDecoration)
        }
    }

    companion object {
        private const val EXTRA_ID = "extra_id"

        @JvmStatic
        fun start(context: Context, id: Int) {
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
                context.startActivity(this)
            }
        }
    }
}