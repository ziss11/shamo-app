package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.common.makeToast
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ActivityCartBinding
import com.ziss.shamoapp.presentation.adapters.CartItemAdapter

class CartActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityCartBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            binding.bottomAppBar.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        setupCartList()
        setupSubtotal()

        binding.btnBack.setOnClickListener(this)
        binding.btnExploreStore.setOnClickListener(this)
        binding.btnGoToCheckout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnBack.id -> finish()
            binding.btnExploreStore.id -> finish()
            binding.btnGoToCheckout.id -> CheckoutActivity.start(this@CartActivity)
        }
    }

    private fun setupCartList() {
        val adapter = CartItemAdapter()
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = MarginVerticalItemDecoration(30)

        adapter.apply {
            setOnItemClickCallback(object : CartItemAdapter.OnItemClickCallback {
                override fun onItemClicked() {
                    ProductDetailActivity.start(this@CartActivity, 1)
                }
            })
            setOnCartItemActionsCallback(object : CartItemAdapter.OnCartItemActionsCallback {
                override fun increaseQuantity() {
                    "Increasing Quantity".makeToast(this@CartActivity)
                }

                override fun decreaseQuantity() {
                    "Decreasing Quantity".makeToast(this@CartActivity)
                }

                override fun remove() {
                    "Remove Item from Cart".makeToast(this@CartActivity)
                }
            })
        }

        binding.apply {
            rvCarts.adapter = adapter
            rvCarts.layoutManager = layoutManager
            rvCarts.addItemDecoration(itemDecoration)
        }

        setCartListVisibility(true)
    }

    private fun setCartListVisibility(isVisible: Boolean) {
        binding.apply {
            rvCarts.visibility = if (isVisible) View.VISIBLE else View.GONE
            emptyStateContainer.visibility = if (isVisible) View.GONE else View.VISIBLE
        }
    }

    private fun setupSubtotal() {
        binding.tvSubTotal.text = 50000.toLocalCurrency()
    }

    companion object {
        private val TAG = CartActivity::class.java.simpleName

        @JvmStatic
        fun start(context: Context) {
            Intent(context, CartActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}