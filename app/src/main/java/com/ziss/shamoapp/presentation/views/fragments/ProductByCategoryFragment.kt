package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.databinding.FragmentProductByCategoryBinding
import com.ziss.shamoapp.presentation.adapters.ProductItemAdapter
import com.ziss.shamoapp.presentation.views.activities.ProductDetailActivity

class ProductByCategoryFragment : Fragment() {
    private var _binding: FragmentProductByCategoryBinding? = null
    private val binding get() = _binding!!

    private var categoryIndex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryIndex = it.getString(ARG_SECTION_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductByCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProductByCategoryList()
    }

    private fun setupProductByCategoryList() {
        val itemAdapter = ProductItemAdapter()
        val itemLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        val itemDecoration = MarginVerticalItemDecoration(30)

        itemAdapter.setOnItemClickCallback(object : ProductItemAdapter.OnItemClickCallback {
            override fun onItemClicked() {
                ProductDetailActivity.start(requireActivity(), 1)
            }
        })

        binding.apply {
            rvProductByCategoryList.adapter = itemAdapter
            rvProductByCategoryList.layoutManager = itemLayoutManager
            rvProductByCategoryList.addItemDecoration(itemDecoration)
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun startWithParams(position: Int): ProductByCategoryFragment {
            return ProductByCategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, position)
                }
            }
        }
    }
}