package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.common.toLocalCurrency
import com.ziss.shamoapp.databinding.ActivityCheckoutBinding
import com.ziss.shamoapp.presentation.adapters.CheckoutProductItemAdapter

class CheckoutActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            binding.bottomAppBar.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        setupCheckoutProductItemList()
        setupAddressAndPaymentSummary()

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun setupCheckoutProductItemList() {
        val adapter = CheckoutProductItemAdapter(this)
        val layoutManager = object : LinearLayoutManager(this, VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val itemDecoration = MarginVerticalItemDecoration(30)

        binding.apply {
            rvCheckoutProducts.adapter = adapter
            rvCheckoutProducts.layoutManager = layoutManager
            rvCheckoutProducts.addItemDecoration(itemDecoration)
        }
    }

    private fun setupAddressAndPaymentSummary() {
        binding.apply {
            checkoutAddressItem.ivStoreLocation.text = getString(R.string.shamo_official_location)
            checkoutAddressItem.ivCustomerLocation.text = getString(R.string.customer_address)

            tvPaymentQty.text = getString(R.string.quantity, 2)
            tvPaymentPrice.text = 25000.toLocalCurrency()
            tvPaymentShipping.text = getString(R.string.free)
            tvPaymentTotalPrice.text = 25000.toLocalCurrency()
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            Intent(context, CheckoutActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}