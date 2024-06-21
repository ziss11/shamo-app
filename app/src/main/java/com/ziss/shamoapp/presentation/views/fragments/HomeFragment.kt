package com.ziss.shamoapp.presentation.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textview.MaterialTextView
import com.ziss.shamoapp.R
import com.ziss.shamoapp.databinding.FragmentHomeBinding
import com.ziss.shamoapp.presentation.adapters.ProductPageAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbarText()
        setUpTabLayout()
    }

    private fun setUpToolbarText() {
        binding.toolbarTitle.text = getString(R.string.home_toolbar_title, "Azis")
        binding.toolbarSubtitle.text = "@ziss"
    }

    @SuppressLint("InflateParams")
    private fun setUpTabLayout() {
        val pageAdapter = ProductPageAdapter(requireActivity(), TABS.size)
        binding.viewPager.adapter = pageAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = TABS[position]
        }.attach()

        TABS.forEachIndexed { index, _ ->
            val tabTitle = layoutInflater.inflate(R.layout.tab_title, null) as MaterialTextView
            binding.tabs.getTabAt(index)?.customView = tabTitle
        }
    }

    companion object {
        private val TABS = arrayOf(
            "All Shoes", "Running", "Basketball", "Hiking", "Football"
        )
    }
}