package com.ziss.shamoapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ziss.shamoapp.presentation.views.fragments.AllProductFragment
import com.ziss.shamoapp.presentation.views.fragments.ProductByCategoryFragment

class ProductPageAdapter(activity: FragmentActivity, private val itemCount: Int) :
    FragmentStateAdapter(activity) {
    override fun getItemCount() = itemCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllProductFragment()
            else -> ProductByCategoryFragment.startWithParams(position)
        }
    }
}